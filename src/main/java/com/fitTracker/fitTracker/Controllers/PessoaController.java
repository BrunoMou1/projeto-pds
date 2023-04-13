package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Pessoa;
import com.fitTracker.fitTracker.Models.Plano;
import com.fitTracker.fitTracker.Service.PessoaService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/people")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa create(@RequestBody Pessoa pessoa) {
        try {
            return pessoaService.save(pessoa);
        } catch(ElementoExisteException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) {
        try {
            pessoaService.deleteById(id);
        } catch(ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping(value="/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {
        pessoa.setId(id);

        try {
            pessoaService.updateTelefone(pessoa);
        } catch(ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }


    @GetMapping(value="/{cpf}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa findByCpf(@PathVariable("cpf") String cpf){
        try {
            return pessoaService.findByCpf(cpf).get();
        } catch(ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
