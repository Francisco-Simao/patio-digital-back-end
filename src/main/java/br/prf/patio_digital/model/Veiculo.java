package br.prf.patio_digital.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String placa;
    private String modelo;
    private String cpfProprietario;

    private LocalDate dataRetencao;

    private Double taxaGuincho;
    private Double valorDiaria;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }
}