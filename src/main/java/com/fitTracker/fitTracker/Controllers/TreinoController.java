package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Nivel;
import com.fitTracker.fitTracker.Models.Treino;
import com.fitTracker.fitTracker.Service.TreinoService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TreinoController {

    @Autowired
    TreinoService treinoService;

    @PostMapping(value="/level", produces = "application/json;charset=UTF-8")
    public ResponseEntity createNivel(@RequestBody Nivel nivel) {
        try {
            Nivel nivelSalvo = treinoService.createNivel(nivel);

            return new ResponseEntity(nivelSalvo, HttpStatus.CREATED);
        } catch (ElementoNaoEncontradoException | RegraNegocioException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity createTreino(@RequestBody Treino treino) {
        try {
            Treino treinoSalvo = treinoService.createTreino(treino);

            return new ResponseEntity(treinoSalvo, HttpStatus.CREATED);
        } catch (ElementoNaoEncontradoException | RegraNegocioException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity findTreinoByIdNivel(@PathVariable("id") Long nivelId){
        try {
            List<Treino> lista = treinoService.findTreinoByNivelId(nivelId);

            return ResponseEntity.ok(lista);
        }catch (ElementoNaoEncontradoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(value="/level")
    public ResponseEntity findAllNivel() {
        return ResponseEntity.ok(treinoService.findAllNivel());
    }

    @PutMapping(value="/level", produces = "application/json;charset=UTF-8")
    public ResponseEntity updateNivel(@RequestBody Nivel nivel){
        try{
            Nivel nivelAtualizado = treinoService.updateNivel(nivel);

            return ResponseEntity.ok(nivelAtualizado);
        }catch (ElementoNaoEncontradoException | RegraNegocioException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity updateTreino(@RequestBody Treino treino){
        try{
            Treino treinoAtualizado = treinoService.updateTreino(treino);

            return ResponseEntity.ok(treinoAtualizado);
        }catch (ElementoNaoEncontradoException | RegraNegocioException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTreino(@PathVariable("id") Long treinoId){
        try{
            treinoService.deleteTreino(treinoId);
            return ResponseEntity.ok().build();
        }catch (ElementoNaoEncontradoException | RegraNegocioException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/level/{id}")
    public ResponseEntity deleteNivel(@PathVariable("id") Long nivelId){
        try{
            treinoService.deleteNivel(nivelId);
            return ResponseEntity.ok().build();
        }catch (ElementoNaoEncontradoException | ElementoExisteException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
