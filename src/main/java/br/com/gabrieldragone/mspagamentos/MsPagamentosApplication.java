package br.com.gabrieldragone.mspagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient // Pra aplicação saber que é um Cliente Eureka e que precisa se registrar em algum Servidor Eureka. Não precisa mais anotar nas versões mais recentes.
public class MsPagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPagamentosApplication.class, args);
	}

}
