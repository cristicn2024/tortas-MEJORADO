package com.example.auth.dto;



/**
 * DTO (Data Transfer Object) que representa la respuesta con la información
 * del usuario obtenida desde el servicio o base de datos.
 * 
 * Contiene los campos:
 * - idUsuario: identificador único del usuario.
 * - username: nombre de usuario.
 * - password: contraseña codificada.
 * 
 * Las anotaciones Lombok usadas:
 * - @Getter y @Setter para los métodos de acceso.
 * - @NoArgsConstructor para constructor sin argumentos.
 * - @AllArgsConstructor para constructor con todos los campos.
 * 
 * @author Ramos
 */
public class UsuarioResponse {

    private String idUsuario;
    private String username;
    private String password;
    
     // Constructor sin argumentos
    public UsuarioResponse() {}
    
    // Constructor con argumentos
    public UsuarioResponse(String idUsuario, String username, String password) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
    }
    
    // Getters y Setters
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
