package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Recompensa;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Strategy.EstrategiaRecompensa;

import java.util.List;

public interface RecompensaService {

    void gerarPontuacao(Long idUsuario, EstrategiaRecompensa estrategiaRecompensa);

    List<Recompensa> listAll();

    void redeemById(Long recompensaId, Long userId);
}
