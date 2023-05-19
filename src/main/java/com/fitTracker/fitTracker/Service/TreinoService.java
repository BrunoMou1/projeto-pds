package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Nivel;
import com.fitTracker.fitTracker.Models.Treino;

import java.util.List;

public interface TreinoService {

    Nivel createNivel(Nivel nivel);

    Treino createTreino(Treino treino);

    List<Treino> findTreinoByNivelId(Long nivelId);

    List<Nivel> findAllNivel();

    void deleteTreino(Long treinoId);

    Treino updateTreino(Treino treino);

    Nivel updateNivel(Nivel nivel);

    void deleteNivel(Long nivelId);

    void validarNivel(Nivel nivel);

    void validarTreino(Treino treino);
}
