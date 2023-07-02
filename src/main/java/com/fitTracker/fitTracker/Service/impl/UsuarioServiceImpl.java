package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Treino;
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
    public List<Treino> addTreinoUsuario(Long usuarioId, Long treinoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId).get();

        if(usuario != null){

            Treino treino = (Treino) atividadeRepository.findById(treinoId).get();

            if(treino != null){

                if(!usuario.getTreinos().contains(treino)) {
                    usuario.getTreinos().add(treino);
                    return usuarioRepository.save(usuario).getTreinos();
                }else {
                    throw new ElementoExisteException("Esse treino já foi cadastrado para esse usuario");
                }
            }else {
                throw new ElementoNaoEncontradoException("Esse treino informado não foi encotnrado no sistema.");
            }
        }else {
            throw new ElementoNaoEncontradoException("Esse usuario informado não foi encontrado no sistema.");
        }
    }

    @Override
    public List<Treino> removeTreinoUsuario(Long usuarioId, Long treinoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).get();

        if(usuario != null){

            Treino treino = (Treino) atividadeRepository.findById(treinoId).get();

            if(treino != null){

                usuario.getTreinos().remove(treino);
                return usuarioRepository.save(usuario).getTreinos();
            }else {
                throw new ElementoNaoEncontradoException("Esse treino informado não foi encontrado no sistema.");
            }
        }else {
            throw new ElementoNaoEncontradoException("Esse usuario informado não foi encontrado no sistema.");
        }
    }


}
