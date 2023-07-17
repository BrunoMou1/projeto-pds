package com.fitTracker.fitTracker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Column
    private String password;

    @OneToOne
    private Pessoa pessoa;

    @NotNull
    @Column
    private int pontos;

    @NotNull
    @Column
    private double multiplicador;


    @ManyToMany
    @JoinTable(name = "usuario_avaliacao", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name="avaliacao_id"))
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "usuario_recompensa", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "recompensa_id"))
    private List<Recompensa> historicoRecompensas = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String username, String password, int pontos, double multiplicador) {
        this.username = username;
        this.password = password;
        this.pontos = pontos;
        this.multiplicador = multiplicador;
    }

    public Usuario(String username, String password, Set<Role> roles, int pontos,
                   double multiplicador) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.pontos = pontos;
        this.multiplicador = multiplicador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void setHistoricoRecompensas(List<Recompensa> historicoRecompensas) {
        this.historicoRecompensas = historicoRecompensas;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public void setMultiplicador(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public List<Recompensa> getHistoricoRecompensas() {
        return historicoRecompensas;
    }
}
