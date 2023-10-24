package com.eliasfs06.tinktime.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Agenda extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<DiaAgenda> diasAgenda = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<DiaAgenda> getDiasAgenda() {
        return diasAgenda;
    }

    public void setDiasAgenda(List<DiaAgenda> diasAgenda) {
        this.diasAgenda = diasAgenda;
    }
}
