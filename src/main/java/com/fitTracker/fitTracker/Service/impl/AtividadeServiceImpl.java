package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Models.Nivel;
import com.fitTracker.fitTracker.Models.Treino;
import com.fitTracker.fitTracker.Repositories.NivelRepository;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.AtividadeService;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;
import com.fitTracker.fitTracker.Strategy.concrets.EstrategiaTreino;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    NivelRepository nivelRepository;

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public Nivel createNivel(Nivel nivel) {

        validarNivel(nivel);

        return nivelRepository.save(nivel);
    }

    @Override
    public Atividade createAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade) {

        Treino treino = (Treino) atividade;

        validarAtividade(treino, estrategiaAtividade);

        if(nivelRepository.findById(atividade.getNivel().getId()).isPresent()) {
            return estrategiaAtividade.create(treino);
        } else {
            throw new ElementoNaoEncontradoException("O nivel informado no treino não foi encontrado!");
        }
    }

    @Override
    public List<Atividade> findAtividadeByNivelId(Long nivelId) {
        if(nivelRepository.findById(nivelId).isPresent()){
            return atividadeRepository.findByNivelId(nivelId);
        } else {
            throw new ElementoNaoEncontradoException("O nivel informado não foi encontrado!");
        }
    }

    @Override
    public List<Nivel> findAllNivel() {
        return nivelRepository.findAll();
    }

    @Override
    public void deleteAtividade(Long atividadeId) {
        if(atividadeRepository.findById(atividadeId).isPresent()){

            if(!usuarioRepository.findUsuarioByTreinosId(atividadeId).isEmpty()){
                throw new RegraNegocioException("Esse treino não pode ser apagado, pois existe ligação com algum usuario.");
            }

            atividadeRepository.deleteById(atividadeId);
        }else {
            throw new ElementoNaoEncontradoException("Esse treino informado não foi encontrado no sistema");
        }
    }

    @Override
    public Atividade updateAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade) {

        validarAtividade(atividade, estrategiaAtividade);

        if(atividadeRepository.findById(atividade.getId()).isPresent()){
            return estrategiaAtividade.create(atividade);
        }else {
            throw new ElementoNaoEncontradoException("Esse treino informado não foi encontrado no sistema");
        }
    }

    @Override
    public Nivel updateNivel(Nivel nivel) {

        validarNivel(nivel);

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
        }else if(!atividadeRepository.findByNivelId(nivelId).isEmpty()){
            throw new ElementoExisteException("Exitem treinos cadastrados para esse nivel, se quiser deletar esse nivel, exclui os treino vinculados a ele.");
        }else {
            nivelRepository.deleteById(nivelId);
        }
    }


    @Override
    public void validarNivel(Nivel nivel){
        if(nivel.getDescricao() == null || nivel.getDescricao().trim().equals("")){
            throw new RegraNegocioException("Informe uma descrição válida.");
        }

        if(nivel.getNome() == null || nivel.getNome().trim().equals("")){
            throw new RegraNegocioException("Informe um nome válido.");
        }
    }

    @Override
    public void validarAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade){

        estrategiaAtividade.addGenericRepository(atividadeRepository);

        estrategiaAtividade.validar(atividade);
    }
}
