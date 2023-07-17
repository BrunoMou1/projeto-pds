package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Avaliacao;
import com.fitTracker.fitTracker.Models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public Usuario create(Usuario usuario);

    public Optional<Usuario> findByUsername(String username);

    public Optional<Usuario> findById(Long id);

    public void deleteById(Long id);

    public List<Usuario> findAll();

    public List<Avaliacao> addAvaliacaoUsuario(Long usuarioId, Long treinoId);

}
