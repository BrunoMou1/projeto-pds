package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Frequencia;
import com.fitTracker.fitTracker.Service.FrequenciaService;
import com.fitTracker.fitTracker.Service.RecompensaService;
import com.fitTracker.fitTracker.Strategy.concrets.EstrategiaFreq;
import com.fitTracker.fitTracker.Strategy.concrets.EstrategiaRecompensaEscola;
import com.fitTracker.fitTracker.Util.CheckinJaExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RepositoryNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/frequencia")
public class FrequenciaController {

    @Autowired
    private FrequenciaService frequenciaService;

    @Autowired
    private RecompensaService recompensaService;

    @PostMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity createFrequencia(@PathVariable("id") Long id, @RequestBody Frequencia frequencia){
        try {

            Frequencia resposta = frequenciaService.save(frequencia, id, new EstrategiaFreq());

            recompensaService.gerarPontuacao(id, new EstrategiaRecompensaEscola());

            return new ResponseEntity(resposta, HttpStatus.CREATED);
        } catch (CheckinJaExisteException | ElementoNaoEncontradoException | RepositoryNullException ex ) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity listFreqByUsuario(@PathVariable("id") Long id){
        try {
            List<Frequencia> listFreq = new ArrayList(frequenciaService.findFrequenciaByUsuarioId(id));
            return ResponseEntity.ok(listFreq);
        } catch(ElementoNaoEncontradoException | RepositoryNullException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
