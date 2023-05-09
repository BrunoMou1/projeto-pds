package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.ERole;
import com.fitTracker.fitTracker.Models.Recompensa;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Service.impl.RecompensaServiceImpl;
import com.fitTracker.fitTracker.Service.impl.UsuarioServiceImpl;
import com.fitTracker.fitTracker.Util.PermissaoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recompensa")
public class RecompensaController {

    @Autowired
    private RecompensaServiceImpl recompensaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Recompensa createRecompensa(@PathVariable("id") Long id, @RequestBody Recompensa recompensa){
        Usuario usuario = usuarioService.findById(id).get();
        if(usuario.getRoles().contains(ERole.ROLE_USER)){
            throw new PermissaoInsuficienteException("Você não tem permissão para cadastrar uma recompensa");
        }
        //pode lançar exceção aqui?

        return recompensaService.save(recompensa);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Recompensa> getAll() {
        return recompensaService.listAll();
    }
}
