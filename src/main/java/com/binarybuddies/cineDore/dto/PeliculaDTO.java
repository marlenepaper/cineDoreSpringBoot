package com.binarybuddies.cineDore.dto;

import com.binarybuddies.cineDore.models.Funcion;
import lombok.Data;

import java.util.List;

@Data
public class PeliculaDTO {
    private Long id;
    private String nombre;
    private int anio;
    private int duracion;
    private String sinopsis;
    private String imagenPoster;
    private String categoria;
    private String clasificacion;
    private String formato;
    private String lenguaje;
    private String color;
    private List<FuncionDTO> funciones;

    public PeliculaDTO() {}

    public PeliculaDTO(Long id, String nombre, int anio, int duracion, String sinopsis,
                       String imagenPoster, String categoria, String clasificacion,
                       String formato, String lenguaje, String color, List<FuncionDTO> funciones) {
        this.id = id;
        this.nombre = nombre;
        this.anio = anio;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.imagenPoster = imagenPoster;
        this.categoria = categoria;
        this.clasificacion = clasificacion;
        this.formato = formato;
        this.lenguaje = lenguaje;
        this.color = color;
        this.funciones = funciones;
    }


}
