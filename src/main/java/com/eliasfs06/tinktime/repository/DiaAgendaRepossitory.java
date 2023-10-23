package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.DiaAgenda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface DiaAgendaRepossitory extends GenericRepository<com.eliasfs06.tinktime.model.DiaAgenda>{
    @Query(value = "SELECT dia.* FROM agenda a " +
            "JOIN agenda_dias_agenda da ON da.agenda_id = a.id " +
            "JOIN dia_agenda dia ON da.dias_agenda_id = dia.id " +
            "WHERE a.id = ?1 AND dia.dia = ?2" , nativeQuery = true)
    DiaAgenda findByDia(Long agenda, Date data);
}
