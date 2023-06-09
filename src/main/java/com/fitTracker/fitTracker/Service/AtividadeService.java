package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Models.Nivel;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;

import java.util.List;

public interface AtividadeService {

    Nivel createNivel(Nivel nivel);

    Atividade createAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);

    List<Atividade> findAtividadeByNivelId(Long nivelId);

    List<Nivel> findAllNivel();

    void deleteAtividade(Long atividadeId);

    Atividade updateAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);

    Nivel updateNivel(Nivel nivel);

    void deleteNivel(Long nivelId);

    void validarNivel(Nivel nivel);

    void validarAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade);
}
