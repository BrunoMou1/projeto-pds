package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Matricula;
import com.fitTracker.fitTracker.Service.MatriculaService;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class MatriculaController {
    @Autowired
    MatriculaService matriculaService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity createMatricula(@RequestBody Matricula matricula) {

        try {
            Matricula matriculaSalva = matriculaService.save(matricula);
            return new ResponseEntity(matriculaSalva, HttpStatus.CREATED);
        } catch (ElementoNaoEncontradoException | RegraNegocioException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity listByIdUsuario(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(matriculaService.listByUsuarioId(id));
        } catch (ElementoNaoEncontradoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMatricula(@PathVariable Long id) {
        try {
            matriculaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ElementoNaoEncontradoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(matriculaService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity enableOrDisableStatusById(@PathVariable Long id) {
        try {
            matriculaService.enableOrDisableStatusById(id);
            return ResponseEntity.ok().build();
        } catch (ElementoNaoEncontradoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
