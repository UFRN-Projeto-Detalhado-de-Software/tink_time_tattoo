package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.Agenda;
import com.eliasfs06.tinktime.model.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends GenericRepository<Agenda>{
    @Query("SELECT a FROM Agenda a JOIN Funcionario func ON func.agenda = a WHERE func = ?1")
    Agenda findByFuncionario(Funcionario funcionario);


}
