package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.PeliculaDTO;
import com.binarybuddies.cineDore.services.PeliculaDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculasDTO")
@CrossOrigin(origins = "*")
public class PeliculaDTOController {


    @Autowired
    private PeliculaDTOService peliculaDTOService;

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getAllPeliculas() {
        List<PeliculaDTO> peliculasDTO = peliculaDTOService.getAll();
        return ResponseEntity.ok(peliculasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getById(@PathVariable long id) {
        return peliculaDTOService.getPeliculaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

