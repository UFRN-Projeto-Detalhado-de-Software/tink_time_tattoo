package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.PropostaOrcamento;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaOrcamentoRepository extends GenericRepository<PropostaOrcamento>{

    //find all where propostaTatuagem.tatuador.id = id
    @Query("SELECT p FROM PropostaOrcamento p WHERE p.propostaTatuagem.cliente.id = ?1")
    Optional<List<PropostaOrcamento>> findAllByClienteId(Long id);

}
