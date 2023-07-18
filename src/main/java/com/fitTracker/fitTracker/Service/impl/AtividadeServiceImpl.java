package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.*;
import com.fitTracker.fitTracker.Repositories.NivelRepository;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.QuestaoRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.AtividadeService;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;
import com.fitTracker.fitTracker.Util.ElementoExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    NivelRepository nivelRepository;

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    QuestaoRepository questaoRepository;


    @Override
    public Nivel createNivel(Nivel nivel) {

        validarNivel(nivel);

        return nivelRepository.save(nivel);
    }

    @Override
    public Atividade createAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade) {

        AtividadeIdiomas atividadeIdiomas = (AtividadeIdiomas) atividade;

        validarAtividade(atividadeIdiomas, estrategiaAtividade);

        if(nivelRepository.findById(atividade.getNivel().getId()).isPresent()) {
            estrategiaAtividade.addGenericRepository(atividadeRepository);
            estrategiaAtividade.addGenericRepository(questaoRepository);
            return estrategiaAtividade.create(atividadeIdiomas);
        } else {
            throw new ElementoNaoEncontradoException("O nivel informado na atividade não foi encontrado!");
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
    public Atividade findAtividadeByAtividadeId(Long atividadeId) {
        if(atividadeRepository.findById(atividadeId).isPresent()){
            return atividadeRepository.findById(atividadeId).get();
        } else {
            throw new ElementoNaoEncontradoException("A atividade informada não foi encontrada!");
        }
    }

    @Override
    public List<String> checkIfCorrectAnswer(Atividade atividade, Long userId, List<String> responses,
                                             EstrategiaAtividade estrategiaAtividade){
        AtividadeIdiomas atividadeIdiomas = (AtividadeIdiomas) atividade;

        Optional<Usuario> userOp = usuarioRepository.findById(userId);

        if(!userOp.isPresent()){
            throw new ElementoNaoEncontradoException("O usuário informado não foi encontrado no sistema");
        }

        Usuario user = userOp.get();

        estrategiaAtividade.addGenericRepository(atividadeRepository);
        estrategiaAtividade.addGenericRepository(usuarioRepository);

        List<String> gabarito = estrategiaAtividade.checkAnswer(atividadeIdiomas, user, responses);

        return gabarito;
    }

    @Override
    public List<Nivel> findAllNivel() {
        return nivelRepository.findAll();
    }

    @Override
    public void deleteAtividade(Long atividadeId) {
        if(atividadeRepository.findById(atividadeId).isPresent()){
            atividadeRepository.deleteById(atividadeId);
        }else {
            throw new ElementoNaoEncontradoException("Essa atividade não foi encontrada no sistema");
        }
    }

    @Override
    public Atividade updateAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade) {

        validarAtividade(atividade, estrategiaAtividade);

        if(atividadeRepository.findById(atividade.getId()).isPresent()){
            return estrategiaAtividade.create(atividade);
        }else {
            throw new ElementoNaoEncontradoException("Essa atividade informada não foi encontrado no sistema");
        }
    }

    @Override
    public Nivel updateNivel(Nivel nivel) {

        validarNivel(nivel);

        if(nivelRepository.findById(nivel.getId()).isPresent()){
            return nivelRepository.save(nivel);
        }else {
            throw new ElementoNaoEncontradoException("Essa atividade informada não foi encontrada no sistema");
        }
    }

    @Override
    public void deleteNivel(Long nivelId) {
        if(nivelRepository.findById(nivelId).isEmpty()) {
            throw new ElementoNaoEncontradoException("Esse nivel informado não foi encontrado no sistema");
        }else if(!atividadeRepository.findByNivelId(nivelId).isEmpty()){
            throw new ElementoExisteException("Existem atividades cadastradas para esse nivel, se quiser deletar esse" +
                    " " +
                    "nivel, exclua as atividades vinculadas a ele.");
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
