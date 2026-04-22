package br.prf.patio_digital.service;

import br.prf.patio_digital.dto.DashboardResponse;
import br.prf.patio_digital.dto.HistoricoResponse;
import br.prf.patio_digital.dto.VeiculoConsultaResponse;
import br.prf.patio_digital.exception.CpfInvalidoException;
import br.prf.patio_digital.model.StatusVeiculo;
import br.prf.patio_digital.model.Veiculo;
import br.prf.patio_digital.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;
    private final HistoricoEventoService historicoEventoService;

    public VeiculoService(VeiculoRepository repository,
                          HistoricoEventoService historicoEventoService) {
        this.repository = repository;
        this.historicoEventoService = historicoEventoService;
    }

    public Veiculo salvar(Veiculo veiculo) {

        repository.findByPlaca(veiculo.getPlaca())
                .ifPresent(v -> {
                    throw new RuntimeException("Placa já cadastrada.");
                });

        Veiculo salvo = repository.save(veiculo);

        historicoEventoService.registrarEvento(
                "Veículo cadastrado no sistema",
                salvo
        );

        return salvo;
    }

    public Veiculo buscarPorPlaca(String placa) {
        return repository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    public double liberarVeiculo(String placa) {

        Veiculo veiculo = buscarPorPlaca(placa);

        if (veiculo.getStatus() == StatusVeiculo.LIBERADO) {
            throw new RuntimeException("Veículo já está liberado.");
        }

        double valorTotal = calcularValorTotal(veiculo);

        veiculo.setStatus(StatusVeiculo.LIBERADO);
        repository.save(veiculo);

        historicoEventoService.registrarEvento(
                "Veículo liberado ao proprietário",
                veiculo
        );

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

    public VeiculoConsultaResponse consultar(String placa, String cpf) {

        Veiculo veiculo = buscarPorPlaca(placa);

        if (!veiculo.getCpfProprietario().equals(cpf)) {
            throw new CpfInvalidoException("CPF não corresponde ao proprietário");
        }

        long diasNoPatio = ChronoUnit.DAYS.between(
                veiculo.getDataRetencao(),
                LocalDate.now()
        );

        if (diasNoPatio < 0) {
            diasNoPatio = 0;
        }

        double valorTotal = veiculo.getTaxaGuincho() +
                (diasNoPatio * veiculo.getValorDiaria());

        List<HistoricoResponse> historico = historicoEventoService
                .buscarPorVeiculo(veiculo.getId())
                .stream()
                .map(h -> new HistoricoResponse(
                        h.getDescricao(),
                        h.getDataHora()
                ))
                .toList();

        return new VeiculoConsultaResponse(
                veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getCpfProprietario(),
                veiculo.getDataRetencao(),
                veiculo.getStatus().name(),
                veiculo.getTaxaGuincho(),
                veiculo.getValorDiaria(),
                diasNoPatio,
                valorTotal,
                historico
        );
    }

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public Veiculo alterarStatus(String placa, StatusVeiculo novoStatus) {

        Veiculo veiculo = buscarPorPlaca(placa);

        veiculo.setStatus(novoStatus);

        historicoEventoService.registrarEvento(
                "Status alterado para: " + novoStatus,
                veiculo
        );

        return repository.save(veiculo);
    }

    public DashboardResponse gerarDashboard() {

        List<Veiculo> veiculos = repository.findAll();

        long patio = veiculos.stream()
                .filter(v -> v.getStatus() == StatusVeiculo.AUTUADO)
                .count();

        long liberadosHoje = veiculos.stream()
                .filter(v -> v.getStatus() == StatusVeiculo.LIBERADO)
                .count();

        double valorArrecadado = veiculos.stream()
                .filter(v -> v.getStatus() == StatusVeiculo.LIBERADO)
                .mapToDouble(this::calcularValorTotal)
                .sum();

        long total = veiculos.size();

        return new DashboardResponse(patio, liberadosHoje, valorArrecadado, total);
    }
}