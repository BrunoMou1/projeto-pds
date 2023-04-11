package com.fitTracker.fitTracker.Controllers;

import aj.org.objectweb.asm.Type;
import com.fitTracker.fitTracker.Models.Matricula;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registration")
public class MatriculaController {
    @Autowired
    MatriculaService matriculaService;

    @Autowired
    UsuarioRepository usuarioService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Matricula createMatricula(@RequestBody Matricula matricula) {
        usuarioService.findById(matricula.getUsuario().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado."));
        return matriculaService.save(matricula);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Matricula findByIdUsuario(@PathVariable Long id) {
        usuarioService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado."));

        return matriculaService.findByIdUsuario(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Este usuario não possui matricula."));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMatricula(@PathVariable Long id) {
        matriculaService.findById(id)
                .map(matricula -> {
                    matriculaService.deleteById(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esta matricula não existe"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Matricula> listAll() {

        return matriculaService.listAll();
    }
}
