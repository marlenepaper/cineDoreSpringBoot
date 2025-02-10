package com.binarybuddies.cineDore.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cuenta")
public class Cuenta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;


    @Column(name = "estado_cuenta", nullable = false)
    private String estadoCuenta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private String idUsuario;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sesion> sesiones;
}
