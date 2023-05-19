package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Plano;

import java.util.List;
import java.util.Optional;

public interface PlanoService {

    public Plano create(Plano plano);

    public Optional<Plano> findByNome(String nome);

    public List<Plano> findAll();

    public Optional<Plano> findById(Long id);

    public void update(Plano plano);

    public void validar(Plano plano);
}
