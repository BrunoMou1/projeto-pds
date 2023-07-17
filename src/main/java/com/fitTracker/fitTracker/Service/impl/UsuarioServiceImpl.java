package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Avaliacao;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.UsuarioService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AtividadeRepository atividadeRepository;

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Avaliacao> addAvaliacaoUsuario(Long usuarioId, Long treinoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId).get();

        if(usuario != null){

            Avaliacao avaliacao = (Avaliacao) atividadeRepository.findById(treinoId).get();

            if(avaliacao != null){

                if(!usuario.getAvaliacoes().contains(avaliacao)) {
                    usuario.getAvaliacoes().add(avaliacao);
                    return usuarioRepository.save(usuario).getAvaliacoes();
                }else {
                    throw new ElementoExisteException("Essa avaliação já foi cadastrado para esse usuario");
                }
            }else {
                throw new ElementoNaoEncontradoException("Essa avaliação informado não foi encotnrado no sistema.");
            }
        }else {
            throw new ElementoNaoEncontradoException("Esse usuario informado não foi encontrado no sistema.");
        }
    }
}
