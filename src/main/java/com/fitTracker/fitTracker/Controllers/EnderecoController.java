package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Endereco;
import com.fitTracker.fitTracker.Models.EnderecoPessoa;
import com.fitTracker.fitTracker.Models.Pessoa;
import com.fitTracker.fitTracker.Service.impl.EnderecoPessoaServiceImpl;
import com.fitTracker.fitTracker.Service.impl.EnderecoServiceImpl;
import com.fitTracker.fitTracker.Service.impl.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/address")
public class EnderecoController {

    @Autowired
    private EnderecoPessoaServiceImpl enderecoPessoaService;

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @Autowired
    private PessoaServiceImpl pessoaService;

    @PostMapping(value="/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoPessoa createEnderecoPessoa(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
        Pessoa pessoa = pessoaService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Essa Pessoa não foi encontrada."));

        if(endereco.getId() == null) {
            enderecoService.save(endereco);
        }
        EnderecoPessoa enderecoPessoa = new EnderecoPessoa(pessoa, endereco);

        return enderecoPessoaService.save(enderecoPessoa);
    }

    @GetMapping(value="/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoPessoa> listEnderecoByIdPessoa(@PathVariable("id") Long id){
        return enderecoPessoaService.listEnderecosByIdPessoa(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteEnderecoPessoa(@RequestBody EnderecoPessoa enderecoPessoa) {
        enderecoPessoaService.findById(enderecoPessoa.getId())
                .map(enderecoPessoaBanco -> {

                    enderecoPessoaService.deleteById(enderecoPessoa.getId());

                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse Endereco não existe com essa pessoa."));
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoPessoa> listAll(){
        return enderecoPessoaService.listAll();
    }
}
