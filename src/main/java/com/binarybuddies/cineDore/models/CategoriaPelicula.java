package com.binarybuddies.cineDore.models;

public enum CategoriaPelicula
{
    ACCION("Acción"),
    AVENTURA("Aventura"),
    ANIMACION("Animación"),
    COMEDIA("Comedia"),
    DOCUMENTAL("Documental"),
    DRAMA("Drama"),
    FANTASIA("Fantasía"),
    CIENCIA_FICCION("Ciencia Ficción"),
    TERROR("Terror"),
    MISTERIO("Misterio"),
    MUSICAL("Musical"),
    ROMANCE("Romance"),
    THRILLER("Thriller"),
    CRIMEN("Crimen"),
    GUERRA("Guerra"),
    WESTERN("Western"),
    DEPORTES("Deportes"),
    FAMILIAR("Familiar"),
    HISTORICA("Histórica"),
    BIOGRAFIA("Biografía");

    private final String displayName;

    CategoriaPelicula(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
