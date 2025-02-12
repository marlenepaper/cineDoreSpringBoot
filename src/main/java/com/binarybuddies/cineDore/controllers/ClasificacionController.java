package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Clasificacion;
import com.binarybuddies.cineDore.services.ClasificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clasificaciones")
@CrossOrigin(origins = "*")
public class ClasificacionController {

    @Autowired
    private ClasificacionService clasificacionService;

    @GetMapping
    public ResponseEntity<List<Clasificacion>> getAllClasificaciones() {
        List<Clasificacion> clasificaciones = clasificacionService.getAll();
        return ResponseEntity.ok(clasificaciones);
    }
}
