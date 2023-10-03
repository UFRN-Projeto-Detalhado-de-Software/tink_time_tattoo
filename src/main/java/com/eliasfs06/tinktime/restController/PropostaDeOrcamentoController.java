package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.model.PropostaDeOrcamento;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropostaDeOrcamentoController extends GenericRestController<PropostaDeOrcamento> {
    public PropostaDeOrcamentoController(GenericRepository<PropostaDeOrcamento> repository) {
        super(repository);
    }
}
