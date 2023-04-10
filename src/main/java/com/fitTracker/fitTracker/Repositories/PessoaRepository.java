package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public Optional<Pessoa> findByCpf(String cpf);
}
