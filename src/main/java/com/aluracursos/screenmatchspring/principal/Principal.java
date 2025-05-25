package com.aluracursos.screenmatchspring.principal;

import com.aluracursos.screenmatchspring.model.DatosSerie;
import com.aluracursos.screenmatchspring.model.DatosTemporadas;
import com.aluracursos.screenmatchspring.service.ConsumoAPI;
import com.aluracursos.screenmatchspring.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final String URL_BASE = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=cb12e773";
    private final ConvierteDatos convierteDatos = new ConvierteDatos();


    public void mostrarMenu(){

        // Solicitar ingresar nombre de la serie
        System.out.println("Por favor, ingresa el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();

        // Formato valido para la solicitud a la API
        nombreSerie = nombreSerie.replace(" ", "+");

        // Reemplazar los espacios por un '+'
        var url = URL_BASE + nombreSerie.replace(" ", "+") + API_KEY;

        // Hacer petición
        var json = consumoAPI.obtenerDatos(url);

        // Convertir datos a objeto de tipo DatosSerie
        var datos = convierteDatos.obtenerDatos(json, DatosSerie.class);


        List<DatosTemporadas> temporadas = new ArrayList<>();

        // Obtener todas las temporadas en base al total de temporadas
        for (int i = 1; i < datos.totalDeTemporadas() ; i++) {
            json = consumoAPI.obtenerDatos(URL_BASE+nombreSerie+"&Season="+i+API_KEY);
            var datosTemporadas = convierteDatos.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }

        // Imprimir el contenido de la lista
        temporadas.forEach(System.out::println);

    }


}
