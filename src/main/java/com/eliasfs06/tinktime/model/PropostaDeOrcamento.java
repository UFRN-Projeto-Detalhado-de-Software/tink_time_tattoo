package com.eliasfs06.tinktime.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PropostaDeOrcamento")
public class PropostaDeOrcamento extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
