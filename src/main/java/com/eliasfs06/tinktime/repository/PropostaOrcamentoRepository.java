package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.PropostaOrcamento;
import com.eliasfs06.tinktime.model.enums.StatusAprovacao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaOrcamentoRepository extends GenericRepository<PropostaOrcamento>{

    //find all where propostaTatuagem.tatuador.id = id
    @Query("SELECT p FROM PropostaOrcamento p WHERE p.propostaTatuagem.cliente.id = ?1")
    Optional<List<PropostaOrcamento>> findAllByClienteId(Long id);

    @Query(value = "SELECT po.* FROM proposta_orcamento po " +
            " JOIN proposta_tatuagem pt ON pt.id = po.propostatatuagem_id " +
            " WHERE po.status_aprovacao = ?1 AND pt.id = ?2", nativeQuery = true)
    Optional<List<PropostaOrcamento>> findAllByStatusAprovacaoAndPropostaTatuagemId(String statusAprovacao, Long tatuagemId);
}
