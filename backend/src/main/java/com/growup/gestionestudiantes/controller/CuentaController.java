package com.growup.gestionestudiantes.controller;

import com.growup.gestionestudiantes.dto.LoginDTO;
import com.growup.gestionestudiantes.model.Cuenta;
import com.growup.gestionestudiantes.service.CuentaService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    // Registrar una nueva cuenta de usuario
    @PostMapping
    public Cuenta registrarCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.registrarCuenta(cuenta);
    }

    // Registrar una nueva cuenta de usuario
    @PostMapping("/login")
    public Cuenta login(@RequestBody LoginDTO loginDTO) {

        Cuenta cuenta = cuentaService.login(loginDTO);

        if (cuenta == null) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return cuenta;
    }
}