package com.fitTracker.fitTracker.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "checkin")
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "data")
    private Date data;

    @NotBlank
    @Column(name = "hora")
    private Time hora;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Checkin() {
    }

    public Checkin(Date data, Time hora, Usuario usuario) {
        this.data = data;
        this.hora = hora;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
