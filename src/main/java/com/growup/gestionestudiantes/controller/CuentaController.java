package com.growup.gestionestudiantes.controller;

import com.growup.gestionestudiantes.model.Cuenta;
import com.growup.gestionestudiantes.service.CuentaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public Cuenta registrarCuenta(@RequestBody Cuenta cuenta) {

        return cuentaService.registrarCuenta(cuenta);
    }
}