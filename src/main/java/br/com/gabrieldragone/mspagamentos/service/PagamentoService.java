package br.com.gabrieldragone.mspagamentos.service;

import br.com.gabrieldragone.mspagamentos.dto.PagamentoDto;
import br.com.gabrieldragone.mspagamentos.model.Pagamento;
import br.com.gabrieldragone.mspagamentos.model.Status;
import br.com.gabrieldragone.mspagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired // Injeção de dependência.
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao) // Pegue todos.
                .map(p -> modelMapper.map(p, PagamentoDto.class)); // Transforme retorno em DTO.
    }

    public PagamentoDto obterPorId(Long id) {
        Pagamento pagamento = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criar(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizar(Long id, PagamentoDto dto) {
        Pagamento pagamento = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

}
