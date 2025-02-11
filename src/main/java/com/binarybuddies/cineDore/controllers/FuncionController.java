package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Funcion;
import com.binarybuddies.cineDore.services.FuncionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/funciones")
@CrossOrigin(origins = "*")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping
    public List<Funcion> getAllFunciones() {
        return funcionService.getAll();
    }
}
