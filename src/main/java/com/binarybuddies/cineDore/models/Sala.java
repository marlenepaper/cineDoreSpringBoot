package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "salas")
public class Sala
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String salas;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Funcion> funciones;

    @ManyToOne
    @JoinColumn(name = "id_cine", referencedColumnName = "id", nullable = false)
    private Cine cine;
}
