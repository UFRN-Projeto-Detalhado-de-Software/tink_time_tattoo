package com.eliasfs06.tinktime.model;

public enum PropostaStatus {
    APROVADO("Aprovado"),
    RECUSADO("Recusado"),
    AVALIACAO("Em Avaliação");

    private String status;

    PropostaStatus(String status) {
        this.status = status;
    }
}
