package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.Horario;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService extends GenericService<Horario> {

    @Autowired
    private HorarioRepository horarioRepository;

    public HorarioService(GenericRepository<Horario> repository) {
        super(repository);
    }

}
