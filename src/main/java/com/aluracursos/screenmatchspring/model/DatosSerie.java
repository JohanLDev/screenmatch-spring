package com.aluracursos.screenmatchspring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Ignorar campos no mapeados en esta clase
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("Runtime") String duracion,
                         @JsonAlias("Director") String director) {
}
