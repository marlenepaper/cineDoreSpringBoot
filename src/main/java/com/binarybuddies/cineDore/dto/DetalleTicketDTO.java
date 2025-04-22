package com.binarybuddies.cineDore.dto;

import lombok.Data;

/**
 * DTO para recibir datos de los tickets del front, como tipo de tickets y cantidad
 */

@Data
public class DetalleTicketDTO {
    private Long tipoEntradaId;
    int cantidad;
    private Long estadoId;

    public DetalleTicketDTO() {
    }

    public DetalleTicketDTO(Long tipoEntradaId, int cantidad, Long estadoId) {
        this.tipoEntradaId = tipoEntradaId;
        this.cantidad = cantidad;
        this.estadoId = estadoId;
    }

}
