package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tickets_entrada")
public class TicketEntrada
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra", referencedColumnName = "id", nullable = false)
    private Compra compra;

    @Column(name = "codigo_qr")
    private String codigoQr;

    @ManyToOne
    @JoinColumn(name = "estado_ticket_id", nullable = false)
    private EstadoTicket estado;

    @OneToMany(mappedBy = "ticketEntrada", cascade = CascadeType.ALL)
    private List<DetalleTicket> detalles = new ArrayList<>();
}

