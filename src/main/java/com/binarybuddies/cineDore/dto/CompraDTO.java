package com.binarybuddies.cineDore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO para recibir datos de la compra del front
 */

@Data
public class CompraDTO {
    private Long compraId;
    private Long usuarioId;
    private Long funcionId;
    private BigDecimal totalPago;
    private List<DetalleTicketDTO> tickets;

    public CompraDTO() {
    }

    public CompraDTO(Long compraId,Long usuarioId, Long funcionId, BigDecimal totalPago, List<DetalleTicketDTO> tickets) {
        this.compraId=compraId;
        this.usuarioId = usuarioId;
        this.funcionId = funcionId;
        this.totalPago = totalPago;
        this.tickets = tickets;
    }
}
