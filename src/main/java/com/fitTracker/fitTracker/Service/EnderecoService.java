package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoService {

    public Endereco save(Endereco endereco);

    public List<Endereco> listAll();

    public Optional<Endereco> findById(Long id);

    public void deleteById(Long id);
}
