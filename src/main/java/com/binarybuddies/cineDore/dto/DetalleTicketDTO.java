package com.binarybuddies.cineDore.dto;

import lombok.Data;

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
