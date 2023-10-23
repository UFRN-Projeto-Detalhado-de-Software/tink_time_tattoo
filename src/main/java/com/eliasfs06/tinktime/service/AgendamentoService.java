package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.Agendamento;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService extends GenericService<Agendamento>{
    public AgendamentoService(GenericRepository<Agendamento> repository) {
        super(repository);
    }
}
