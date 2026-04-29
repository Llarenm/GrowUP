package com.growup.gestionestudiantes.service;

import com.growup.gestionestudiantes.model.Persona;
import com.growup.gestionestudiantes.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository repo;

    public PersonaService(PersonaRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Persona crearPersona(Persona persona) {
        return repo.save(persona);
    }

    // READ
    public List<Persona> obtenerPersonas() {
        return repo.findAll();
    }

    public Optional<Persona> obtenerPorId(Integer id) {
        return repo.findById(id);
    }

    // UPDATE
    public Persona actualizarPersona(Persona persona) {
        return repo.save(persona);
    }

    // DELETE
    public void eliminarPersona(Integer id) {
        repo.deleteById(id);
    }
}