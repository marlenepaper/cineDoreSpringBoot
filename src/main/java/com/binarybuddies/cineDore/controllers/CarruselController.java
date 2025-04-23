package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Carrusel;
import com.binarybuddies.cineDore.services.CarruselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carrusel")  // Es mejor tener un prefijo "api" para las rutas REST
public class CarruselController {

    @Autowired
    private CarruselService carruselService;

    @GetMapping
    public List<Carrusel> getCarruselDataForCurrentMonth() {
        return carruselService.getCarruselDataForCurrentMonth();
    }
}
