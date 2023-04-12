package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.EnderecoPessoa;
import com.fitTracker.fitTracker.Models.EnderecoPessoaKey;
import com.fitTracker.fitTracker.Repositories.EnderecoPessoaRepository;
import com.fitTracker.fitTracker.Service.EnderecoPessoaService;
import com.fitTracker.fitTracker.Service.EnderecoService;
import com.fitTracker.fitTracker.Service.PessoaService;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoPessoaServiceImpl implements EnderecoPessoaService {

    @Autowired
    private EnderecoPessoaRepository enderecoPessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;


    @Override
    public EnderecoPessoa save(EnderecoPessoa enderecoPessoa) {

        if(enderecoPessoa.getEndereco().getId() == null) {
            enderecoPessoa.setEndereco(enderecoService.save(enderecoPessoa.getEndereco()));
        }else {
            enderecoPessoa.setEndereco(enderecoService.findById(enderecoPessoa.getEndereco().getId()).get());
        }

        enderecoPessoa.setPessoa(pessoaService.findById(enderecoPessoa.getPessoa().getId()).get());

        return enderecoPessoaRepository.save(enderecoPessoa);
    }

    @Override
    public List<EnderecoPessoa> listAll(){
        return enderecoPessoaRepository.findAll();
    }

    @Override
    public Optional<EnderecoPessoa> findById(EnderecoPessoaKey id) {

        Optional<EnderecoPessoa> enderecoPessoa = enderecoPessoaRepository.findByPessoaIdAndEnderecoId(id.getPessoaId(), id.getEnderecoId());

        if(enderecoPessoa.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhum relação entre o endereco e o usuario recebidos!");
        }

        return enderecoPessoa;
    }

    @Override
    public void deleteById(EnderecoPessoaKey id) {

        Optional<EnderecoPessoa> enderecoPessoa = enderecoPessoaRepository.findByPessoaIdAndEnderecoId(id.getPessoaId(), id.getEnderecoId());

        if(enderecoPessoa.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhum relação entre o endereco e o usuario recebidos!");
        }

        enderecoPessoaRepository.deleteByPessoaIdAndEnderecoId(id.getPessoaId(), id.getEnderecoId());
    }

    @Override
    public List<EnderecoPessoa> listByIdPessoa(Long id) {

        pessoaService.findById(id);

        return enderecoPessoaRepository.findByPessoaId(id);
    }
}
