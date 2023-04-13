package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Security.AuthServices.UserDetailsImpl;
import com.fitTracker.fitTracker.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }

//    @GetMapping("usuario")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<?> getUsuario() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        Usuario usuario = usuarioService.findByUsername(userDetails.getUsername()).get();
//        return ResponseEntity.ok(usuario);
//    }

//    @GetMapping("usuario/checkins")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<?> getUsuarioCheckins() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        Usuario usuario = usuarioService.findByUsername(userDetails.getUsername()).get();
//        return ResponseEntity.ok(usuario.getCheckins());
//    }
}
