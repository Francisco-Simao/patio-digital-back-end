package br.prf.patio_digital.dto;

import java.time.LocalDateTime;

public class HistoricoResponse {

    private String descricao;
    private LocalDateTime dataHora;

    public HistoricoResponse(String descricao, LocalDateTime dataHora) {
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}