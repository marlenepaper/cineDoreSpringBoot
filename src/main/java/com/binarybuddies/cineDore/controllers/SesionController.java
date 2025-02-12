package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Sesion;
import com.binarybuddies.cineDore.services.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/sesiones")
@CrossOrigin(origins = "*")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping
    public ResponseEntity<List<Sesion>> getAllSesiones() {
        List<Sesion> sesiones = sesionService.getAll();
        return ResponseEntity.ok(sesiones);
    }
}
