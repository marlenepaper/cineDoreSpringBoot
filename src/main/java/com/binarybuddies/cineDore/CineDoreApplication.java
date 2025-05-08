package com.binarybuddies.cineDore;

import com.binarybuddies.cineDore.services.LinkService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@OpenAPIDefinition
@SpringBootApplication
@EnableScheduling
public class CineDoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineDoreApplication.class, args);
	}

	//Para probar que funciona
	@Bean
	public CommandLineRunner run(LinkService linkService) {
		return args -> {
			linkService.downloadAndReadPdf();
			System.exit(0);
		};
	}
}
