package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Plano;
import com.fitTracker.fitTracker.Service.PlanoService;
import com.fitTracker.fitTracker.Service.impl.PlanoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
