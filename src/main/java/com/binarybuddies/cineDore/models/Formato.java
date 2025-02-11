package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "formatos")
public class Formato
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String detalle;

    private String color;

    @OneToMany(mappedBy = "formato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pelicula> peliculas;
}
