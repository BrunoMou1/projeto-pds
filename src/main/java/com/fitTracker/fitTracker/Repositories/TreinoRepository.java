package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    public List<Treino> findByNivelId(Long nivelId);

}
