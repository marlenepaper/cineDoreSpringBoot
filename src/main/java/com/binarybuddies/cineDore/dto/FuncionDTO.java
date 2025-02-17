package com.binarybuddies.cineDore.dto;

import lombok.Data;

@Data
public class FuncionDTO {
    private String fechaHora;
    private String sala;

    public FuncionDTO() {}

    public FuncionDTO(String fechaHora, String sala) {
        this.fechaHora = fechaHora;
        this.sala = sala;
    }

}
