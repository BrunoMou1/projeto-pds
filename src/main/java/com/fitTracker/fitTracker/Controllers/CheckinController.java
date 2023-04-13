package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.impl.CheckInServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private CheckInServiceImpl checkInService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Checkin createCheckin(@PathVariable("id") Long id, @RequestBody Checkin checkin){
        Usuario user = usuarioRepository.findById(id).get();
        checkin.setUsuario(user);
        checkin.setData(new Date());
        checkin.setHora(new Time(new Date().getTime()));
        return checkInService.save(checkin);
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public List<Checkin> listCheckinsByPessoa(@PathVariable("id") Long id, @RequestBody Usuario usuario){

        return checkInService.findCheckinsByPessoa(usuario);
    }
}
