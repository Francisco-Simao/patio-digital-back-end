package br.prf.patio_digital.controller;

import br.prf.patio_digital.dto.DashboardResponse;
import br.prf.patio_digital.dto.VeiculoConsultaResponse;
import br.prf.patio_digital.dto.VeiculoRequest;
import br.prf.patio_digital.dto.VeiculoResponse;
import br.prf.patio_digital.model.StatusVeiculo;
import org.springframework.http.ResponseEntity;
import br.prf.patio_digital.model.Veiculo;
import br.prf.patio_digital.service.VeiculoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping("/{placa}")
    public Veiculo buscar(@PathVariable String placa) {
        return service.buscarPorPlaca(placa);
    }

    @GetMapping("/valor/{placa}")
    public double calcularValor(@PathVariable String placa) {
        Veiculo veiculo = service.buscarPorPlaca(placa);
        return service.calcularValorTotal(veiculo);
    }

    @PostMapping("/{placa}/liberar")
    public ResponseEntity<String> liberar(@PathVariable String placa) {

        double valor = service.liberarVeiculo(placa);

        return ResponseEntity.ok("Veículo liberado. Valor total a pagar: R$ " + valor);
    }

    @GetMapping("/consulta")
    public VeiculoConsultaResponse consultar(
            @RequestParam String placa,
            @RequestParam String cpf) {

        return service.consultar(placa, cpf);
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrar(@RequestBody VeiculoRequest request) {

        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(request.getPlaca());
        veiculo.setModelo(request.getModelo());
        veiculo.setCpfProprietario(request.getCpfProprietario());
        veiculo.setDataRetencao(request.getDataRetencao());
        veiculo.setTaxaGuincho(request.getTaxaGuincho());
        veiculo.setValorDiaria(request.getValorDiaria());
        veiculo.setStatus(StatusVeiculo.AUTUADO);

        Veiculo salvo = service.salvar(veiculo);

        double valor = service.calcularValorTotal(salvo);

        VeiculoResponse response = new VeiculoResponse(
                salvo.getPlaca(),
                salvo.getModelo(),
                salvo.getStatus().toString(),
                valor
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<Veiculo> listarTodos() {
        return service.listarTodos();
    }

    @PutMapping("/{placa}/status")
    public Veiculo alterarStatus(
            @PathVariable String placa,
            @RequestParam StatusVeiculo status) {

        return service.alterarStatus(placa, status);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/dashboard")
    public DashboardResponse dashboard() {
        return service.gerarDashboard();
    }

}