package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Plano;
import com.fitTracker.fitTracker.Service.PlanoService;
import com.fitTracker.fitTracker.Service.impl.PlanoServiceImpl;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity create(@RequestBody Plano plano){
        try {
            Plano planoSalvo = planoService.create(plano);

            return new ResponseEntity(planoSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(planoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(planoService.findById(id).get());
        } catch (ElementoNaoEncontradoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping(value="/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity updatePlano(@PathVariable("id") Long id, @RequestBody Plano plano){
        plano.setId(id);
        try {
            planoService.update(plano);
            return ResponseEntity.ok().build();
        } catch (ElementoNaoEncontradoException | RegraNegocioException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
