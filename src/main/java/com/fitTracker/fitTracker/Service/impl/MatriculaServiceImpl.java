package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Matricula;
import com.fitTracker.fitTracker.Repositories.MatriculaRepository;
import com.fitTracker.fitTracker.Service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    MatriculaRepository matriculaRepository;

    @Override
    public Matricula save(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public void deleteById(Long id) {
        matriculaRepository.deleteById(id);
    }

    @Override
    public Optional<Matricula> findById(Long id) {
        return matriculaRepository.findById(id);
    }

    @Override
    public List<Matricula> listAll() {
        return matriculaRepository.findAll();
    }

    @Override
    public Optional<Matricula> findByIdUsuario(Long id){
        return matriculaRepository.findByUsuarioId(id);
    }


}
