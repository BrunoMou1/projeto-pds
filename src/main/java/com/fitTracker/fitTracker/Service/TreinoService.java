package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Nivel;
import com.fitTracker.fitTracker.Models.Treino;

import java.util.List;

public interface TreinoService {

    public Nivel createNivel(Nivel nivel);

    public Treino createTreino(Treino treino);

    public List<Treino> findTreinoByNivelId(Long nivelId);

    public List<Nivel> findAllNivel();
}
