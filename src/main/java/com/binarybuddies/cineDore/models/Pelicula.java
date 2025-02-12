package com.binarybuddies.cineDore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "peliculas")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @JsonBackReference
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idClasificacion", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Clasificacion clasificacion;

    @ManyToOne
    @JoinColumn(name = "idFormato", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Formato formato;

    @ManyToOne
    @JoinColumn(name = "idLenguaje", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Lenguaje lenguaje;

    @ManyToOne
    @JoinColumn(name = "idColor", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Color color;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Funcion> funciones = new ArrayList<>();
}
