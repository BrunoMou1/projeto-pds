package com.fitTracker.fitTracker.Strategy.concrets;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Models.Avaliacao;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import com.fitTracker.fitTracker.Util.RepositoryNullException;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaAvaliacao implements EstrategiaAtividade {

    private List<GenericRepository> listGenericRepository;

    public EstrategiaAvaliacao(){
        listGenericRepository = new ArrayList<>();
    }

    @Override
    public void validar(Atividade atividade) {
        Avaliacao avaliacao = (Avaliacao) atividade;

        if(avaliacao.getDescricao() == null || avaliacao.getDescricao().trim().equals("")){
            throw new RegraNegocioException("Informe uma descrição válida.");
        }

        if(avaliacao.getNome() == null || avaliacao.getNome().trim().equals("")){
            throw new RegraNegocioException("Informe um nome válido.");
        }

        if(avaliacao.getStatus() == null || avaliacao.getStatus().trim().equals("")){
            throw new RegraNegocioException("Informe um status válido");
        }

        if(avaliacao.getPontuacao() < 0){
            throw new RegraNegocioException("Informe uma pontuacao válida");
        }
    }

    @Override
    public Atividade create(Atividade atividade) {

        AtividadeRepository avaliacaoRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof AtividadeRepository){
                avaliacaoRepository = (AtividadeRepository) obj;
            }
        }

        if(avaliacaoRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de AtividadeRepository na list de generic repositories");
        }

        Avaliacao avaliacao = (Avaliacao) atividade;

        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public void addGenericRepository(GenericRepository genericRepository){
        listGenericRepository.add(genericRepository);
    }

    @Override
    public void clearListGenericRepository(){
        listGenericRepository.clear();
    }

}
