package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.CheckinRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.CheckinService;
import com.fitTracker.fitTracker.Util.CheckinJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Checkin save(Checkin checkin, Long id) {
        Usuario user = usuarioRepository.findById(id).get();
        int countUserCheckinsToday = checkinRepository.countCheckinByDataAndUsuario(new Date(),
                user);
        if(countUserCheckinsToday > 0){
            throw new CheckinJaExisteException("Você ja fez o checkin hoje!");
        }
        checkin.setData(new Date());
        checkin.setHora(new Time(new Date().getTime()));
        checkin.setUsuario(user);
        return checkinRepository.save(checkin);
    }

    public List<Checkin> findCheckinsByUsuario(Long id) { return checkinRepository.findByUsuarioId(id);}
}
