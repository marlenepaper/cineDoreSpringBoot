package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "sala")
public class Sala
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroSala;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Funcion> funciones;

    @ManyToOne
    @JoinColumn(name = "id_cine", nullable = false)
    private Cine cine;
}
