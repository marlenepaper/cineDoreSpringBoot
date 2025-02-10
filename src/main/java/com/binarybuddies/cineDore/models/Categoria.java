package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pelicula> peliculas;
}
