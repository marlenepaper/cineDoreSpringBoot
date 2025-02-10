package com.binarybuddies.cineDore.models;


import jakarta.persistence.*;

@Entity
@Table(name = "sesion")
public class Sesion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuenta idCuenta;
}
