package com.fitTracker.fitTracker.Strategy.concrets;

import com.fitTracker.fitTracker.Models.Frequencia;
import com.fitTracker.fitTracker.Repositories.FrequenciaRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;
import com.fitTracker.fitTracker.Strategy.EstrategiaFrequencia;
import com.fitTracker.fitTracker.Util.CheckinJaExisteException;
import com.fitTracker.fitTracker.Util.RepositoryNullException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstrategiaFreq implements EstrategiaFrequencia {

    private List<GenericRepository> listGenericRepository;

    public EstrategiaFreq(){
        listGenericRepository = new ArrayList<>();
    }

    @Override
    public Frequencia gerarFrequencia(Frequencia frequencia) {

        if(frequencia.getData() == null){
            frequencia.setData(new Date());
        }

        validar(frequencia);

        return frequencia;
    }

    @Override
    public void validar(Frequencia frequencia) {

        FrequenciaRepository frequenciaRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof FrequenciaRepository){
                frequenciaRepository = (FrequenciaRepository) obj;
            }
        }

        if(frequenciaRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de FrequenciaRepository na list de generic repositories");
        }

        List<Frequencia> listFreqUser = frequenciaRepository.findByUsuarioId(frequencia.getUsuario().getId());

        listFreqUser.forEach(chekin -> {
            if(chekin.getData().toString().contains(frequencia.getData().toString())){
                throw new CheckinJaExisteException("Você já tem uma frequencia nesse dia");
            }
        });
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
