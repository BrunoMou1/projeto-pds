package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Service.FrequenciaService;
import com.fitTracker.fitTracker.Service.RecompensaService;
import com.fitTracker.fitTracker.Service.impl.FrequenciaServiceImpl;
import com.fitTracker.fitTracker.Strategy.concrets.EstrategiaCheckin;
import com.fitTracker.fitTracker.Strategy.concrets.EstrategiaRecompensaAcademia;
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
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private FrequenciaService checkInService;

    @Autowired
    private RecompensaService recompensaService;

    @PostMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity createCheckin(@PathVariable("id") Long id, @RequestBody Checkin checkin){
        try {

            Checkin resposta = (Checkin) checkInService.save(checkin, id, new EstrategiaCheckin());

            recompensaService.gerarPontuacao(id, new EstrategiaRecompensaAcademia());

            return new ResponseEntity(resposta, HttpStatus.CREATED);
        } catch (CheckinJaExisteException | ElementoNaoEncontradoException | RepositoryNullException ex ) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity listCheckinsByUsuario(@PathVariable("id") Long id){
        try {
            List<Checkin> listCheckin = new ArrayList(checkInService.findFrequenciaByUsuarioId(id));
            return ResponseEntity.ok(listCheckin);
        } catch(ElementoNaoEncontradoException | RepositoryNullException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
