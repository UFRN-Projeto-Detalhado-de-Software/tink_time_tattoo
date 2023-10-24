package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.Horario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends GenericRepository<Horario>{

}
