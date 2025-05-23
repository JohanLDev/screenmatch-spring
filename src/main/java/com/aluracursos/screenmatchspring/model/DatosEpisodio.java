package com.aluracursos.screenmatchspring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Ignorar las claves json no mapeadas
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode")Integer numeroEpisodio,
                            @JsonAlias("imdbRating")String evaluacion,
                            @JsonAlias("Released") String fechaDeLanzamiento
                            ) {
}
