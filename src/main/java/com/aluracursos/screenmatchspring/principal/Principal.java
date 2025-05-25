package com.aluracursos.screenmatchspring.principal;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.aluracursos.screenmatchspring.model.DatosEpisodio;
import com.aluracursos.screenmatchspring.model.DatosSerie;
import com.aluracursos.screenmatchspring.model.DatosTemporadas;
import com.aluracursos.screenmatchspring.model.Episodio;
import com.aluracursos.screenmatchspring.service.ConsumoAPI;
import com.aluracursos.screenmatchspring.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final String URL_BASE = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=cb12e773";
    private final ConvierteDatos convierteDatos = new ConvierteDatos();


    public void mostrarMenu() {

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

        /* Obtener todas las temporadas basándonos en el total de temporadas */
        for (int i = 1; i < datos.totalDeTemporadas(); i++) {
            json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie + "&Season=" + i + API_KEY);
            var datosTemporadas = convierteDatos.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }

        // Imprimir el contenido de la lista
        //temporadas.forEach(System.out::println);


        // Mostrar solo el título de los episodios para las temporadas.
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        // Crear una lista de episodios de todas las temporadas
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList()); // Retornar una lista mutable, a diferencia del toList() que son inmutables

        // Top 5 episodios. Excluyendo los sin evaluacion
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .peek(e-> System.out.println("Aplicando primer filtro" + e)) // Esto sirve como log
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .limit(5)
                .forEach(System.out::println);

        // Convirtiendo los datos a una lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(d -> d.episodios().stream().map(e -> new Episodio(e.numeroEpisodio(), e)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

        // Busqueda de episodios a partir de x año
        System.out.println("Por favor indica el año a partir del cual deseas ver los episodios:");
        var fecha = teclado.nextInt();
        teclado.nextLine();

        LocalDate fechaBusqueda = LocalDate.of(fecha, 1,1);

        // Formatear fecha de aaaa-mm-dd a dd-mm-aaaa
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getFechaDeLanzamiento()!= null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                .forEach(e -> {
                    System.out.println(
                            "Temporada " + e.getTemporada()+
                                    " Episodio " + e.getNumeroEpisodio()+
                                    " Fecha de Lanzamiento " + e.getFechaDeLanzamiento().format(dtf)
                    );
                });
        
        // Obtener episodio con parte del nombre
        System.out.println("Ingrese parte del nombre del episodio a buscar");
        var nombreABuscar = teclado.nextLine();


        Optional<Episodio> first = episodios.stream().filter(e -> e.getTitulo().toUpperCase().contains(nombreABuscar.toUpperCase()))
                .findFirst();

        if(first.isPresent()){
            System.out.println("Episodio encontrado: " + first.get());
        } else {
            System.out.println("Episodio no encontrado");
        }


    }




}
