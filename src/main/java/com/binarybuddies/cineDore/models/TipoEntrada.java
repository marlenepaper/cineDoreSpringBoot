package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tipo_entradas")
public class TipoEntrada
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private Double precio;

    @OneToMany(mappedBy = "tipoEntrada")
    private List<DetalleTicket> detalles = new ArrayList<>();
}
