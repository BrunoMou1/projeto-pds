package com.fitTracker.fitTracker.Strategy.concrets;

import com.fitTracker.fitTracker.Models.Atividade;
import com.fitTracker.fitTracker.Models.Avaliacao;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.AtividadeRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Strategy.EstrategiaRecompensa;
import com.fitTracker.fitTracker.Util.RepositoryNullException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EstrategiaRecompensaEscola implements EstrategiaRecompensa {

    private List<GenericRepository> listGenericRepository;

    public EstrategiaRecompensaEscola(){
        listGenericRepository = new ArrayList<>();
    }

    @Override
    public void gerarPontuacaoUsuario(Usuario usuario) {

        AtividadeRepository atividadeRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof AtividadeRepository){
                atividadeRepository = (AtividadeRepository) obj;
            }
        }

        if(atividadeRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de AtividadeRepository na list de generic repositories");
        }

        addPoints(usuario);
    }

    @Override
    public void addGenericRepository(GenericRepository genericRepository){
        listGenericRepository.add(genericRepository);
    }

    @Override
    public void clearListGenericRepository(){
        listGenericRepository.clear();
    }

    private void addPoints(Usuario user){

        UsuarioRepository usuarioRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof UsuarioRepository){
                usuarioRepository = (UsuarioRepository) obj;
            }
        }

        if(usuarioRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de UsuarioRepository na list de generic repositories");
        }

        List<Avaliacao> listAvaliacoes = user.getAvaliacoes();

        listAvaliacoes.forEach(avaliacao -> {
            user.setPontos(user.getPontos() + avaliacao.getPontuacao());
        });

        usuarioRepository.save(user);
    }
}
