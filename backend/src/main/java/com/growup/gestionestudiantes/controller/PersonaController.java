package com.growup.gestionestudiantes.controller;

import com.growup.gestionestudiantes.model.Persona;
import com.growup.gestionestudiantes.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "https://visionary-froyo-2cc8b1.netlify.app"})@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaService service;

    public PersonaController(PersonaService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Persona crear(@Valid @RequestBody Persona persona) {
        return service.crearPersona(persona);
    }

    // READ ALL
    @GetMapping
    public List<Persona> listar() {
        return service.obtenerPersonas();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Persona obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id).orElse(null);
    }

    // FIND BY NUMEROIDENTIFICACION
    @GetMapping("/documento/{numeroIdentificacion}")
    public ResponseEntity<Persona> buscarPorDocumento(@PathVariable String numeroIdentificacion) {
        return service.buscarPorDocumento(numeroIdentificacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping
    public Persona actualizar(@Valid @RequestBody Persona persona) {
        return service.actualizarPersona(persona);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminarPersona(id);
    }
}