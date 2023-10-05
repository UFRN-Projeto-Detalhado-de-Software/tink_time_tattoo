package com.eliasfs06.tinktime.model.dto;

import com.eliasfs06.tinktime.model.PropostaDesenho;

public class PropostaDesenhoDTO {

    private Long id;

    private byte[] desenho;

    private PropostaOrcamentoDTO propostaOrcamento;

    private String statusAprovacao;

    public PropostaDesenhoDTO() {
    }

    public PropostaDesenhoDTO(PropostaDesenho propostaDesenho){
        this.id = propostaDesenho.getId();
        this.desenho = propostaDesenho.getDesenho();
        this.propostaOrcamento = new PropostaOrcamentoDTO(propostaDesenho.getPropostaOrcamento());
        this.statusAprovacao = propostaDesenho.getStatusAprovacao().getDenominacao();
    }
}
