package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Models.Avaliacao;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.AtividadeService;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Atividade createAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade) {

        Avaliacao avaliacao = (Avaliacao) atividade;

        validarAtividade(avaliacao, estrategiaAtividade);

        return estrategiaAtividade.create(avaliacao);

    }

    @Override
    public void deleteAtividade(Long atividadeId) {
        if(atividadeRepository.findById(atividadeId).isPresent()){
            atividadeRepository.deleteById(atividadeId);
        }else {
            throw new ElementoNaoEncontradoException("Essa atividade informada não foi encontrado no sistema");
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
    public void validarAtividade(Atividade atividade, EstrategiaAtividade estrategiaAtividade){

        estrategiaAtividade.addGenericRepository(atividadeRepository);

        estrategiaAtividade.validar(atividade);
    }
}
