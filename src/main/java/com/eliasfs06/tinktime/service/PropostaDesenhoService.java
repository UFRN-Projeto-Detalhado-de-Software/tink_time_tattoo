package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.PropostaDesenho;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class PropostaDesenhoService extends GenericService<PropostaDesenho> {

    public PropostaDesenhoService(GenericRepository<PropostaDesenho> repository) {
        super(repository);
    }


}
