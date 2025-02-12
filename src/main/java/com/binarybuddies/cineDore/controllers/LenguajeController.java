package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Lenguaje;
import com.binarybuddies.cineDore.services.LenguajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/lenguajes")
@CrossOrigin(origins = "*")
public class LenguajeController {

    @Autowired
    private LenguajeService lenguajeService;

    @GetMapping
    public ResponseEntity<List<Lenguaje>> getAllLenguajes() {
        List<Lenguaje> lenguajes = lenguajeService.getAll();
        return ResponseEntity.ok(lenguajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Lenguaje>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.lenguajeService.getLenguajeById(id));
    }
}
