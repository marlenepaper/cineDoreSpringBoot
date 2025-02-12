package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Categoria;
import com.binarybuddies.cineDore.models.Pelicula;
import com.binarybuddies.cineDore.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias=categoriaService.getAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.categoriaService.getCategoriaById(id));
    }
}
