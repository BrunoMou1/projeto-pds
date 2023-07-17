package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.*;
import com.fitTracker.fitTracker.Service.AtividadeService;
import com.fitTracker.fitTracker.Service.UsuarioService;
import com.fitTracker.fitTracker.Strategy.concrets.EstrategiaAtividadeIdiomas;
import com.fitTracker.fitTracker.Strategy.concrets.EstrategiaTreino;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import com.fitTracker.fitTracker.Util.RepositoryNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity createAtividade(@RequestBody AtividadeIdiomas atividade){
        try{
            AtividadeIdiomas atividadeIdiomasCriada = (AtividadeIdiomas) atividadeService.createAtividade(atividade,
                    new EstrategiaAtividadeIdiomas());
            return new ResponseEntity(atividadeIdiomasCriada, HttpStatus.CREATED);
        } catch (ElementoNaoEncontradoException | RegraNegocioException | RepositoryNullException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping(value="/{atividadeId}/{userId}", produces = "application/json;charset=UTF-8")
    public ResponseEntity responseAtividade(@PathVariable("atividadeId") Long atividadeId,
                                            @PathVariable("userId") Long userId, @RequestBody List<String> responses){
        try{
            Atividade atividade = atividadeService.findAtividadeByAtividadeId(atividadeId);
            List<String> respostas;
            respostas = atividadeService.checkIfCorrectAnswer(atividade, userId, responses);
            return new ResponseEntity(respostas, HttpStatus.OK);
        } catch (ElementoNaoEncontradoException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping(value="/level")
    public ResponseEntity findAllNivel() {
        return ResponseEntity.ok(atividadeService.findAllNivel());
    }

    @PutMapping(value="/level", produces = "application/json;charset=UTF-8")
    public ResponseEntity updateNivel(@RequestBody Nivel nivel){
        try{
            Nivel nivelAtualizado = atividadeService.updateNivel(nivel);
            return ResponseEntity.ok(nivelAtualizado);
        }catch (ElementoNaoEncontradoException | RegraNegocioException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity updateAtividadeIdiomas(@RequestBody AtividadeIdiomas atividade){
        try{
            AtividadeIdiomas atividadeAtualizada = (AtividadeIdiomas) atividadeService.updateAtividade(atividade,
                    new EstrategiaAtividadeIdiomas());

            return ResponseEntity.ok(atividadeAtualizada);
        }catch (ElementoNaoEncontradoException | RegraNegocioException | RepositoryNullException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAtividade(@PathVariable("id") Long atividadeId){
        try{
            atividadeService.deleteAtividade(atividadeId);
            return ResponseEntity.ok().build();
        }catch (ElementoNaoEncontradoException | RegraNegocioException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
