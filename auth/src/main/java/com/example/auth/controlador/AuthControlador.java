package com.example.auth.controlador;

import com.example.auth.config.DetallesUsuario;
import com.example.auth.config.DetallesUsuarioServicio;
import com.example.auth.dto.AuthRequest;
import com.example.auth.dto.UsuarioRequest;
import com.example.auth.feign.UsuarioClient;
import com.example.auth.servicios.JWTServicio;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para la autenticación y registro de usuarios.
 * 
 * Define endpoints para:
 * - Registro de nuevos usuarios.
 * - Login y generación de token JWT.
 * - Validación de tokens.
 * 
 * Utiliza Spring Security para la autenticación y un cliente Feign para la
 * comunicación con el servicio de usuarios.
 * 
 * @author Ramos
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthControlador {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioClient usuarioClient;
    
    @Autowired
    private JWTServicio jwtTokenUtil;  // Usar JwtTokenUtil en lugar de JWTServicio
    
    @Autowired
    private DetallesUsuarioServicio userDetailsService;
    
   
    /**
     * Endpoint para registrar un nuevo usuario.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> addNewUser(@RequestBody UsuarioRequest usuarioRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            // Codificar la contraseña
            String encodedPassword = new BCryptPasswordEncoder().encode(usuarioRequest.getPassword());
            usuarioRequest.setPassword(encodedPassword);
            
            // Crear el usuario
            usuarioClient.createUsuario(usuarioRequest);
            
            response.put("message", "Usuario registrado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al registrar: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    /**
     * Endpoint para autenticar a un usuario y generar un token JWT.
     */
    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
        Map<String, String> response = new HashMap<>();
        
        try {
            System.out.println("=== INICIANDO PROCESO DE AUTENTICACIÓN ===");
            System.out.println("Username: " + request.getUsername());
            
            // Autenticar las credenciales
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            
            System.out.println("Autenticación exitosa para: " + request.getUsername());
            
            // Obtener los detalles del usuario
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            
            // Generar el token JWT usando UserDetails
            String token = jwtTokenUtil.generateToken(userDetails);
            
            System.out.println("Token generado exitosamente");
            
            response.put("token", token);
            response.put("username", request.getUsername());
            
            return ResponseEntity.ok(response);
            
        } catch (BadCredentialsException e) {
            System.out.println("Credenciales inválidas para: " + request.getUsername());
            response.put("error", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            
        } catch (UsernameNotFoundException e) {
            System.out.println("Usuario no encontrado: " + request.getUsername());
            response.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            
        } catch (Exception e) {
            System.out.println("Error durante la autenticación: " + e.getMessage());
            e.printStackTrace();
            response.put("error", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Endpoint para validar un token JWT.
     */
    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestParam("token") String token) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validar el token
            if (jwtTokenUtil.validateToken(token)) {
                String username = jwtTokenUtil.getUsernameFromToken(token);
                
                response.put("valid", true);
                response.put("username", username);
                response.put("message", "Token válido");
                
                return ResponseEntity.ok(response);
            } else {
                response.put("valid", false);
                response.put("message", "Token inválido o expirado");
                
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            response.put("valid", false);
            response.put("message", "Error al validar token: " + e.getMessage());
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
    /**
     * Endpoint alternativo para validar token via POST.
     * @param tokenRequest
     * @return 
     */
    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateTokenPost(@RequestBody TokenRequest tokenRequest) {
        return validateToken(tokenRequest.getToken());
    }
}


// DTOs necesarios
class JwtRequest {
    private String username;
    private String password;
    
    // getters y setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class JwtResponse {
    private final String jwttoken;
    
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
    
    public String getToken() {
        return this.jwttoken;
    }
}

class TokenRequest {
    private String token;
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}

class ValidTokenResponse {
    private String username;
    private boolean valid;
    
    public ValidTokenResponse(String username, boolean valid) {
        this.username = username;
        this.valid = valid;
    }
    
    public String getUsername() { return username; }
    public boolean isValid() { return valid; }
}