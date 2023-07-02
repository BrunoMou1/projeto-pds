package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrequenciaRepository extends GenericRepository<Frequencia, Long> {
    List<Frequencia> findByUsuarioId(Long id);

}
