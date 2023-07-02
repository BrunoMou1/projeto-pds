package com.fitTracker.fitTracker.Strategy.concrets;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Frequencia;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.FrequenciaRepository;
import com.fitTracker.fitTracker.Repositories.GenericRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Strategy.EstrategiaRecompensa;
import com.fitTracker.fitTracker.Util.RepositoryNullException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EstrategiaRecompensaAcademia implements EstrategiaRecompensa {

    private List<GenericRepository> listGenericRepository;

    public EstrategiaRecompensaAcademia(){
        listGenericRepository = new ArrayList<>();
    }

    @Override
    public void gerarPontuacaoUsuario(Usuario usuario) {

        LocalDate currentDate = LocalDate.now();

        FrequenciaRepository frequenciaRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof FrequenciaRepository){
                frequenciaRepository = (FrequenciaRepository) obj;
            }
        }

        if(frequenciaRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de FrequenciaRepository na list de generic repositories");
        }

        List<Frequencia> listCheckinsUser = frequenciaRepository.findAll();

        //Filtragem
        listCheckinsUser = listCheckinsUser.stream()
                .filter(obj -> obj.getUsuario().getId() == usuario.getId())
                .collect(Collectors.toList());

        if(currentDate.getDayOfWeek().toString().contains("MONDAY")){
            if (checkStreak(currentDate.minusDays(3), listCheckinsUser)) {
               addPoints(usuario, true);
            } else {
                addPoints(usuario, false);
            }
        } else {
            if(checkStreak(currentDate.minusDays(1), listCheckinsUser)){
                addPoints(usuario, true);
            } else{
                addPoints(usuario, false);
            }
        }
    }

    @Override
    public void addGenericRepository(GenericRepository genericRepository){
        listGenericRepository.add(genericRepository);
    }

    @Override
    public void clearListGenericRepository(){
        listGenericRepository.clear();
    }

    private boolean checkStreak(LocalDate dateToCheck, List<Frequencia> listUserCheckinHistory){

        List<Checkin> listCheckin = new ArrayList(listUserCheckinHistory);

        for(Checkin checkinIt : listCheckin){
            if(checkinIt.getData().toString().contains(dateToCheck.toString())){
                return true;
            }
        }
        return false;
    }

    private void addPoints(Usuario user, boolean inStreak){

        UsuarioRepository usuarioRepository = null;

        for(GenericRepository obj : listGenericRepository){
            if(obj instanceof UsuarioRepository){
                usuarioRepository = (UsuarioRepository) obj;
            }
        }

        if(usuarioRepository == null){
            throw new RepositoryNullException("Por favor insira uma instancia de UsuarioRepository na list de generic repositories");
        }

        if(inStreak){
            user.setMultiplicador(user.getMultiplicador() * 0.1);
            user.setPontos((int) (user.getPontos() + 10 * user.getMultiplicador()));
        } else {
            user.setMultiplicador(1.0);
            user.setPontos(user.getPontos() + 10);
        }
        usuarioRepository.save(user);
    }
}
