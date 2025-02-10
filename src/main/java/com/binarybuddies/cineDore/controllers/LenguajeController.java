package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.services.LenguajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lenguajes")
@CrossOrigin(origins = "*")
public class LenguajeController {

    @Autowired
    private LenguajeService lenguajeService;

    @GetMapping
    public List<Lenguaje> getAllLenguajes() {
        return lenguajeService.getAllLenguajes();
    }
}
