package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Plano;
import com.fitTracker.fitTracker.Repositories.PlanoRepository;
import com.fitTracker.fitTracker.Service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoServiceImpl implements PlanoService {

    @Autowired
    PlanoRepository planoRepository;

    @Override
    public Plano create(Plano plano) {
        return planoRepository.save(plano);
    }

    @Override
    public Optional<Plano> findByNome(String nome) {
        return planoRepository.findByNome(nome);
    }

    @Override
    public List<Plano> findAll() {
        return planoRepository.findAll();
    }

    @Override
    public Optional<Plano> findById(Long id) {
        return planoRepository.findById(id);
    }
}
