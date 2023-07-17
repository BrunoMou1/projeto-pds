package com.fitTracker.fitTracker.Service.impl;

import com.fitTracker.fitTracker.Models.Frequencia;
import com.fitTracker.fitTracker.Models.Usuario;
import com.fitTracker.fitTracker.Repositories.FrequenciaRepository;
import com.fitTracker.fitTracker.Repositories.UsuarioRepository;
import com.fitTracker.fitTracker.Service.FrequenciaService;
import com.fitTracker.fitTracker.Strategy.EstrategiaFrequencia;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FrequenciaServiceImpl implements FrequenciaService {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Frequencia save(Frequencia frequencia, Long id, EstrategiaFrequencia estrategia) {
        estrategia.addGenericRepository(frequenciaRepository);

        Optional<Usuario> userOP = usuarioRepository.findById(id);
        if(userOP.isEmpty()){
            throw new ElementoNaoEncontradoException("O usuário informado não existe");
        }
        Usuario user = userOP.get();

        frequencia.setUsuario(user);

        Frequencia freq = estrategia.gerarFrequencia(frequencia);

        return frequenciaRepository.save(freq);
    }

    @Override
    public List<Frequencia> findFrequenciaByUsuarioId(Long id) {

        if(usuarioRepository.findById(id).isEmpty()){
            throw new ElementoNaoEncontradoException("O usuário informado não existe");
        }

        return frequenciaRepository.findByUsuarioId(id);
    }
}
