package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_tickets")
public class DetalleTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Agregar un campo de identificación obligatorio

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket_entrada", referencedColumnName = "id", nullable = false)
    private TicketEntrada ticketEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_entrada", referencedColumnName = "id", nullable = false)
    private TipoEntrada tipoEntrada;

    @Column(name = "cantidad", nullable = false)
    @Min(1)
    private Integer cantidad;
}
