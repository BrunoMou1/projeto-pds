package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Conquista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column
    private String nome;

    @Column
    private String status;

    @ManyToMany
    @JoinTable(
            name = "conquista_treino",
            joinColumns = @JoinColumn(name = "conquista_id"),
            inverseJoinColumns = @JoinColumn(name = "treino_id")
    )
    private List<Treino> treinos;

    public Conquista(String nome, String status, List<Treino> treinos) {
        this.nome = nome;
        this.status = status;
        this.treinos = treinos;
    }

    public Conquista() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }
}
