package com.growup.gestionestudiantes.service;

import com.growup.gestionestudiantes.model.Cuenta;
import com.growup.gestionestudiantes.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import com.growup.gestionestudiantes.dto.LoginDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository repo;
    private final PasswordEncoder passwordEncoder;

    public CuentaService(CuentaRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE
    public Cuenta registrarCuenta(Cuenta cuenta) {
        cuenta.setContrasena(passwordEncoder.encode(cuenta.getContrasena()));
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
    public Cuenta login(LoginDTO loginDTO) {

        Optional<Cuenta> cuentaOpt =
                repo.findByEmail(loginDTO.getEmail());

        if (cuentaOpt.isEmpty()) {
            return null;
        }

        Cuenta cuenta = cuentaOpt.get();

        if (!passwordEncoder.matches(loginDTO.getContrasena(), cuenta.getContrasena())) {
            return null;
        }

        return cuenta;
    }
}