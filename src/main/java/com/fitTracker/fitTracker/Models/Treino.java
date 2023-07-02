package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Treino extends Atividade{

    @NotBlank
    @Column
    private String status;

    @Column
    private int pontuacao;

    public Treino () {
    }

    public Treino (String nome, String descricao, int pontuacao, Nivel nivel) {
        super(nome,descricao,nivel);
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
