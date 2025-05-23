package com.aluracursos.screenmatchspring.service;

public interface IConvierteDatos {

    // Método generico para mapear la respuesta de un json a una clase java
    <T> T obtenerDatos(String jsonResponse, Class <T> clase);
}
