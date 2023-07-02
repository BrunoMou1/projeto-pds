package com.fitTracker.fitTracker.Strategy;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;

public interface EstrategiaAtividade {

    void validar(Atividade atividade);

    Atividade create(Atividade atividade);

    void clearListGenericRepository();

    void addGenericRepository(GenericRepository genericRepository);

}
