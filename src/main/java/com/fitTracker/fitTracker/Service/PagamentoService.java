package com.fitTracker.fitTracker.Service;

import com.fitTracker.fitTracker.Models.Pagamento;

import java.util.List;

public interface PagamentoService {
    public Pagamento save(Pagamento pagamento);

    public Pagamento findById(Long id);

    public List<Pagamento> listByMatriculaId(Long id);


}
