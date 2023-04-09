package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
}
