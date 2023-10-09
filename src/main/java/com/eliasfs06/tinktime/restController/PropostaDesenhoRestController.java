package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.PropostaDesenho;
import com.eliasfs06.tinktime.model.dto.PropostaDesenhoDTO;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.service.PropostaDesenhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proposta-desenho")
public class PropostaDesenhoRestController extends GenericRestController<PropostaDesenho> {

    private final PropostaDesenhoService propostaDesenhoService;

    public PropostaDesenhoRestController(GenericRepository<PropostaDesenho> propostaDesenhoGenericRepository,
                                         PropostaDesenhoService propostaDesenhoService) {
        super(propostaDesenhoGenericRepository);
        this.propostaDesenhoService = propostaDesenhoService;
    }

    @Override
    @PostMapping("")
    public ResponseEntity<PropostaDesenho> create(@RequestBody PropostaDesenho created) {
        try {
            return ResponseEntity.ok(propostaDesenhoService.create(new PropostaDesenhoDTO(created)));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
