package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Avaliacao extends Atividade{

    @NotBlank
    @Column
    private String status;

    @Column
    private int pontuacao;

    public Avaliacao() {
    }

    public Avaliacao(String nome, String descricao, int pontuacao) {
        super(nome,descricao);
        this.pontuacao = pontuacao;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
