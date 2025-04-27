package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.CarruselDTO;
import com.binarybuddies.cineDore.services.CarruselService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Carrusel", description = "Operaciones relacionadas con el carrusel de películas")
@RestController
@RequestMapping("/carrusel")
public class CarruselController {

    @Autowired
    private CarruselService carruselService;

    @Operation(summary = "Obtener datos del carrusel del mes actual")
    @ApiResponse(responseCode = "200", description = "Datos del carrusel obtenidos correctamente")
    @GetMapping
    public List<CarruselDTO> getCarruselDataForCurrentMonth() {
        return carruselService.getCarruselDataForCurrentMonth();
    }
}
