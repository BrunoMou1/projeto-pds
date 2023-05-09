package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Recompensa;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.RecompensaRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.RecompensaService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class RecompensaServiceImpl implements RecompensaService {

    @Autowired
    private RecompensaRepository recompensaRepository;

    private UsuarioRepository usuarioRepository;

    public Recompensa save(Recompensa recompensa) {
        if(recompensaRepository.exists(Example.of(recompensa))){
            throw new ElementoExisteException("Já existe um produto com essas informações");
        }
        return recompensaRepository.save(recompensa);
    }

    public void deleteById(Long id){
        if(recompensaRepository.findById(id).isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhuma recompensa com esse id!");
        }
        recompensaRepository.deleteById(id);
    }

    public Optional<Recompensa> findById(Long id){
        Optional<Recompensa> recompensa = recompensaRepository.findById(id);

        if(recompensa.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhuma recompensa com esse id!");
        }

        return recompensa;
    }

    public List<Recompensa> listAll(){
        return recompensaRepository.findAll();
    }

    public void redeemById(Long recompensaId, Long userId){
        Optional<Recompensa> recompensa = recompensaRepository.findById(recompensaId);
        Optional<Usuario> usuario = usuarioRepository.findById(userId);

        if(usuario.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhum usuário com esse id!");
        }
        if(recompensa.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhuma recompensa com esse id!");
        }
        //subir a recompensa no histórico do usuario
    }
}
