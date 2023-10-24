package com.eliasfs06.tinktime.model.dto;

public class AgendamentoDto {
    private String diaAgenda;
    private String horaIncio;
    private String horaFim;

    public String getDiaAgenda() {
        return diaAgenda;
    }

    public void setDiaAgenda(String diaAgenda) {
        this.diaAgenda = diaAgenda;
    }

    public String getHoraIncio() {
        return horaIncio;
    }

    public void setHoraIncio(String horaIncio) {
        this.horaIncio = horaIncio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }
}
