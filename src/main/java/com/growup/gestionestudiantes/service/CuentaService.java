package com.growup.gestionestudiantes.service;

import com.growup.gestionestudiantes.model.Cuenta;
import com.growup.gestionestudiantes.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository repo;

    public CuentaService(CuentaRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Cuenta registrarCuenta(Cuenta cuenta) {
        return repo.save(cuenta);
    }

    // READ ALL
    public List<Cuenta> obtenerCuentas() {
        return repo.findAll();
    }

    // READ BY ID
    public Optional<Cuenta> obtenerPorId(Integer id) {
        return repo.findById(id);
    }

    // DELETE
    public void eliminarCuenta(Integer id) {
        repo.deleteById(id);
    }

    // LOGIN
    public Optional<Cuenta> login(String email) {
        return repo.findByEmail(email);
    }
}