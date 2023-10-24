package com.eliasfs06.tinktime.model.dto;

import com.eliasfs06.tinktime.model.DiaAgenda;
import com.eliasfs06.tinktime.model.Horario;

import java.util.*;

public class HorariosTatuagem {
    private DiaAgenda diaAgenda;
    private List<Horario> horarios = new ArrayList<>();

    public DiaAgenda getDiaAgenda() {
        return diaAgenda;
    }

    public void setDiaAgenda(DiaAgenda diaAgenda) {
        this.diaAgenda = diaAgenda;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
