package com.fitTracker.fitTracker.Controllers;

import com.fitTracker.fitTracker.Models.Pagamento;
import com.fitTracker.fitTracker.Service.impl.MatriculaServiceImpl;
import com.fitTracker.fitTracker.Service.impl.PagamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PagamentoController {
    @Autowired
    private PagamentoServiceImpl pagamentoService;

    @PostMapping("/pagamento")
    public Pagamento create(@RequestBody Pagamento pagamento) {
        try {
            return pagamentoService.save(pagamento);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/pagamento/{id}")
    public Pagamento findById(Long id) {
        try {
            return pagamentoService.findById(id).get();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/pagamento/matricula/{id}")
    public Iterable<Pagamento> findByMatriculaId(Long id) {
        try {
            return pagamentoService.listByMatriculaId(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
