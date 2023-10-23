package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.*;
import com.eliasfs06.tinktime.repository.AgendaRepository;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

@Service
public class AgendaService extends GenericService<Agenda> {

    @Autowired
    private DiaAgendaService diaAgendaService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private AgendaRepository agendaRepository;

    public AgendaService(GenericRepository<Agenda> repository) {
        super(repository);
    }

    public void createAgenda(Artist artist) {
        Agenda agenda = new Agenda();
        artist.setAgenda(agenda);
        save(agenda);
    }

    public DiaAgenda createDiaAgenda(Agenda agenda, LocalDate dia){
        DiaAgenda diaAgenda = new DiaAgenda();
        diaAgenda.setDia(dia);
        agenda.getDiasAgenda().add(diaAgenda);
        diaAgendaService.save(diaAgenda);

        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(22, 0);

        while (startTime.getHour() < endTime.getHour()){
            Horario horario = new Horario();
            horario.setHoraInicio(startTime);
            horario.setHoraFim(startTime.plusMinutes(30));
            startTime = startTime.plusMinutes(30);
            horario.setStatusHorario(StatusHorario.FECHADO);
            diaAgenda.getHorarios().add(horario);
            horarioService.save(horario);
        }

        diaAgendaService.save(diaAgenda);
        update(agenda);
        return diaAgenda;
    }

    public Agenda findByArtist(Artist artist) {
        return agendaRepository.findByArtist(artist);
    }
}
