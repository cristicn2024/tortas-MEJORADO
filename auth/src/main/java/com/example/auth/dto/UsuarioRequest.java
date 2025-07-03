package com.example.auth.dto;

import lombok.Data;

/**
 * DTO (Data Transfer Object) que representa la información necesaria
 * para registrar un nuevo usuario.
 * 
 * Contiene los campos:
 * - fullName: nombre completo del usuario.
 * - username: nombre de usuario único para login.
 * - password: contraseña en texto plano (antes de ser codificada).
 * 
 * La anotación Lombok {@code @Data} genera automáticamente
 * los métodos getters, setters, toString, equals y hashCode.
 * 
 * @author Ramos
 */
@Data
public class UsuarioRequest {

    private String fullName;
    private String username;
    private String password;
    
    public UsuarioRequest() {}

    public UsuarioRequest(String username,String fullName, String password) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
