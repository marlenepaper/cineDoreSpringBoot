package com.binarybuddies.cineDore.models;

public enum ClasificacionEdad
{
    TODOS("Apta para todos los públicos", "TP"),
    SIETE("No recomendada para menores de 7 años", "+7"),
    DOCE("No recomendada para menores de 12 años", "+12"),
    DIECISEIS("No recomendada para menores de 16 años", "+16"),
    DIECIOCHO("No recomendada para menores de 18 años", "+18");

    private final String descripcion;
    private final String codigo;

    ClasificacionEdad(String descripcion, String codigo) {
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }
}
