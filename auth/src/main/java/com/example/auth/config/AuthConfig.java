package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración de seguridad para la aplicación de autenticación.
 *
 * Define beans esenciales para la autenticación y autorización de usuarios
 * usando Spring Security.
 *
 * Anotaciones usadas: - @Configuration: Marca la clase como una clase de
 * configuración para Spring. - @EnableWebSecurity: Habilita la configuración de
 * seguridad web de Spring Security.
 *
 * Esta clase: - Define el servicio para cargar los detalles de usuario. -
 * Configura la cadena de filtros de seguridad (SecurityFilterChain). -
 * Configura el codificador de contraseñas. - Define el proveedor de
 * autenticación. - Expone el administrador de autenticación.
 *
 */
@Configuration
@EnableWebSecurity
public class AuthConfig {
    
    /**
     * Configura la cadena de filtros de seguridad HTTP.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                        "/api/v1/auth/register",
                        "/api/v1/auth/token",
                        "/api/v1/auth/validate"
                    ).permitAll()
                    .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }
    
    /**
     * Bean que proporciona el codificador de contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Configura el AuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder, 
            DetallesUsuarioServicio userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    
    /**
     * Exposición del AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}