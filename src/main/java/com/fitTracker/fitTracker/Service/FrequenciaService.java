package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Frequencia;
import com.fitTracker.fitTracker.Strategy.EstrategiaFrequencia;

import java.util.List;

public interface FrequenciaService {

    Frequencia save(Frequencia frequencia, Long id, EstrategiaFrequencia estrategia);

    List<Frequencia> findFrequenciaByUsuarioId(Long id);
}
