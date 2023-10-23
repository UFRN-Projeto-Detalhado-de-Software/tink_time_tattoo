package com.eliasfs06.tinktime.model;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "PropostaOrcamento")
public class PropostaOrcamento extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "propostatatuagem_id")
    private PropostaTatuagem propostaTatuagem;

    private Float orcamento;

    @Nullable
    @Enumerated(EnumType.STRING)
    private PropostaStatus aprovado;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {
    }

    public PropostaTatuagem getPropostaTatuagem() {
        return propostaTatuagem;
    }

    public void setPropostaTatuagem(PropostaTatuagem propostaTatuagem) {
        this.propostaTatuagem = propostaTatuagem;
    }

    public Float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Float orcamento) {
        this.orcamento = orcamento;
    }

    public PropostaStatus getAprovado() {
        return aprovado;
    }

    public void setAprovado(PropostaStatus aprovado) {
        this.aprovado = aprovado;
    }
}
