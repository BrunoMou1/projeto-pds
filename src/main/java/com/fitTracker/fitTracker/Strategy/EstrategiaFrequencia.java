package com.fitTracker.fitTracker.Strategy;

import com.fitTracker.fitTracker.Models.Frequencia;
import com.fitTracker.fitTracker.Repositories.FrequenciaRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;

public interface EstrategiaFrequencia {
    Frequencia gerarFrequencia(Frequencia frequencia);

    void validar(Frequencia frequencia);

    void addGenericRepository(GenericRepository genericRepository);

    void clearListGenericRepository();
}
