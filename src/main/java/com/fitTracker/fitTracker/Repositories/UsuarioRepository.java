package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    boolean existsByUsername(String username);

    List<Usuario> findUsuarioByTreinosId(Long Id);
}