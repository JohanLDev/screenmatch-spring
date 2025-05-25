package com.aluracursos.screenmatchspring;

import com.aluracursos.screenmatchspring.model.DatosEpisodio;
import com.aluracursos.screenmatchspring.model.DatosSerie;
import com.aluracursos.screenmatchspring.model.DatosTemporadas;
import com.aluracursos.screenmatchspring.principal.EjemploStreams;
import com.aluracursos.screenmatchspring.principal.Principal;
import com.aluracursos.screenmatchspring.service.ConsumoAPI;
import com.aluracursos.screenmatchspring.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchspringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchspringApplication.class, args);
	}

	// Implementar CommanLineRunner nos permite ejecutar acciones antes que inicie la aplicacion en SI.
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mostrarMenu();


//		EjemploStreams ejemploStreams = new EjemploStreams();
//		ejemploStreams.muestraEjemplo();


	}
}
