package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Plano;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    Optional<Plano> findByNome(String nome);

    @Modifying
    @Transactional
    @Query("update plano p set p.nome = :nome, p.valor = :valor where p.id = :id")
    public int updatePlano(@Param("nome") String nome, @Param("valor") String valor, @Param("id") Long id);
}