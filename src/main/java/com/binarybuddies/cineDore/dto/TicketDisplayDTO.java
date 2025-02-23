package com.binarybuddies.cineDore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDisplayDTO {
    private Long funcionId;
    private BigDecimal totalPago;
    private String codigoQr;
    private String fechaFuncion;
    private String tituloPelicula;
    private String imagenPelicula;
    private String clasificacion;
    private String lenguaje;
    private int duracion;
}
