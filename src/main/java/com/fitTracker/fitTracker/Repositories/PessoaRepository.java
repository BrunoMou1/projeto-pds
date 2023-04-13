package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public Optional<Pessoa> findByCpf(String cpf);

    @Modifying
    @Transactional
    @Query("update pessoa p set p.telefone = :telefone where p.id = :id")
    public int updateTelefone(@Param("telefone") String telefone, @Param("id") Long id);
}
