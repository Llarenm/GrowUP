package com.growup.gestionestudiantes.controller;

import com.growup.gestionestudiantes.dto.RegistroDTO;
import com.growup.gestionestudiantes.model.Cuenta;
import com.growup.gestionestudiantes.service.RegistroService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "https://growupedu.netlify.app"})
@RequestMapping("/registro")
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @PostMapping
    public Cuenta registrar(@RequestBody RegistroDTO dto) {
        return registroService.registrarUsuario(dto);
    }
}