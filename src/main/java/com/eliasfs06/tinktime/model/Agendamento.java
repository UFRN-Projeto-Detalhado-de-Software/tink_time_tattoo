package com.eliasfs06.tinktime.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Agendamento extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Horario> horarios = new ArrayList<>();

    private LocalDate data;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
