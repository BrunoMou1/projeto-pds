package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Avaliacao;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Service.UsuarioService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping(value="/{usuarioId}/training/{treinoId}")
    public List<Avaliacao> addTreino(@PathVariable("usuarioId") Long usuarioId, @PathVariable("treinoId") Long treinoId){
        try{
            return usuarioService.addAvaliacaoUsuario(usuarioId, treinoId);
        }catch (ElementoNaoEncontradoException | ElementoExisteException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/username")
    public Usuario getUsuarioByUsername(@RequestParam String username) {
        return usuarioService.findByUsername(username).get();
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
