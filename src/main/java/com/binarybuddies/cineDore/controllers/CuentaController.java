package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Cuenta;
import com.binarybuddies.cineDore.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
        List<Cuenta> cuentas = cuentaService.getAll();
        return ResponseEntity.ok(cuentas);
    }
}
