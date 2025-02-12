package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Color;
import com.binarybuddies.cineDore.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/colores")
@CrossOrigin(origins = "*")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<Color>> getAllColores() {
        List<Color> colores = colorService.getAll();
        return ResponseEntity.ok(colores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Color>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.colorService.getColorById(id));
    }
}
