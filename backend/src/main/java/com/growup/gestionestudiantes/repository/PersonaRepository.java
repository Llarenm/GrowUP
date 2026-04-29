package com.growup.gestionestudiantes.repository;

import com.growup.gestionestudiantes.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
