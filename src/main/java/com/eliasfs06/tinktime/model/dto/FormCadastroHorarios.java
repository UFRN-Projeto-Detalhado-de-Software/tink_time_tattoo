package com.eliasfs06.tinktime.model.dto;

import java.util.List;

public class FormCadastroHorarios {
     private Long agendaId;
     private List<Long> horariosId;

    public FormCadastroHorarios() {
    }

    public Long getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Long agendaId) {
        this.agendaId = agendaId;
    }

    public List<Long> getHorariosId() {
        return horariosId;
    }

    public void setHorariosId(List<Long> horariosId) {
        this.horariosId = horariosId;
    }
}
