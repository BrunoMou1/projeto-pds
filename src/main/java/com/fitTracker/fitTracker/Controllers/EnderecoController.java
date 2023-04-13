package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Endereco;
import com.fitTracker.fitTracker.Models.EnderecoPessoa;
import com.fitTracker.fitTracker.Models.Pessoa;
import com.fitTracker.fitTracker.Service.EnderecoPessoaService;
import com.fitTracker.fitTracker.Service.PessoaService;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/address")
public class EnderecoController {

    @Autowired
    private EnderecoPessoaService enderecoPessoaService;


    @Autowired
    private PessoaService pessoaService;

    @PostMapping(value="/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoPessoa createEnderecoPessoa(@PathVariable("id") Long id, @RequestBody Endereco endereco) {

        try {
            Pessoa pessoa = pessoaService.findById(id).get();
            EnderecoPessoa enderecoPessoa = new EnderecoPessoa(pessoa, endereco);

            return enderecoPessoaService.save(enderecoPessoa);

        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(value="/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoPessoa> listByIdPessoa(@PathVariable("id") Long id){
        try {
            return enderecoPessoaService.listByIdPessoa(id);
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteEnderecoPessoa(@RequestBody EnderecoPessoa enderecoPessoa) {
        try {
            enderecoPessoaService.deleteById(enderecoPessoa.getId());
        } catch (ElementoNaoEncontradoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoPessoa> listAll(){
        return enderecoPessoaService.listAll();
    }
}
