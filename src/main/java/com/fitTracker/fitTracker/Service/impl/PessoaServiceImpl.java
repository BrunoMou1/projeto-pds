package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Pessoa;
import com.fitTracker.fitTracker.Repositories.PessoaRepository;
import com.fitTracker.fitTracker.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    @Override
    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }
}
