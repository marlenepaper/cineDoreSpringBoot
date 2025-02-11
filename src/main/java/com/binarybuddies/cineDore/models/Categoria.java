package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaPelicula categoria;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pelicula> peliculas;

    public Categoria() {}

    public Categoria(CategoriaPelicula categoria) {
        this.categoria = categoria;
    }

    // Método útil para obtener el nombre para mostrar
    public String getNombreCategoria() {
        return categoria.getDisplayName();
    }
}
