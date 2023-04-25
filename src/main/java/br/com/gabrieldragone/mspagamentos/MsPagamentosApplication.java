package br.com.gabrieldragone.mspagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient // Pra aplicação saber que é um Cliente Eureka e que precisa se registrar em algum Servidor Eureka. Não precisa mais anotar nas versões mais recentes.
//@ImportAutoConfiguration({FeignAutoConfiguration.class}) // Adicionado devido à erro "required a bean of type 'org.springframework.cloud.openfeign.FeignContext' that could not be found"
@EnableFeignClients  // Para conseguirmos usar o Feign na aplicação pra fazer as chamadas HTTP entre ms
public class MsPagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPagamentosApplication.class, args);
	}

}
