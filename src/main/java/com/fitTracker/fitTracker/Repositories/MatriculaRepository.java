package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    public List<Matricula> findByUsuarioId(Long id);
}
