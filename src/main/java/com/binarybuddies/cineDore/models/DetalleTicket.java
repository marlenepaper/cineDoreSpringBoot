package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "detalle_ticket")
public class DetalleTicket
{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket_entrada", nullable = false)
    private TicketEntrada idTicketEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_entrada", nullable = false)
    private TipoEntrada tipoEntrada;

    @Column(name = "cantidad", nullable = false)
    @Min(1)
    private Integer cantidad;
}
