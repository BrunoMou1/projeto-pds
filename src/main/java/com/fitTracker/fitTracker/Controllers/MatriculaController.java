package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Matricula;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.MatriculaRepository;
import com.fitTracker.fitTracker.Repositories.PagamentoRepository;
import com.fitTracker.fitTracker.Repositories.PlanoRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fittracker/")
public class MatriculaController {
    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    PlanoRepository planoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @PostMapping("matricula")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Matricula createMatricula(@RequestBody Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @GetMapping("matricula/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Matricula getMatriculaByUsuarioId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return usuario.getMatricula();
    }

    @PutMapping("matricula/{id}/plano")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Matricula updateMatricula(@PathVariable Long id, @RequestBody Matricula matricula) {
        Matricula matriculaAtual = matriculaRepository.findById(id).get();
        matriculaAtual.setPlano(matricula.getPlano());
        return matriculaRepository.save(matriculaAtual);
    }

    @DeleteMapping("matricula/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteMatricula(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        matriculaRepository.deleteById(usuario.getMatricula().getId());
    }
}
