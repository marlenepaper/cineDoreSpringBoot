package com.binarybuddies.cineDore.dto;

import lombok.Data;

/**
 * DTO para las funciones
 */

@Data
public class FuncionDTO {
    private Long id;
    private String fechaHora;
    private String sala;

    public FuncionDTO() {}

    public FuncionDTO(Long id, String fechaHora, String sala) {
        this.id= id;
        this.fechaHora = fechaHora;
        this.sala = sala;
    }

}
