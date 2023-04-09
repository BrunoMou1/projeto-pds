package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Security.AuthServices.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fittracker/")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("admin/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("admin/usuario/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id).get();
    }

    @GetMapping("admin/usuario/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario getUsuarioByUsername(@PathVariable String username) {
        return usuarioRepository.findByUsername(username).get();
    }

    @DeleteMapping("admin/usuario/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    @PutMapping("admin/usuario/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtual = usuarioRepository.findById(id).get();
        usuarioAtual.setUsername(usuario.getUsername());
        usuarioAtual.setRoles(usuario.getRoles());
        return usuarioRepository.save(usuarioAtual);
    }

    @GetMapping("usuario")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername()).get();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("usuario/checkins")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUsuarioCheckins() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername()).get();
        return ResponseEntity.ok(usuario.getCheckins());
    }

    @PutMapping("usuario")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Usuario usuarioAtual = usuarioRepository.findByUsername(userDetails.getUsername()).get();
        usuarioAtual.setUsername(usuario.getUsername());
        usuarioAtual.setRoles(usuario.getRoles());
        return ResponseEntity.ok(usuarioRepository.save(usuarioAtual));
    }



}
