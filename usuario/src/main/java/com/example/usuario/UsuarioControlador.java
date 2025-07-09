package com.example.usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que gestiona las operaciones CRUD para los usuarios.
 */
@RestController
@RequestMapping(path = "/api/v1/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    UsuarioRepository usuarioRepositorio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioServicio.getUsuarios();
    }

    @GetMapping("/{idUsuario}")
    public Optional<Usuario> getByID(@PathVariable("idUsuario") String idUsuario) {
        return usuarioServicio.getUsuario(idUsuario);
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody Usuario usuario) {
        usuarioServicio.saveOrUpdate(usuario);
    }

    @DeleteMapping("/{idUsuario}")
    public void delete(@PathVariable("idUsuario") String idUsuario) {
        usuarioServicio.delete(idUsuario);
    }

    @GetMapping("/con-reportes/{idUsuario}")
    public FullUsuarioResponse getAll(@PathVariable("idUsuario") String idUsuario) {
        return usuarioServicio.findAllReportesByUsuario(idUsuario);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioResponse> getByUsername(@PathVariable String username) {
        Optional<Usuario> usuario = usuarioRepositorio.findByUsername(username);
        return usuario.map(u -> new UsuarioResponse(
                u.getIdUsuario(),
                u.getUsername(),
                u.getPassword()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
