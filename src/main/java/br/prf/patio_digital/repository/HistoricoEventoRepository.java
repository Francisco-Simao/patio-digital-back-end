package br.prf.patio_digital.repository;

import br.prf.patio_digital.model.HistoricoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricoEventoRepository extends JpaRepository<HistoricoEvento, Long> {

    List<HistoricoEvento> findByVeiculoId(Long veiculoId);
}