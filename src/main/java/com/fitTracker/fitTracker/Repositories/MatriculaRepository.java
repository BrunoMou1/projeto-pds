package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    public Optional<Matricula> findByUsuarioId(Long id);
}
