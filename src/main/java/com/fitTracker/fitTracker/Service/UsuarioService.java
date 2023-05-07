package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Plano;
import com.fitTracker.fitTracker.Models.Treino;
import com.fitTracker.fitTracker.Models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public Usuario create(Usuario usuario);

    public Optional<Usuario> findByUsername(String username);

    public Optional<Usuario> findById(Long id);

    public void deleteById(Long id);

    public List<Usuario> findAll();

    public List<Treino> addTreinoUsuario(Long usuarioId, Long treinoId);

    public List<Treino> removeTreinoUsuario(Long usuarioId, Long treinoId);

}
