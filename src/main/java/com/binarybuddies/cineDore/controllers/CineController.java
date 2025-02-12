package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Cine;
import com.binarybuddies.cineDore.services.CineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cines")
@CrossOrigin(origins = "*")
public class CineController {

    @Autowired
    private CineService cineService;

    @GetMapping
    public ResponseEntity<List<Cine>> getAllCines() {
        List<Cine> cines = cineService.getAll();
        return ResponseEntity.ok(cines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cine>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.cineService.getCineById(id));
    }
}
