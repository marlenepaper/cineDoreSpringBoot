package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Clasificacion;
import com.binarybuddies.cineDore.services.ClasificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clasificaciones")
@CrossOrigin(origins = "*")
public class ClasificacionController {

    @Autowired
    private ClasificacionService clasificacionService;

    @GetMapping
    public List<Clasificacion> getAllClasificaciones() {
        return clasificacionService.getAllClasificaciones();
    }
}
