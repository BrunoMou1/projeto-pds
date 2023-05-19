package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Pessoa;
import com.fitTracker.fitTracker.Repositories.PessoaRepository;
import com.fitTracker.fitTracker.Service.PessoaService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {

        validar(pessoa);

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
    public void updateTelefone(Pessoa pessoa) {

        validar(pessoa);

        if(pessoaRepository.findById(pessoa.getId()).isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrada nenhuma Pessoa com esse id!");
        }

        pessoaRepository.updateTelefone(pessoa.getTelefone(), pessoa.getId());
    }

    @Override
    public void validar(Pessoa pessoa){
        if(pessoa.getNomeCompleto() == null || pessoa.getNomeCompleto().trim().equals("")){
            throw new RegraNegocioException("Informe o nome completo válido.");
        }

        if(pessoa.getDataNascimento() == null || pessoa.getDataNascimento().compareTo(new Date()) >= 0){
            throw new RegraNegocioException("Informe uma data de nascimento válida.");
        }

        //Formato Aceito xxx.xxx.xxx-xx
        if(pessoa.getCpf() == null || pessoa.getCpf().trim().equals("") || !pessoa.getCpf().matches( "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")){
            throw new RegraNegocioException("Informe um CPF válido");
        }

        //Formato Aceito (xx) xxxxx-xxxx
        if(pessoa.getTelefone() == null || pessoa.getTelefone().equals("") || !pessoa.getTelefone().matches("^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")){
            throw new RegraNegocioException("Informe um telefone válido");
        }
    }

}
