package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.PropostaOrcamento;
import com.eliasfs06.tinktime.model.PropostaTatuagem;
import com.eliasfs06.tinktime.model.dto.PropostaOrcamentoDTO;
import com.eliasfs06.tinktime.model.dto.PropostaTatuagemDTO;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.service.PropostaOrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proposta-orcamento")
public class PropostaOrcamentoController extends GenericRestController<PropostaOrcamento> {

    private final PropostaOrcamentoService propostaOrcamentoService;

    public PropostaOrcamentoController(GenericRepository<PropostaOrcamento> repository, PropostaOrcamentoService propostaOrcamentoService) {
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

    @GetMapping("/recusar/{id}")
    public ResponseEntity<PropostaOrcamento> recusar(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(propostaOrcamentoService.recusar(id));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/aprovar/{id}")
    public ResponseEntity<PropostaOrcamento> aprovar(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(propostaOrcamentoService.aprovar(id));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
