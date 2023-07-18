package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/username")
    public Usuario getUsuarioByUsername(@RequestParam String username) {
        return usuarioService.findByUsername(username).get();
    }
}
