package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cines")
public class Cine
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String horarioInvierno;

    @Column(nullable = false)
    private String horarioVerano;

    @Column(nullable = false)
    private String horarioProyecciones;

    @Column(nullable = false)
    private String localizacion;

    @Column(nullable = false)
    private String instalaciones;

    @Column(nullable = false)
    private String fechasDeCierre;

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Sala> salas;

}
