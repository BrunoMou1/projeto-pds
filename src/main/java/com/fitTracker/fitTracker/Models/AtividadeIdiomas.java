package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AtividadeIdiomas extends Atividade{

    @OneToMany
    private List<Questao> questoes = new ArrayList<>();

    @Column
    private int pontuacao;

    public AtividadeIdiomas() {
    }

    public AtividadeIdiomas(String nome, String descricao, Nivel nivel, List<Questao> questoes, int pontuacao) {
        super(nome, descricao, nivel);
        this.questoes = questoes;
        this.pontuacao = pontuacao;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
