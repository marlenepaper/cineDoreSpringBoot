package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "pelicula")
public class Pelicula
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int anio;

    @Column(nullable = false)
    private int duracion; // in minutes

    @Column(nullable = false)
    private String sipnosis;

    @Column(nullable = false)
    private String imagenPoster;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idClasificacion", referencedColumnName = "id", nullable = false)
    private Clasificacion clasificacion;

    @ManyToOne
    @JoinColumn(name = "idFormato", referencedColumnName = "id", nullable = false)
    private Formato formato;

    @ManyToOne
    @JoinColumn(name = "idLenguaje", referencedColumnName = "id", nullable = false)
    private Lenguaje lenguaje;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Funcion> funciones;
}
