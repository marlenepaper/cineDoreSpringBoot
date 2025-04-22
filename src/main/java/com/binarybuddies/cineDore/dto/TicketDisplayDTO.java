package com.binarybuddies.cineDore.dto;

import com.binarybuddies.cineDore.models.Sala;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO para enviar al front con los datos del ticket
 */

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
    private String sala;
    private int cantidadTickets;
}
