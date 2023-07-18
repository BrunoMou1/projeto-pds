package com.fitTracker.fitTracker.Strategy;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Models.AtividadeIdiomas;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;

import java.util.List;

public interface EstrategiaAtividade {

    void validar(Atividade atividade);

    Atividade create(Atividade atividade);

    List<String> checkAnswer(AtividadeIdiomas atividadeIdiomas, Usuario user, List<String> responses);

    void clearListGenericRepository();

    void addGenericRepository(GenericRepository genericRepository);

}
