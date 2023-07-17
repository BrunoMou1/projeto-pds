package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;

import java.util.List;

public interface AtividadeService {
    Atividade createAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);

    void deleteAtividade(Long atividadeId);

    Atividade updateAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);

    void validarAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);
}
