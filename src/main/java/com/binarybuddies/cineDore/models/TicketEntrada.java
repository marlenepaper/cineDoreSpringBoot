package com.binarybuddies.cineDore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa al ticket que se genera con la compra.
 * Lleva el código qr y contiene los datos de las entradas.
 */

@Data
@Entity
@Table(name = "tickets_entrada")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketEntrada
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra", nullable = false)
    @JsonBackReference
    private Compra compra;

    @OneToMany(mappedBy = "ticketEntrada", cascade = CascadeType.ALL)
    private List<DetalleTicket> detalles = new ArrayList<>();

    @Column(name = "codigo_qr", columnDefinition = "TEXT")
    private String codigoQr;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

