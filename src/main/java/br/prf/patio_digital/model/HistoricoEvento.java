package br.prf.patio_digital.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistoricoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    public HistoricoEvento() {
    }

    public HistoricoEvento(String descricao, Veiculo veiculo) {
        this.descricao = descricao;
        this.veiculo = veiculo;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public Veiculo getVeiculo() { return veiculo; }

    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
}