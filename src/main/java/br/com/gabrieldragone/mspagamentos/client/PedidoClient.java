package br.com.gabrieldragone.mspagamentos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ms-pedidos") // Nome do ms indicado no spring.application.name do projeto de Pedidos.
public interface PedidoClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/pedidos/{id}/aprovar-pagamento") // Verbo PUT e caminho do endpoint no controller do projeto Pedidos
    void atualizarPagamento(@PathVariable Long id);

}
