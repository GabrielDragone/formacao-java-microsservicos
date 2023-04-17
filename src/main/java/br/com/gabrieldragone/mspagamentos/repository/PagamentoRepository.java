package br.com.gabrieldragone.mspagamentos.repository;

import br.com.gabrieldragone.mspagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
