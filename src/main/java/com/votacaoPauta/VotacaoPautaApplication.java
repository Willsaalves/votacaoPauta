package com.votacaoPauta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info( 
				title= "API votacaoPauta",
                description ="Esta API fornece endpoints para gerenciar votações em pautas",
                version="1.0"
		))
public class VotacaoPautaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacaoPautaApplication.class, args);
	}

}
