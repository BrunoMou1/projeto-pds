package com.fitTracker.fitTracker.Strategy.concrets;

import com.fitTracker.fitTracker.Models.*;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.FrequenciaRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Strategy.EstrategiaAtividade;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import com.fitTracker.fitTracker.Util.RepositoryNullException;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaAtividadeIdiomas implements EstrategiaAtividade {

    private List<GenericRepository> listGenericRepository;

    public EstrategiaAtividadeIdiomas() {
        listGenericRepository = new ArrayList<>();
    }

    @Override
    public void validar(Atividade atividade){
        AtividadeIdiomas atividadeIdiomas = (AtividadeIdiomas) atividade;

        if(atividadeIdiomas.getDescricao() == null || atividadeIdiomas.getDescricao().trim().equals("")){
            throw new RegraNegocioException("Informe uma descrição válida.");
        }

        if(atividadeIdiomas.getNome() == null || atividadeIdiomas.getNome().trim().equals("")){
            throw new RegraNegocioException("Informe um nome válido.");
        }

        if(atividadeIdiomas.getPontuacao() < 0){
            throw new RegraNegocioException("Informe uma pontuacao válida");
        }

        if(atividadeIdiomas.getQuestoes() == null){
            throw new RegraNegocioException("Informe uma lista de questões válida");
        }
    }

    @Override
    public Atividade create(Atividade atividade) {

        AtividadeRepository atividadeIdiomasRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof AtividadeRepository){
                atividadeIdiomasRepository = (AtividadeRepository) obj;
            }
        }

        if(atividadeIdiomasRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de AtividadeRepository na list de generic repositories");
        }

        AtividadeIdiomas atividadeIdiomas = (AtividadeIdiomas) atividade;

        return atividadeIdiomasRepository.save(atividadeIdiomas);
    }

    @Override
    public List<String> checkAnswer(AtividadeIdiomas atividadeIdiomas, Usuario user, List<String> responses){

        UsuarioRepository usuarioRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof UsuarioRepository){
                usuarioRepository = (UsuarioRepository) obj;
            }
        }

        if(usuarioRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de UsuarioRepository na lista de " +
                    "generic repositories");
        }

        List<Questao> questoes = atividadeIdiomas.getQuestoes();
        List<String> gabarito = new ArrayList<>();
        int qntRespostasCertas = 0;

        for(int i = 0; i < questoes.size(); i++){
            String respostaCorreta = questoes.get(i).getRespostaCorreta();
            String userResponse = responses.get(i);

            if(respostaCorreta.equals(userResponse)){
                qntRespostasCertas++;
                gabarito.add("Acertou!");
            } else {
                gabarito.add("Errou...");
            }
        }
        if(qntRespostasCertas >= Math.round(questoes.size()) * 0.7){
            List<AtividadeIdiomas> listaAtividadesFeitas = user.getAtividades();
            listaAtividadesFeitas.add(atividadeIdiomas);
            user.setAtividades(listaAtividadesFeitas);
            user.setPontos(user.getPontos() + atividadeIdiomas.getPontuacao());
            usuarioRepository.save(user);
        }

        return gabarito;
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
