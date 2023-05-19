package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Plano;
import com.fitTracker.fitTracker.Repositories.PlanoRepository;
import com.fitTracker.fitTracker.Service.PlanoService;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoServiceImpl implements PlanoService {

    @Autowired
    PlanoRepository planoRepository;

    @Override
    public Plano create(Plano plano) {
        validar(plano);

        return planoRepository.save(plano);
    }

    @Override
    public Optional<Plano> findByNome(String nome) {
        return planoRepository.findByNome(nome);
    }

    @Override
    public List<Plano> findAll() {
        return planoRepository.findAll();
    }

    @Override
    public Optional<Plano> findById(Long id) {
        Optional<Plano> plano = planoRepository.findById(id);
        if(plano.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado um plano com esse id!");
        }

        return plano;
    }

    @Override
    public void update(Plano plano) {
        if(planoRepository.findById(plano.getId()).isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado um plano com esse id!");
        }

        validar(plano);

        planoRepository.updatePlano(plano.getNome(), plano.getValor(), plano.getId());
    }

    @Override
    public void validar(Plano plano){
        if(plano.getNome() == null || plano.getNome().trim().equals("")){
            throw new RegraNegocioException("Informe um nome válido");
        }
        if(plano.getValor() == null || plano.getValor().trim().equals("")) {
            throw new RegraNegocioException("Informe um valor válido");
        }
    }
}
