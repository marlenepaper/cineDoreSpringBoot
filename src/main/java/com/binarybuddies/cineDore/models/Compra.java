package com.binarybuddies.cineDore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "compras")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Compra
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Usuario cannot be null")
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcion", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Función cannot be null")
    @JsonManagedReference
    private Funcion funcion;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

    @Column(name = "total_pago", nullable = false, precision = 10, scale = 2)
    @Positive(message = "Total debe ser positivo")
    private BigDecimal totalPago;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.ALL)
    @JsonManagedReference
    private TicketEntrada ticket;

    @PrePersist
    protected void onCreate() {
        fechaCompra = LocalDateTime.now();
    }

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
