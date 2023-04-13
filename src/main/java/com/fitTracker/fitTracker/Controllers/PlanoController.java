package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Plano;
import com.fitTracker.fitTracker.Service.PlanoService;
import com.fitTracker.fitTracker.Service.impl.PlanoServiceImpl;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Plano create(@RequestBody Plano plano){
        return planoService.create(plano);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Plano> findAll() {
        return planoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Plano findById(@PathVariable("id") Long id) {
        try {
            return planoService.findById(id).get();
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping(value="/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public void updatePlano(@PathVariable("id") Long id, @RequestBody Plano plano){
        plano.setId(id);

        try {
            planoService.update(plano);
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
