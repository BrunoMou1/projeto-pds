package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "recompensa")
public class Recompensa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String nome;

    @NotBlank
    @Column
    private String descricao;

    @NotNull
    @Column
    private int valor;

    public Recompensa() {

    }

    public Recompensa(String nome, String descricao, int valor){
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
