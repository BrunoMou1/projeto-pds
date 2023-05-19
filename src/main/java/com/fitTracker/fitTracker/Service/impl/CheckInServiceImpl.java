package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.CheckinRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.CheckinService;
import com.fitTracker.fitTracker.Util.CheckinJaExisteException;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckInServiceImpl implements CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    boolean checkStreak(LocalDate dateToCheck, List<Checkin> listUserCheckinHistory){
        for(Checkin checkinIt : listUserCheckinHistory){
            if(checkinIt.getData().toString().contains(dateToCheck.toString())){
                return true;
            }
        }
        return false;
    }

    void addPoints(Usuario user, boolean inStreak){
        if(inStreak){
            user.setMultiplicador(user.getMultiplicador() * 0.1);
            user.setPontos((int) (user.getPontos() + 10 * user.getMultiplicador()));
        } else {
            user.setMultiplicador(1.0);
            user.setPontos(user.getPontos() + 10);
        }
        usuarioRepository.save(user);
    }



    public Checkin save(Checkin checkin, Long id) {
        LocalDate currentDate = LocalDate.now();
        Optional<Usuario> userOP = usuarioRepository.findById(id);
        if(userOP.isEmpty()){
            throw new ElementoNaoEncontradoException("O usuário informado não existe");
        }
        Usuario user = userOP.get();
        List<Checkin> listCheckinsUser = checkinRepository.findByUsuarioId(user.getId());
        listCheckinsUser.forEach(checkin1 -> {
            if(checkin1.getData().toString().contains(currentDate.toString())){
                throw new CheckinJaExisteException("Você ja fez o checkin hoje!");
            }
        });
        if(currentDate.getDayOfWeek().toString().contains("MONDAY")){
            if (checkStreak(currentDate.minusDays(3), listCheckinsUser)) {
                addPoints(user, true);
            } else {
                addPoints(user, false);
            }
        } else {
            if(checkStreak(currentDate.minusDays(1), listCheckinsUser)){
                addPoints(user, true);
            } else{
                addPoints(user, false);
            }
        }
        checkin.setData(new Date());
        checkin.setHora(new Time(new Date().getTime()));
        checkin.setUsuario(user);
        return checkinRepository.save(checkin);
    }

    public List<Checkin> findCheckinsByUsuario(Long id) { return checkinRepository.findByUsuarioId(id);}
}
