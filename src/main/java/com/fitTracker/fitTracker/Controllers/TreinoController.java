package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Nivel;
import com.fitTracker.fitTracker.Models.Treino;
import com.fitTracker.fitTracker.Service.TreinoService;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TreinoController {

    @Autowired
    TreinoService treinoService;

    @PostMapping(value="/level", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Nivel createNivel(@RequestBody Nivel nivel) {
        try {
            return treinoService.createNivel(nivel);
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Treino createTreino(@RequestBody Treino treino) {
        try {
            return treinoService.createTreino(treino);
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Treino> findTreinoByIdNivel(@PathVariable("id") Long nivelId){
        try {
            return treinoService.findTreinoByNivelId(nivelId);
        }catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(value="/level")
    @ResponseStatus(HttpStatus.OK)
    public List<Nivel> findAllNivel() {
        return treinoService.findAllNivel();
    }
}
