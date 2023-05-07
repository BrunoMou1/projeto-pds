package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Nivel;
import com.fitTracker.fitTracker.Models.Treino;
import com.fitTracker.fitTracker.Repositories.NivelRepository;
import com.fitTracker.fitTracker.Repositories.TreinoRepository;
import com.fitTracker.fitTracker.Service.TreinoService;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinoServiceImpl implements TreinoService {

    @Autowired
    NivelRepository nivelRepository;

    @Autowired
    TreinoRepository treinoRepository;

    @Override
    public Nivel createNivel(Nivel nivel) {
        return nivelRepository.save(nivel);
    }

    @Override
    public Treino createTreino(Treino treino) {
        if(nivelRepository.findById(treino.getNivel().getId()).isPresent()) {
            return treinoRepository.save(treino);
        } else {
            throw new ElementoNaoEncontradoException("O nivel informado no treino não foi encontrado!");
        }
    }

    @Override
    public List<Treino> findTreinoByNivelId(Long nivelId) {
        if(nivelRepository.findById(nivelId).isPresent()){
            return treinoRepository.findByNivelId(nivelId);
        } else {
            throw new ElementoNaoEncontradoException("O nivel informado não foi encontrado!");
        }
    }

    @Override
    public List<Nivel> findAllNivel() {
        return nivelRepository.findAll();
    }

    @Override
    public void deleteTreino(Long treinoId) {
        if(treinoRepository.findById(treinoId).isPresent()){
            treinoRepository.deleteById(treinoId);
        }else {
            throw new ElementoNaoEncontradoException("Esse treino informado não foi encontrado no sistema");
        }
    }

    @Override
    public Treino updateTreino(Treino treino) {
        if(treinoRepository.findById(treino.getId()).isPresent()){
            return treinoRepository.save(treino);
        }else {
            throw new ElementoNaoEncontradoException("Esse treino informado não foi encontrado no sistema");
        }
    }

    @Override
    public Nivel updateNivel(Nivel nivel) {
        if(nivelRepository.findById(nivel.getId()).isPresent()){
            return nivelRepository.save(nivel);
        }else {
            throw new ElementoNaoEncontradoException("Esse treino informado não foi encontrado no sistema");
        }
    }

    @Override
    public void deleteNivel(Long nivelId) {
        if(nivelRepository.findById(nivelId).isEmpty()) {
            throw new ElementoNaoEncontradoException("Esse nivel informado não foi encontrado no sistema");
        }else if(!treinoRepository.findByNivelId(nivelId).isEmpty()){
            throw new ElementoExisteException("Exitem treinos cadastrados para esse nivel, se quiser deletar esse nivel, exclui os treino vinculados a ele.");
        }else {
            nivelRepository.deleteById(nivelId);
        }
    }


}
