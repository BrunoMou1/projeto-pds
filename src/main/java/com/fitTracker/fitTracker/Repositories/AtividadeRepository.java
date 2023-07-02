package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends GenericRepository<Atividade, Long> {

    public List<Atividade> findByNivelId(Long nivelId);
}
