package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Matricula;
import com.fitTracker.fitTracker.Repositories.MatriculaRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.MatriculaService;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import com.fitTracker.fitTracker.Util.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Matricula save(Matricula matricula) {

        validar(matricula);

        if(usuarioRepository.findById(matricula.getUsuario().getId()).isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhum usuario com esse id!");
        }

        return matriculaRepository.save(matricula);
    }

    @Override
    public void deleteById(Long id) {

        if(matriculaRepository.findById(id).isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrada nenhuma matricula com esse id!");
        }

        matriculaRepository.deleteById(id);
    }

    @Override
    public Optional<Matricula> findById(Long id) {

        Optional<Matricula> matricula = matriculaRepository.findById(id);

        if(matricula.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrada nenhuma matricula com esse id!");
        }

        return matricula;
    }

    @Override
    public List<Matricula> listAll() {
        return matriculaRepository.findAll();
    }

    @Override
    public List<Matricula> listByUsuarioId(Long id){

        if(usuarioRepository.findById(id).isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado um usuario com esse id!");
        }

        List<Matricula> matricula = matriculaRepository.findByUsuarioId(id);

        if(matricula.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrada nenhuma matricula desse usuario!");
        }

        return matricula;
    }

    @Override
    public void enableOrDisableStatusById(Long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);

        if(matricula.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrada nenhuma matricula com esse id!");
        }
        if(matricula.get().getStatus().equals("desativo")) {
            matriculaRepository.updateStatus("ativo", id);
        }else {
            matriculaRepository.updateStatus("desativo", id);
        }
    }


    @Override
    public void validar(Matricula matricula){
        if(matricula.getStatus() == null || matricula.getStatus().trim().equals("")){
            throw new RegraNegocioException("Informe um status válido.");
        }
        if(matricula.getValor() == null || matricula.getValor().trim().equals("")){
            throw new RegraNegocioException("Informe um valor válido.");
        }
        if(matricula.getDataVencimento() == null || matricula.getDataVencimento().trim().equals("")){
            throw new RegraNegocioException("Informe uma data de vencimento válida.");
        }
    }

}
