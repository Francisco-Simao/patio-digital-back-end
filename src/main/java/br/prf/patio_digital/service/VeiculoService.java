package br.prf.patio_digital.service;

import br.prf.patio_digital.exception.CpfInvalidoException;
import br.prf.patio_digital.model.StatusVeiculo;
import br.prf.patio_digital.model.Veiculo;
import br.prf.patio_digital.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public Veiculo buscarPorPlaca(String placa) {
        return repository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    private final HistoricoEventoService historicoEventoService;

    public VeiculoService(VeiculoRepository repository,
                          HistoricoEventoService historicoEventoService) {
        this.repository = repository;
        this.historicoEventoService = historicoEventoService;
    }

    public double liberarVeiculo(String placa) {

        Veiculo veiculo = buscarPorPlaca(placa);

        double valorTotal = calcularValorTotal(veiculo);

        veiculo.setStatus(StatusVeiculo.LIBERADO);

        repository.save(veiculo);

        return valorTotal;
    }
    public double calcularValorTotal(Veiculo veiculo) {

        long diasNoPatio = ChronoUnit.DAYS.between(
                veiculo.getDataRetencao(),
                LocalDate.now()
        );

        if (diasNoPatio < 0) {
            diasNoPatio = 0;
        }

        return veiculo.getTaxaGuincho() +
                (diasNoPatio * veiculo.getValorDiaria());
    }

    public Veiculo consultarVeiculo( String placa , String cpf){
        Veiculo veiculo = buscarPorPlaca(placa);

        if(!veiculo.getCpfProprietario().equals(cpf)){
            throw new CpfInvalidoException("CPF não corresponde ao proprietário");
        }

        return veiculo;
    }
}