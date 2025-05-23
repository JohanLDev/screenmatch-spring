package com.aluracursos.screenmatchspring;

import com.aluracursos.screenmatchspring.model.DatosSerie;
import com.aluracursos.screenmatchspring.service.ConsumoAPI;
import com.aluracursos.screenmatchspring.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchspringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchspringApplication.class, args);
	}

	// Implementar CommanLineRunner nos permite ejecutar acciones antes que inicie la aplicacion en SI.
	@Override
	public void run(String... args) throws Exception {

		var consumoAPI = new ConsumoAPI();
		var jsonResponse = consumoAPI.obtenerDatos("http://www.omdbapi.com/?i=tt3896198&apikey=cb12e773");

		ConvierteDatos convierteDatos = new ConvierteDatos();

		var datos = convierteDatos.obtenerDatos(jsonResponse, DatosSerie.class);

		System.out.println(datos);

	}
}
