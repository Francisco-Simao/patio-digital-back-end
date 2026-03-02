package br.prf.patio_digital.dto;

import java.time.LocalDate;

public class VeiculoRequest {

    private String placa;
    private String modelo;
    private String cpfProprietario;
    private LocalDate dataRetencao;
    private Double taxaGuincho;
    private Double valorDiaria;

    // getters e setters

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    public LocalDate getDataRetencao() {
        return dataRetencao;
    }

    public void setDataRetencao(LocalDate dataRetencao) {
        this.dataRetencao = dataRetencao;
    }

    public Double getTaxaGuincho() {
        return taxaGuincho;
    }

    public void setTaxaGuincho(Double taxaGuincho) {
        this.taxaGuincho = taxaGuincho;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}