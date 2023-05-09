package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Recompensa;

import java.util.List;

public interface RecompensaService {
    public List<Recompensa> listAll();

    public void redeemById(Long recompensaId, Long userId);
}
