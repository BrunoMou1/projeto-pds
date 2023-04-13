package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Pessoa;
import com.fitTracker.fitTracker.Repositories.PessoaRepository;
import com.fitTracker.fitTracker.Service.PessoaService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {

        Optional<Pessoa> pessoaVerificacao = pessoaRepository.findByCpf(pessoa.getCpf());

        if(pessoaVerificacao.isPresent()){
            throw new ElementoExisteException("Já existe uma pessoa com esse cpf!");
        }

        return pessoaRepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) {

        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);

        if(pessoa.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrada nenhuma Pessoa com esse cpf!");
        }
        return pessoa;
    }

    @Override
    public void deleteById(Long id) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if(pessoa.isEmpty()) {
            throw new ElementoNaoEncontradoException("Não foi encontrada nenhuma Pessoa com esse id!");
        }

        pessoaRepository.deleteById(id);
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if(pessoa.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrada nenhuma Pessoa com esse id!");
        }
        return pessoa;
    }

    @Override
    public Pessoa update(Long id, Pessoa pessoa) {
        return null;
    }
}
