package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Frequencia;

import java.util.List;

public interface FrequenciaRepository extends GenericRepository<Frequencia, Long> {
    List<Frequencia> findByUsuarioId(Long id);

}
