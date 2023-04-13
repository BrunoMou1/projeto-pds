package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.CheckinRepository;
import com.fitTracker.fitTracker.Service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInServiceImpl implements CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    public Checkin save(Checkin checkin) { return checkinRepository.save(checkin);}

    public List<Checkin> findCheckinsByUsuario(Long id) { return checkinRepository.findByUsuarioId(id);}
}
