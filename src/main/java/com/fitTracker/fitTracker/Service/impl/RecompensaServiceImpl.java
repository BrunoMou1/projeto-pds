package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.ERole;
import com.fitTracker.fitTracker.Models.Recompensa;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.RecompensaRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.RecompensaService;
import com.fitTracker.fitTracker.Util.*;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Recompensa save(Recompensa recompensa, Usuario usuario) {
        /*if(recompensaRepository.exists(Example.of(recompensa))){
            throw new ElementoExisteException("Já existe um produto com essas informações");
        }
        if(usuario.getRoles().contains(ERole.ROLE_USER)){
            throw new PermissaoInsuficienteException("Você não tem permissão para cadastrar uma recompensa");
        }*/
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
        Optional<Recompensa> recompensaOp = recompensaRepository.findById(recompensaId);
        Optional<Usuario> usuarioOp = usuarioRepository.findById(userId);
        if(recompensaOp.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhuma recompensa com esse id!");
        }
        if(usuarioOp.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhum usuário com esse id!");
        }

        Recompensa recompensa = recompensaOp.get();
        Usuario usuario = usuarioOp.get();

        if(usuario.getPontos() < recompensa.getValor()){
            throw new PontosInsuficienteException("Você não tem pontos suficientes para resgatar a recompensa");
        }
        if(recompensa.getQuantidade() > 0){
            List<Recompensa> listRecompensas = usuario.getHistoricoRecompensas();
            listRecompensas.add(recompensa);
            usuario.setHistoricoRecompensas(listRecompensas);
            usuario.setPontos(usuario.getPontos() - recompensa.getValor());
            usuarioRepository.save(usuario);
            recompensa.setQuantidade(recompensa.getQuantidade() - 1);
            recompensaRepository.save(recompensa);
        } else {
            throw new RecompensaEsgotadaException("Esta recompensa esgotou");
        }
    }
}
