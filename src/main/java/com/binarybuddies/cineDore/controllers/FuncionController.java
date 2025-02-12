package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Funcion;
import com.binarybuddies.cineDore.services.FuncionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/funciones")
@CrossOrigin(origins = "*")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping
    public ResponseEntity<List<Funcion>> getAllFunciones() {
        List<Funcion> funciones = funcionService.getAll();
        return ResponseEntity.ok(funciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Funcion>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.funcionService.getFuncionById(id));
    }
}
