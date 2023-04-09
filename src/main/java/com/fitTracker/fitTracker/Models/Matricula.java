package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "data_vencimento")
    private String data_vencimento;

    @NotBlank
    @Column(name = "valor")
    private String valor;

    @NotBlank
    @Column(name = "status", columnDefinition = "varchar(255) default 'inativo'")
    private String status;

    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;

    public Matricula() {
    }

    public Matricula(String data_vencimento, String valor, String status, Usuario usuario, List<Pagamento> pagamentos, Plano plano) {
        this.data_vencimento = data_vencimento;
        this.valor = valor;
        this.status = status;
        this.usuario = usuario;
        this.pagamentos = pagamentos;
        this.plano = plano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

}
