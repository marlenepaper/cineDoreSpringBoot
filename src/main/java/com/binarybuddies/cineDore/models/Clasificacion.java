package com.binarybuddies.cineDore.models;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "clasificacion")
public class Clasificacion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "clasificacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pelicula> peliculas;
}
