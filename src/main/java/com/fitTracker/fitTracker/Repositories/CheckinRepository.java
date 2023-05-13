package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    List<Checkin> findByUsuarioId(Long id);

    int countCheckinByDataAndUsuario(Date data, Usuario user);
    //@Modifying
    //@Transactional
    //@Query("update usuario  u set u.historicoRecompensas = CONCAT_WS(',', u.historicoRecompensas, :checkin) where u
    // .id = :userId)
    //public int updateHistoricoCheckins(@Param("checkin") Checkin checkin, @Param("usuario") Long userId);
}
