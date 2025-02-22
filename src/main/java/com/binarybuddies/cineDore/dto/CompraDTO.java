package com.binarybuddies.cineDore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CompraDTO {
    private Long usuarioId;
    private Long funcionId;
    private BigDecimal totalPago;
    private List<TicketEntradaDTO> tickets;

    public CompraDTO() {
    }

    public CompraDTO(Long usuarioId, Long funcionId, BigDecimal totalPago, List<TicketEntradaDTO> tickets) {
        this.usuarioId = usuarioId;
        this.funcionId = funcionId;
        this.totalPago = totalPago;
        this.tickets = tickets;
    }
}
