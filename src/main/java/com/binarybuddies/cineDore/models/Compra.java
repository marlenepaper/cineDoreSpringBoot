package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "compras")
public class Compra
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Usuario cannot be null")
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcion", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Función cannot be null")
    private Funcion funcion;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

    @Column(name = "total_pago", nullable = false, precision = 10, scale = 2)
    @Positive(message = "Total debe ser positivo")
    private BigDecimal totalPago;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<TicketEntrada> tickets = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        fechaCompra = LocalDateTime.now();
    }
}
