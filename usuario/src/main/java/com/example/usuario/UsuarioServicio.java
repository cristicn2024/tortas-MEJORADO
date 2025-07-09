/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.usuario;

import com.example.usuario.client.ReporteClient;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Servicio de negocio para gestionar operaciones sobre usuarios.
 */
@Service
@AllArgsConstructor
public class UsuarioServicio {

    private final UsuarioRepository usuarioRepository;
    private final ReporteClient reporteClient;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuario(String id) {
        return usuarioRepository.findById(id);
    }

    public void saveOrUpdate(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void delete(String id) {
        usuarioRepository.deleteById(id);
    }

    public FullUsuarioResponse findAllReportesByUsuario(String idUsuario) {
        var usuario = usuarioRepository.findById(idUsuario).orElse(
                new Usuario(null, "NOT_FOUND", "NOT_FOUND", null)
        );

        var reportes = reporteClient.findAllReportesByUsuario(idUsuario);

        return FullUsuarioResponse.builder()
                .fullName(usuario.getFullName())
                .username(usuario.getUsername())
                .reportes(reportes)
                .build();
    }

    public Optional<Usuario> getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
