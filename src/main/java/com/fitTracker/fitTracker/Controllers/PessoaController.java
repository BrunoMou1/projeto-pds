package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Pessoa;
import com.fitTracker.fitTracker.Models.Plano;
import com.fitTracker.fitTracker.Service.PessoaService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/people")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity create(@RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaSalva = pessoaService.save(pessoa);
            return new ResponseEntity(pessoaSalva, HttpStatus.CREATED);
        } catch(ElementoExisteException | RegraNegocioException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            pessoaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(ElementoNaoEncontradoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping(value="/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {
        pessoa.setId(id);

        try {
            pessoaService.updateTelefone(pessoa);
            return ResponseEntity.ok().build();
        } catch(ElementoNaoEncontradoException | RegraNegocioException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @GetMapping(value="/{cpf}", produces = "application/json;charset=UTF-8")
    public ResponseEntity findByCpf(@PathVariable("cpf") String cpf){
        try {
            Pessoa pessoaEncontrada = pessoaService.findByCpf(cpf).get();
            return ResponseEntity.ok(pessoaEncontrada);
        } catch(ElementoNaoEncontradoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
