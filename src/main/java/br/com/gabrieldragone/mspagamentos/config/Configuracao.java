package br.com.gabrieldragone.mspagamentos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Pra indicar ao Spring que essa é uma Classe de Configuração.
public class Configuracao {

    @Bean
    public ModelMapper obterModelMappar() { // Feita essa configuraçãom nossa dependência dentro do PagamentoService já vai funcionar.
        return new ModelMapper();
    }

}
