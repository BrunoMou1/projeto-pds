package com.fitTracker.fitTracker.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;


@Entity
public class Checkin extends Frequencia{

    @NotNull
    @Column
    private Time hora;

    public Checkin() {
    }

    public Checkin(Date data, Time hora) {
        super(data);
        this.hora = hora;
    }
    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}
