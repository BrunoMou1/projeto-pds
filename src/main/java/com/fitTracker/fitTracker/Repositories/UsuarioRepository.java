package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.AtividadeIdiomas;
import com.fitTracker.fitTracker.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends GenericRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}