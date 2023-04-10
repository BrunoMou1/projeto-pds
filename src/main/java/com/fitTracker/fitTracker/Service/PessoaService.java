package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Pessoa;

import java.util.Optional;

public interface PessoaService {

    public Pessoa save(Pessoa pessoa);

    public Optional<Pessoa> findByCpf(String cpf);

    public void deleteById(Long id);

    public Optional<Pessoa> findById(Long id);
}
