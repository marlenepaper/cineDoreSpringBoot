package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.PeliculaDTO;
import com.binarybuddies.cineDore.services.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name= "Peliculas", description = "Operaciones con peliculas")
@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Operation(summary = "Obtener lista de peliculas dto")
    @ApiResponse(responseCode = "200", description = "Lista de peliculas dto encontrada")
    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getAllPeliculas() {
        List<PeliculaDTO> peliculasDTO = peliculaService.getAll();
        return ResponseEntity.ok(peliculasDTO);
    }

    @Operation(summary = "Obtener pelicula dto por ID de pelicula")
    @ApiResponse(responseCode = "200", description = "Pelicula dto encontrada")
    @ApiResponse(responseCode = "404", description = "Pelicula no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getById(@PathVariable long id) {
        return peliculaService.getPeliculaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
