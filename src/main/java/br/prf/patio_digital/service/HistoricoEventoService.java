package br.prf.patio_digital.service;

import br.prf.patio_digital.model.HistoricoEvento;
import br.prf.patio_digital.model.Veiculo;
import br.prf.patio_digital.repository.HistoricoEventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoEventoService {

    private final HistoricoEventoRepository repository;

    public HistoricoEventoService(HistoricoEventoRepository repository) {
        this.repository = repository;
    }

    public void registrarEvento(String descricao, Veiculo veiculo) {
        HistoricoEvento evento = new HistoricoEvento(descricao, veiculo);
        repository.save(evento);
    }

    public List<HistoricoEvento> buscarPorVeiculo(Long veiculoId) {
        return repository.findByVeiculoId(veiculoId);
    }
}