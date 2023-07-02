package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Matricula;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatriculaRepository extends GenericRepository<Matricula, Long> {

    public List<Matricula> findByUsuarioId(Long id);

    @Modifying
    @Transactional
    @Query("update matricula m set m.status = :status where m.id = :id")
    public int updateStatus(@Param("status") String status, @Param("id") Long id);
}
