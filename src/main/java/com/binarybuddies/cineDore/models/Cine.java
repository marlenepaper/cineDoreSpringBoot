package com.binarybuddies.cineDore.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
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

//    @Column(nullable = false)
//    private String fechasDeCierre;

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Sala> salas= new ArrayList<>();

}
