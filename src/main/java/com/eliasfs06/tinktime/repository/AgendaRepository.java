package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.Agenda;
import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.DiaAgenda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AgendaRepository extends GenericRepository<Agenda>{
    @Query("SELECT a FROM Agenda a JOIN Artist art ON art.agenda = a WHERE art = ?1")
    Agenda findByArtist(Artist artist);


}
