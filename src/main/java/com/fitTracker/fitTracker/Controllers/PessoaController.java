package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Pessoa;
import com.fitTracker.fitTracker.Service.impl.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/people")
public class PessoaController {
    @Autowired
    private PessoaServiceImpl pessoaService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa create(@RequestBody Pessoa pessoa) {
        return pessoaService.save(pessoa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) {
        pessoaService.findById(id)
                .map(pessoa -> {
                    pessoaService.deleteById(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Essa Pessoa não foi encontrada."));
    }

    @GetMapping(value="/{cpf}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa findByCpf(@PathVariable("cpf") String cpf){
        return pessoaService.findByCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Essa Pessoa não foi encontrada."));
    }
}
