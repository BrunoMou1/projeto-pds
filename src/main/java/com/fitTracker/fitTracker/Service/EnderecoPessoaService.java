package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.EnderecoPessoa;
import com.fitTracker.fitTracker.Models.EnderecoPessoaKey;

import java.util.List;
import java.util.Optional;

public interface EnderecoPessoaService {
    public EnderecoPessoa save(EnderecoPessoa endereco);

    public List<EnderecoPessoa> listAll();

    public Optional<EnderecoPessoa> findById(EnderecoPessoaKey id);

    public void deleteById(EnderecoPessoaKey id);

    public List<EnderecoPessoa> listByIdPessoa(Long id);
}
