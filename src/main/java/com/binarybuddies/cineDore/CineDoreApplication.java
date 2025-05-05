package com.binarybuddies.cineDore;

import com.binarybuddies.cineDore.services.PdfReaderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@OpenAPIDefinition
@SpringBootApplication
@EnableScheduling
public class CineDoreApplication {

	@Autowired
	private PdfReaderService reader;

	public static void main(String[] args) {
		SpringApplication.run(CineDoreApplication.class, args);
	}
	@PostConstruct
	public void run() {
		try {
			String texto = reader.procesarPdfpeliculas("C:\\Users\\Sihao\\Desktop\\EPSUM\\TFG\\recursos\\programacion\\programa-mayo2025-filmotecaespa-ola.pdf");
			reader.fechaFuncion(texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
