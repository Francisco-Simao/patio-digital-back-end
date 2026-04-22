package br.prf.patio_digital.dto;

public class DashboardResponse {

    private long veiculosPatio;
    private long veiculosLiberadosHoje;
    private double valorArrecadado;
    private long totalApreensoes;

    public DashboardResponse(long veiculosPatio,
                             long veiculosLiberadosHoje,
                             double valorArrecadado,
                             long totalApreensoes) {
        this.veiculosPatio = veiculosPatio;
        this.veiculosLiberadosHoje = veiculosLiberadosHoje;
        this.valorArrecadado = valorArrecadado;
        this.totalApreensoes = totalApreensoes;
    }

    public long getVeiculosPatio() { return veiculosPatio; }
    public long getVeiculosLiberadosHoje() { return veiculosLiberadosHoje; }
    public double getValorArrecadado() { return valorArrecadado; }
    public long getTotalApreensoes() { return totalApreensoes; }
}