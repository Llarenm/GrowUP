package com.growup.gestionestudiantes.repository;

import com.growup.gestionestudiantes.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByNumeroIdentificacion(String numeroIdentificacion);

}

