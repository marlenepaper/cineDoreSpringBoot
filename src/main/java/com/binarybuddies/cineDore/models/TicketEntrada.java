package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tickets_entrada")
public class TicketEntrada
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra", nullable = false)
    private Compra compra;

    @Column(name = "codigo_qr")
    private String codigoQr;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;

    @OneToMany(mappedBy = "ticketEntrada", cascade = CascadeType.ALL)
    private List<DetalleTicket> detalles = new ArrayList<>();
}

