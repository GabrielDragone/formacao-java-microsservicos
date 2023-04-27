package br.com.gabrieldragone.mspagamentos.controller;

import br.com.gabrieldragone.mspagamentos.dto.PagamentoDto;
import br.com.gabrieldragone.mspagamentos.enums.Status;
import br.com.gabrieldragone.mspagamentos.service.PagamentoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    @Operation(description = "Retorna todos os pagamentos de forma paginada")
    public ResponseEntity<Page<PagamentoDto>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(pagamentoService.obterTodos(paginacao));
    }

    @GetMapping("/{id}") // Indica ao Spring que deve atribuir à variável abaixo o valor que estamos enviando como parâmetro no endereço da Requisição
    @Operation(description = "Retorna um pagamento buscando por id")
    public ResponseEntity<PagamentoDto> buscar(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(pagamentoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar(@RequestBody @Valid PagamentoDto dto, UriComponentsBuilder uriBuilder) { // RequestBody Indica ao Spring que deve atribuir à variável o conteúdo que estamos enviando no corpo de Requisição
        PagamentoDto pagamentoDto = pagamentoService.criar(dto);
        URI endereco = uriBuilder.path("/api/v1/pagamentos/{id}").buildAndExpand(pagamentoDto.getId()).toUri();

        return ResponseEntity.created(endereco).body(pagamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDto dto) {
        PagamentoDto pagamentoDto = pagamentoService.atualizar(id, dto);

        return ResponseEntity.ok(pagamentoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable @NotNull Long id) {
        pagamentoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirmar") // Patch pois vai atualizar parcialmente o objeto.
    @CircuitBreaker(name = "atualizaPedido", fallbackMethod = "pagamentoAutorizadoComIntegracaoPendente") // Habilita a tratativa do Circuit Breaker. fallBackMethod indica qual o método que fará a tratativa em caso de entrar no Circuit Breaker
    public void confirmarPagamento(@PathVariable @NotNull Long id) {
        pagamentoService.confirmarPagamento(id);
    }

    // Fallback do atualizaPedido. Deve ter a mesma assinatura (retorno, parametro e UM paramtro adicional de Exception). Obs: Se não passar a exception não vai funcionar.
    public void pagamentoAutorizadoComIntegracaoPendente(@PathVariable @NotNull Long id, Exception e) {
        pagamentoService.alterarStatus(id, Status.CONFIRMADO_SEM_INTEGRACAO);
    }

    @GetMapping("/porta")
    public String retornarPorta(@Value("{local.server.port}") String porta) {
        return String.format("Aplicação rodando na porta %s", porta);
    }

}
