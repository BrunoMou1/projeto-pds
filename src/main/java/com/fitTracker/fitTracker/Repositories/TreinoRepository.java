package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Treino;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    public List<Treino> findByNivelId(Long nivelId);
}
