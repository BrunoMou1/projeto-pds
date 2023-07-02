package com.fitTracker.fitTracker.Strategy;

import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.GenericRepository;

public interface EstrategiaRecompensa {

    void gerarPontuacaoUsuario(Usuario usuario);

    void addGenericRepository(GenericRepository genericRepository);

    void clearListGenericRepository();

}
