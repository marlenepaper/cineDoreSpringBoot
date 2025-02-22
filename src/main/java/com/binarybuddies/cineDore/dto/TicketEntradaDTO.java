package com.binarybuddies.cineDore.dto;

import lombok.Data;

@Data
public class TicketEntradaDTO {
    private String codigoQr;
    private Long estadoId;

    public TicketEntradaDTO() {
    }

    public TicketEntradaDTO(String codigoQr, Long estadoId) {
        this.codigoQr = codigoQr;
        this.estadoId = estadoId;
    }
}
