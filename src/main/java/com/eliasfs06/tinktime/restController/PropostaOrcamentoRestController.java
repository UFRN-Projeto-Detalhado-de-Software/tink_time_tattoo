package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.PropostaOrcamento;
import com.eliasfs06.tinktime.model.dto.PropostaOrcamentoDTO;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.service.PropostaOrcamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proposta-orcamento")
public class PropostaOrcamentoRestController extends GenericRestController<PropostaOrcamento> {

    private final PropostaOrcamentoService propostaOrcamentoService;

    public PropostaOrcamentoRestController(GenericRepository<PropostaOrcamento> repository, PropostaOrcamentoService propostaOrcamentoService) {
        super(repository);
        this.propostaOrcamentoService = propostaOrcamentoService;
    }

    @Override
    @PostMapping("")
    public ResponseEntity<PropostaOrcamento> create(@RequestBody PropostaOrcamento created) {
        try {
            return ResponseEntity.ok(propostaOrcamentoService.create(new PropostaOrcamentoDTO(created)));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
