package br.prf.patio_digital.dto;

public class VeiculoResponse {

    private String placa;
    private String modelo;
    private String status;
    private Double valorAtualizado;

    public VeiculoResponse(String placa, String modelo, String status, Double valorAtualizado) {
        this.placa = placa;
        this.modelo = modelo;
        this.status = status;
        this.valorAtualizado = valorAtualizado;
    }

    // getters
}