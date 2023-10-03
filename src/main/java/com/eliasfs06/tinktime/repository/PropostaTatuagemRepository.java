package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.PropostaTatuagem;
import com.eliasfs06.tinktime.model.User;

public interface PropostaTatuagemRepository extends GenericRepository<PropostaTatuagem> {

    PropostaTatuagem findPropostaTatuagemByClienteAndTatuadorAndDescricao(User cliente, User tatuador, String descricao);
}
