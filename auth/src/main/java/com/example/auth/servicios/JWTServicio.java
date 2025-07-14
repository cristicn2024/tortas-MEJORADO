/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.auth.servicios;

import com.example.auth.config.DetallesUsuario;
import com.example.auth.dto.UsuarioResponse;
import com.example.auth.feign.UsuarioClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Servicio encargado de la generación y validación de tokens JWT. Este servicio
 * se utiliza para autenticar usuarios y asegurar la comunicación.
 *
 * Utiliza una clave secreta compartida para firmar y verificar los tokens.
 *
 * El token generado incluye el nombre de usuario como "subject" y el idUsuario
 * como un claim personalizado.
 *
 * @author Ramos
 */
@Component
public class JWTServicio {

    // Clave secreta en Base64 utilizada para firmar/verificar los JWTs
    private static final String SECRET = "A213SD331213313332132DA213SD331213313332132D";

    // Validez del token en segundos (30 minutos)
    private static final int JWT_TOKEN_VALIDITY = 30 * 60;

    @Autowired
    private UsuarioClient usuarioClient;

    /**
     * Obtiene el nombre de usuario desde el token JWT.
     *
     * @param token Token JWT
     * @return Nombre de usuario
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Obtiene la fecha de expiración del token JWT.
     *
     * @param token Token JWT
     * @return Fecha de expiración
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Obtiene un claim específico del token JWT.
     *
     * @param token Token JWT
     * @param claimsResolver Función para obtener el claim
     * @return Claim solicitado
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene todos los claims del token JWT.
     *
     * @param token Token JWT
     * @return Claims del token
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Verifica si el token JWT ha expirado.
     *
     * @param token Token JWT
     * @return true si el token ha expirado, false en caso contrario
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Genera un token JWT para un usuario específico.
     *
     * @param userDetails Detalles del usuario autenticado
     * @return Token JWT generado
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        // Obtener información adicional del usuario desde el microservicio
        try {
            UsuarioResponse usuario = usuarioClient.findByUsername(userDetails.getUsername());
            claims.put("userId", usuario.getIdUsuario());
        } catch (Exception e) {
            System.out.println("Error al obtener información del usuario: " + e.getMessage());
            // Continuar sin el userId si hay error
        }

        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Genera un token JWT con username (método de compatibilidad).
     *
     * @param username Nombre de usuario
     * @return Token JWT generado
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        // Obtener información adicional del usuario desde el microservicio
        try {
            UsuarioResponse usuario = usuarioClient.findByUsername(username);
            claims.put("userId", usuario.getIdUsuario());
        } catch (Exception e) {
            System.out.println("Error al obtener información del usuario: " + e.getMessage());
        }

        return createToken(claims, username);
    }

    /**
     * Crea el token JWT con los claims y el subject especificados.
     *
     * @param claims Claims adicionales
     * @param subject Subject del token (username)
     * @return Token JWT creado
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSignKey())
                .compact();
    }

    /**
     * Valida si el token JWT es válido para el usuario especificado.
     *
     * @param token Token JWT
     * @param userDetails Detalles del usuario
     * @return true si el token es válido, false en caso contrario
     */
    public Boolean validateToken(String token, DetallesUsuario userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Valida solo si el token JWT no ha expirado y está bien formado.
     *
     * @param token Token JWT
     * @return true si el token es válido, false en caso contrario
     */
    public Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Convierte la clave secreta codificada en Base64 a un objeto SecretKey.
     *
     * @return Clave secreta como objeto SecretKey
     */
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
