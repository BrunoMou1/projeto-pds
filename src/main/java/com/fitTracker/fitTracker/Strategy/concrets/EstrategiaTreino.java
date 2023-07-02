package com.fitTracker.fitTracker.Strategy.concrets;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Models.Treino;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import com.fitTracker.fitTracker.Util.RepositoryNullException;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaTreino implements EstrategiaAtividade {

    private List<GenericRepository> listGenericRepository;

    public EstrategiaTreino(){
        listGenericRepository = new ArrayList<>();
    }

    @Override
    public void validar(Atividade atividade) {
        Treino treino = (Treino) atividade;

        if(treino.getDescricao() == null || treino.getDescricao().trim().equals("")){
            throw new RegraNegocioException("Informe uma descrição válida.");
        }

        if(treino.getNome() == null || treino.getNome().trim().equals("")){
            throw new RegraNegocioException("Informe um nome válido.");
        }

        if(treino.getStatus() == null || treino.getStatus().trim().equals("")){
            throw new RegraNegocioException("Informe um status válido");
        }

        if(treino.getPontuacao() < 0){
            throw new RegraNegocioException("Informe uma pontuacao válida");
        }
    }

    @Override
    public Atividade create(Atividade atividade) {

        AtividadeRepository treinoRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof AtividadeRepository){
                treinoRepository = (AtividadeRepository) obj;
            }
        }

        if(treinoRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de AtividadeRepository na list de generic repositories");
        }

        Treino treino = (Treino) atividade;

        return treinoRepository.save(treino);
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
