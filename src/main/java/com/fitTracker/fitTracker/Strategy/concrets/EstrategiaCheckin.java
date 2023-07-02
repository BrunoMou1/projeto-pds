package com.fitTracker.fitTracker.Strategy.concrets;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Frequencia;
import com.fitTracker.fitTracker.Repositories.FrequenciaRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Strategy.EstrategiaFrequencia;
import com.fitTracker.fitTracker.Util.CheckinJaExisteException;
import com.fitTracker.fitTracker.Util.RepositoryNullException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstrategiaCheckin implements EstrategiaFrequencia {

    private List<GenericRepository> listGenericRepository;

    public EstrategiaCheckin(){
        listGenericRepository = new ArrayList<>();
    }

    @Override
    public Frequencia gerarFrequencia(Frequencia frequencia) {

        Checkin checkin = (Checkin) frequencia;

        if(checkin.getData() == null){
            checkin.setData(new Date());
        }

        if(checkin.getHora() == null) {
            checkin.setHora(new Time(new Date().getTime()));
        }

        validar(frequencia);

        return checkin;
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

        List<Frequencia> listCheckinsUser = frequenciaRepository.findByUsuarioId(frequencia.getUsuario().getId());

        listCheckinsUser.forEach(chekin -> {
            if(chekin.getData().toString().contains(frequencia.getData().toString())){
                throw new CheckinJaExisteException("Você ja fez o checkin hoje!");
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
