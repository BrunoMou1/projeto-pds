package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
}
