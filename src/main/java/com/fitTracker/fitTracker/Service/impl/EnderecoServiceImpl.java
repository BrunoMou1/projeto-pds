package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Endereco;
import com.fitTracker.fitTracker.Repositories.EnderecoRepository;
import com.fitTracker.fitTracker.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public List<Endereco> listAll(){
        return enderecoRepository.findAll();
    }

    @Override
    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }
}
