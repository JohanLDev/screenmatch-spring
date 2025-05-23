package com.aluracursos.screenmatchspring.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class <T> clase);
}
