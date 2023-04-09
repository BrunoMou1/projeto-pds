package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.ERole;
import com.fitTracker.fitTracker.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
