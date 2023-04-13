package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Checkin;
import com.fitTracker.fitTracker.Models.Usuario;

import java.util.List;

public interface CheckinService {
    public List<Checkin> findCheckinsByPessoa(Usuario usuario);
}
