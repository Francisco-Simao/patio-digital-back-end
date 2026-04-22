package br.prf.patio_digital.dto;

import java.time.LocalDate;
import java.util.List;

public class VeiculoConsultaResponse {

    private String placa;
    private String modelo;
    private String cpf;
    private LocalDate dataRetencao;
    private String status;
    private double taxaGuincho;
    private double diaria;
    private long diasNoPatio;
    private double valorTotal;
    private List<HistoricoResponse> historico;

    public VeiculoConsultaResponse(
            String placa,
            String modelo,
            String cpf,
            LocalDate dataRetencao,
            String status,
            double taxaGuincho,
            double diaria,
            long diasNoPatio,
            double valorTotal,
            List<HistoricoResponse> historico) {

        this.placa = placa;
        this.modelo = modelo;
        this.cpf = cpf;
        this.dataRetencao = dataRetencao;
        this.status = status;
        this.taxaGuincho = taxaGuincho;
        this.diaria = diaria;
        this.diasNoPatio = diasNoPatio;
        this.valorTotal = valorTotal;
        this.historico = historico;
    }


    // getters


    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataRetencao() {
        return dataRetencao;
    }

    public String getStatus() {
        return status;
    }

    public double getTaxaGuincho() {
        return taxaGuincho;
    }

    public double getDiaria() {
        return diaria;
    }

    public long getDiasNoPatio() {
        return diasNoPatio;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public List<HistoricoResponse> getHistorico() {
        return historico;
    }
}
