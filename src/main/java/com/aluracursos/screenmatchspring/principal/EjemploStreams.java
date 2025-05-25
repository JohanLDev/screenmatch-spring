package com.aluracursos.screenmatchspring.principal;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EjemploStreams {

    public  void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Yari","Johan","Giovanni","Scarleth");

        // Ordenar usando la funcion stream
        nombres.stream().
                sorted().
                limit(2).
                filter(n -> n.startsWith("J")).
                map(n -> n.toUpperCase()).
                forEach(System.out::println);

    }

}
