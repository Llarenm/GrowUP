package com.growup.gestionestudiantes.repository;

import com.growup.gestionestudiantes.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository
        extends JpaRepository<Cuenta, Integer> {

    Optional<Cuenta> findByEmail(String email);
}