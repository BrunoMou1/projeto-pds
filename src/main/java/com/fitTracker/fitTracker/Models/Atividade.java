package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column
    private String nome;

    @Column
    private String descricao;
    @ManyToMany(mappedBy = "atividades")
    private List<Usuario> usuarios;

    public Atividade(){

    }

    public Atividade(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getUsuarios() {return usuarios;}

    public void setUsuarios(List<Usuario> usuarios) {this.usuarios = usuarios;}

}
