package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="data_pagamento")
    private Date data_pagamento;

    @NotBlank
    @Column(name="valor")
    private String valor;

    @NotBlank
    @Column(name="forma_pagamento")
    private String forma_pagamento;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    private Matricula matricula;

    public Pagamento() {
    }

    public Pagamento(Date data_pagamento, String valor, String forma_pagamento, Matricula matricula) {
        this.data_pagamento = data_pagamento;
        this.valor = valor;
        this.forma_pagamento = forma_pagamento;
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public String getValor() {
        return valor;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

}
