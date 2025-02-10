package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Cine;
import com.binarybuddies.cineDore.services.CineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cines")
@CrossOrigin(origins = "*")
public class CineController {

    @Autowired
    private CineService cineService;

    @GetMapping
    public List<Cine> getAllCines() {
        return cineService.getAllCines();
    }
}
