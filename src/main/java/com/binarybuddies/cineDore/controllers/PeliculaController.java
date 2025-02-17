package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.PeliculaDTO;
import com.binarybuddies.cineDore.models.Pelicula;
import com.binarybuddies.cineDore.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getAllPeliculas() {
        List<PeliculaDTO> peliculas = peliculaService.getAll();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getById(@PathVariable long id) {
        return peliculaService.getPeliculaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
