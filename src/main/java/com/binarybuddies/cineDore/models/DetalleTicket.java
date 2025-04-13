package com.binarybuddies.cineDore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "detalle_tickets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "estado")
    private Integer estado;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
