package com.binarybuddies.cineDore.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String telefono;
    private String identificacion;
    private LocalDate fechaNacimiento;
    private String contrasenia;
}
