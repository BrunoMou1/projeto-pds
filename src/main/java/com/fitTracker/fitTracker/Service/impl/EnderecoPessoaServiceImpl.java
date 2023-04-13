package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Endereco;
import com.fitTracker.fitTracker.Models.EnderecoPessoa;
import com.fitTracker.fitTracker.Models.EnderecoPessoaKey;
import com.fitTracker.fitTracker.Repositories.EnderecoPessoaRepository;
import com.fitTracker.fitTracker.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoPessoaServiceImpl {

    @Autowired
    private EnderecoPessoaRepository enderecoPessoaRepository;

    public EnderecoPessoa save(EnderecoPessoa enderecoPessoa) {
        return enderecoPessoaRepository.save(enderecoPessoa);
    }

    public List<EnderecoPessoa> listAll(){
        return enderecoPessoaRepository.findAll();
    }

    public Optional<EnderecoPessoa> findById(EnderecoPessoaKey id) {
        return enderecoPessoaRepository.findByPessoaIdAndEnderecoId(id.getPessoaId(), id.getEnderecoId());
    }

    public void deleteById(EnderecoPessoaKey id) {
        enderecoPessoaRepository.deleteByPessoaIdAndEnderecoId(id.getPessoaId(), id.getEnderecoId());
    }

    public List<EnderecoPessoa> listEnderecosByIdPessoa(Long id) {
        return enderecoPessoaRepository.findByPessoaId(id);
    }
}
