package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Models.AtividadeIdiomas;
import com.fitTracker.fitTracker.Models.Nivel;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;
import com.fitTracker.fitTracker.Strategy.concrets.EstrategiaAtividadeIdiomas;

import java.util.List;

public interface AtividadeService {

    Nivel createNivel(Nivel nivel);

    Atividade createAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);

    List<Atividade> findAtividadeByNivelId(Long nivelId);

    Atividade findAtividadeByAtividadeId(Long atividadeId);

    public List<String> checkIfCorrectAnswer(Atividade atividade, Long userId, List<String> responses,
                                             EstrategiaAtividade estrategiaAtividade);

    List<Nivel> findAllNivel();

    void deleteAtividade(Long atividadeId);

    Atividade updateAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);

    Nivel updateNivel(Nivel nivel);

    void deleteNivel(Long nivelId);

    void validarNivel(Nivel nivel);

    void validarAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);
}
