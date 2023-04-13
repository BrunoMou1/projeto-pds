package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.impl.CheckInServiceImpl;
import com.fitTracker.fitTracker.Service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private CheckInServiceImpl checkInService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Checkin createCheckin(@PathVariable("id") Long id, @RequestBody Checkin checkin){
        Usuario user = usuarioService.findById(id).get();
        checkin.setData(new Date());
        checkin.setHora(new Time(new Date().getTime()));
        checkin.setUsuario(user);
        return checkInService.save(checkin);
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<Checkin> listCheckinsByUsuario(@PathVariable("id") Long id){
        return checkInService.findCheckinsByUsuario(id);
    }
}
