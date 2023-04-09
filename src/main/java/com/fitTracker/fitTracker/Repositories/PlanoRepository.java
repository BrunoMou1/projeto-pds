package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    Optional<Plano> findByNome(String nome);
}