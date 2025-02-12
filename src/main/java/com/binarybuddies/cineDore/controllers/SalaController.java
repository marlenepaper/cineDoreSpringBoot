package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Sala;
import com.binarybuddies.cineDore.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/salas")
@CrossOrigin(origins = "*")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public ResponseEntity<List<Sala>> getAllSalas() {
        List<Sala> salas = salaService.getAll();
        return ResponseEntity.ok(salas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sala>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.salaService.getSalaById(id));
    }
}
