package com.binarybuddies.cineDore.dto;

import lombok.Data;

/**
 * DTO para enviar el contenido del carrusel
 */
@Data
public class CarruselDTO {
    private Long id;
    private String titulo;
    private String subtitulo;
    private String imagenPoster;
    private String mes;
    private int anio;


    public CarruselDTO() {}

    public CarruselDTO(Long id, String titulo, String subtitulo, String imagenPoster, String mes,
                       int anio) {
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.imagenPoster = imagenPoster;
        this.mes = mes;
        this.anio = anio;
    }

}
