package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.Horario;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class HorarioService extends GenericService<Horario> {
    public HorarioService(GenericRepository<Horario> repository) {
        super(repository);
    }
}
