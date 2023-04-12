package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.EnderecoPessoa;
import com.fitTracker.fitTracker.Models.EnderecoPessoaKey;
import com.fitTracker.fitTracker.Repositories.EnderecoPessoaRepository;
import com.fitTracker.fitTracker.Service.EnderecoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoPessoaServiceImpl implements EnderecoPessoaService {

    @Autowired
    private EnderecoPessoaRepository enderecoPessoaRepository;


    @Override
    public EnderecoPessoa save(EnderecoPessoa enderecoPessoa) {
        return enderecoPessoaRepository.save(enderecoPessoa);
    }

    @Override
    public List<EnderecoPessoa> listAll(){
        return enderecoPessoaRepository.findAll();
    }

    @Override
    public Optional<EnderecoPessoa> findById(EnderecoPessoaKey id) {
        return enderecoPessoaRepository.findByPessoaIdAndEnderecoId(id.getPessoaId(), id.getEnderecoId());
    }

    @Override
    public void deleteById(EnderecoPessoaKey id) {
        enderecoPessoaRepository.deleteByPessoaIdAndEnderecoId(id.getPessoaId(), id.getEnderecoId());
    }

    @Override
    public List<EnderecoPessoa> listByIdPessoa(Long id) {
        return enderecoPessoaRepository.findByPessoaId(id);
    }
}
