package com.fitTracker.fitTracker.Repositories;

import com.fitTracker.fitTracker.Models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends GenericRepository<Pagamento, Long> {
    List<Pagamento> findByMatriculaId(Long id);
}
