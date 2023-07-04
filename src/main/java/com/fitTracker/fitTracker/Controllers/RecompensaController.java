package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Recompensa;
import com.fitTracker.fitTracker.Service.impl.RecompensaServiceImpl;
import com.fitTracker.fitTracker.Service.impl.UsuarioServiceImpl;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.PermissaoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recompensa")
public class RecompensaController {

    @Autowired
    private RecompensaServiceImpl recompensaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Recompensa createRecompensa(@RequestBody Recompensa recompensa){
        try{
            return recompensaService.save(recompensa);
        } catch (PermissaoInsuficienteException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage());
        }

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Recompensa> getAll() {
        return recompensaService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecompensaById(@PathVariable Long id){
        try{
            recompensaService.deleteById(id);
        } catch(ElementoNaoEncontradoException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Recompensa> findRecompensaById(@PathVariable Long id){
        try{
            return recompensaService.findById(id);
        } catch(ElementoNaoEncontradoException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PutMapping("/{usuarioId}/{recompensaId}")
    @ResponseStatus(HttpStatus.OK)
    public void redeemRecompensaAndPushInUser(@PathVariable Long usuarioId, @PathVariable Long recompensaId){
        try{
            recompensaService.redeemById(recompensaId, usuarioId);
        } catch(ElementoNaoEncontradoException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }




}
