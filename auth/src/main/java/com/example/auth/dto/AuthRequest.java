package com.example.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) que representa la solicitud de autenticación.
 * 
 * Contiene las credenciales del usuario que se usan para el login:
 * - username: nombre de usuario.
 * - password: contraseña en texto plano (antes de ser codificada).
 * 
 * Anotaciones Lombok usadas para generar código automáticamente:
 * - @Getter y @Setter: genera métodos getters y setters.
 * - @AllArgsConstructor: genera un constructor con todos los campos.
 * - @NoArgsConstructor: genera un constructor sin argumentos.
 * - @Builder: habilita el patrón builder para crear instancias.
 * - @Data: combina @Getter, @Setter, @RequiredArgsConstructor, @ToString y @EqualsAndHashCode.
 * 
 * @author Ramos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest() {}

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
