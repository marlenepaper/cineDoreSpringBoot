package com.binarybuddies.cineDore.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Column(name = "estado_cuenta", nullable = false)
    private String estadoCuenta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario",referencedColumnName = "id", nullable = false, unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sesion> sesiones= new ArrayList<>();
}
