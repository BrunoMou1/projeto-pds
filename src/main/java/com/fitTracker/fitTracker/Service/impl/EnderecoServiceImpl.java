package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Endereco;
import com.fitTracker.fitTracker.Repositories.EnderecoRepository;
import com.fitTracker.fitTracker.Service.EnderecoService;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
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

        Optional<Endereco> endereco = enderecoRepository.findById(id);

        if(endereco.isEmpty()) {
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhum Endereco com esse id");
        }

        return endereco;
    }

    @Override
    public void deleteById(Long id) {

        if(enderecoRepository.findById(id).isEmpty()) {
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhum Endereco com esse id");
        }

        enderecoRepository.deleteById(id);
    }
}
