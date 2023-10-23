package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.PropostaTatuagem;
import com.eliasfs06.tinktime.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaTatuagemRepository extends GenericRepository<PropostaTatuagem> {

    PropostaTatuagem findPropostaTatuagemByClienteAndTatuadorAndDescricao(User cliente, User tatuador, String descricao);

    @Query("SELECT p FROM PropostaTatuagem p WHERE p.tatuador.id = ?1")
    Optional<List<PropostaTatuagem>> findAllByTatuadorId(Long id);

    //find all by cliente id
    @Query("SELECT p FROM PropostaTatuagem p WHERE p.cliente.id = ?1")
    Optional<List<PropostaTatuagem>> findAllByClienteId(Long id);
}
