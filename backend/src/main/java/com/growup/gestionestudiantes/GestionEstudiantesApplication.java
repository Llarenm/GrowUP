package com.growup.gestionestudiantes;

import com.growup.gestionestudiantes.service.PersonaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.growup.gestionestudiantes.repository.PersonaRepository;
import com.growup.gestionestudiantes.model.Persona;

@SpringBootApplication
public class GestionEstudiantesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionEstudiantesApplication.class, args);
    }

    }