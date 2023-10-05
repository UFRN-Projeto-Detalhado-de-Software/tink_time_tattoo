package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.model.PropostaDesenho;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proposta-desenho")
public class PropostaDesenhoRestController extends GenericRestController<PropostaDesenho> {

    public PropostaDesenhoRestController(GenericRepository<PropostaDesenho> repository) {
        super(repository);
    }

    @Override
    @PostMapping("")
    public ResponseEntity<PropostaDesenho> create(PropostaDesenho created) {
        return super.create(created);
    }
}
