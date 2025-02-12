package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Formato;
import com.binarybuddies.cineDore.services.FormatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/formatos")
@CrossOrigin(origins = "*")
public class FormatoController {

    @Autowired
    private FormatoService formatoService;

    @GetMapping
    public ResponseEntity<List<Formato>> getAllFormatos() {
        List<Formato> formatos = formatoService.getAll();
        return ResponseEntity.ok(formatos);
    }
}
