package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {

    public Matricula save(Matricula matricula);

    public void deleteById(Long id);

    public Optional<Matricula> findById(Long id);

    public List<Matricula> listAll();

    public List<Matricula> listByUsuarioId(Long id);

    public void enableOrDisableStatusById(Long id);

    public void validar(Matricula matricula);
}
