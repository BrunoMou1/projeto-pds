package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Matricula;
import com.fitTracker.fitTracker.Service.MatriculaService;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class MatriculaController {
    @Autowired
    MatriculaService matriculaService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Matricula createMatricula(@RequestBody Matricula matricula) {
        try {
            return matriculaService.save(matricula);
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Matricula> listByIdUsuario(@PathVariable Long id) {
        try {
            return matriculaService.listByUsuarioId(id);
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatricula(@PathVariable Long id) {
        try {
            matriculaService.deleteById(id);
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Matricula> listAll() {
        return matriculaService.listAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void enableOrDisableStatusById(@PathVariable Long id) {
        try {
            matriculaService.enableOrDisableStatusById(id);
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
