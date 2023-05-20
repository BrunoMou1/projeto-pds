package com.fitTracker.fitTracker.Service.impl;
import com.fitTracker.fitTracker.Models.Matricula;
import com.fitTracker.fitTracker.Models.Pagamento;
import com.fitTracker.fitTracker.Repositories.MatriculaRepository;
import com.fitTracker.fitTracker.Repositories.PagamentoRepository;
import com.fitTracker.fitTracker.Util.ElementoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoServiceImpl {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public PagamentoServiceImpl() {
    }

    public PagamentoServiceImpl(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento save(Pagamento pagamento) {
        Matricula matricula = matriculaRepository.findById(pagamento.getMatricula().getId()).get();
        if(matricula == null){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhuma matricula com esse id!");
        }

        if(matricula.getStatus().equals("Cancelado")){
            throw new ElementoNaoEncontradoException("Não é possível realizar um pagamento para uma matricula cancelada!");
        }

        if(matricula.getStatus().equals("Pago")){
            throw new ElementoNaoEncontradoException("Não é possível realizar um pagamento para uma matricula já paga!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataVencimento = LocalDate.parse(matricula.getDataVencimento(), formatter);
        LocalDate novaDataVencimento = dataVencimento.plusMonths(1);
        String novaDataVencimentoString = novaDataVencimento.format(formatter);
        matricula.setDataVencimento(novaDataVencimentoString);

        return pagamentoRepository.save(pagamento);
    }

    public Optional<Pagamento> findById(Long id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

        if(pagamento.isEmpty()) {
            throw new ElementoNaoEncontradoException("Não foi encontrado um pagamento com esse id!");
        }

        return pagamento;

    }

    public List<Pagamento> listByMatriculaId(Long id) {
        if(matriculaRepository.findById(id).isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhuma matricula com esse id!");
        }

        List<Pagamento> pagamentos = pagamentoRepository.findByMatriculaId(id);

        if(pagamentos.isEmpty()){
            throw new ElementoNaoEncontradoException("Não foi encontrado nenhum pagamento com esse id!");
        }
        return pagamentos;

    }

}
