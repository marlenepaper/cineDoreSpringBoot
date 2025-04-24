package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Categoria;
import com.binarybuddies.cineDore.models.Pelicula;
import com.binarybuddies.cineDore.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Categorias", description = "Operaciones con categorias de peliculas")
@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Obtener lista de categorias")
    @ApiResponse(responseCode = "200", description = "Lista de categorias encontrada")
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias=categoriaService.getAll();
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Obtener categoria por ID")
    @Parameter(name = "id", required = true)
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.categoriaService.getCategoriaById(id));
    }
}
