package com.binarybuddies.cineDore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Representa cada entrada dentro del ticket.
 * Su estado por defecto es activo, se puede desactivar.
 * Cada entrada está unida a su tipo.
 */

@Data
@Entity
@Table(name = "detalle_tickets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetalleTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket_entrada", referencedColumnName = "id", nullable = false)
    private TicketEntrada ticketEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_entrada", referencedColumnName = "id", nullable = false)
    private TipoEntrada tipoEntrada;

    @Column(name = "estado")
    private Integer estado;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
