package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Clasificacion;
import com.binarybuddies.cineDore.services.ClasificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clasificacion>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.clasificacionService.getClasificacionById(id));
    }
}
