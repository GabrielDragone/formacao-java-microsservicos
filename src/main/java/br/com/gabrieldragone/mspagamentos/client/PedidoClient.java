package br.com.gabrieldragone.mspagamentos.client;

import br.com.gabrieldragone.mspagamentos.model.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ms-pedidos") // Nome do ms indicado no spring.application.name do projeto de Pedidos.
public interface PedidoClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/pedidos/{id}/aprovar-pagamento") // Verbo PUT e caminho do endpoint no controller do projeto Pedidos
    void atualizarPagamento(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/pedidos/{id}")
    Pedido obterItensDoPedido(@PathVariable Long id);

}
