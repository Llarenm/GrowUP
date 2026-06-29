package com.growup.gestionestudiantes.service;

import com.growup.gestionestudiantes.dto.RegistroDTO;
import com.growup.gestionestudiantes.model.Cuenta;
import com.growup.gestionestudiantes.model.EstadoCuenta;
import com.growup.gestionestudiantes.model.Persona;
import com.growup.gestionestudiantes.model.Rol;
import com.growup.gestionestudiantes.repository.CuentaRepository;
import com.growup.gestionestudiantes.repository.PersonaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class RegistroService {

    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    private final CuentaRepository cuentaRepository;

    public RegistroService(PersonaRepository personaRepository, PasswordEncoder passwordEncoder, CuentaRepository cuentaRepository) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
        this.cuentaRepository = cuentaRepository;
    }


    @Transactional
    public Cuenta registrarUsuario (RegistroDTO dto) {
        // 1. Buscar si ya existe la persona
        Optional<Persona> personaExistente =
                personaRepository.findByNumeroIdentificacion(dto.getNumeroIdentificacion());

        Persona persona;
        if (personaExistente.isPresent()) {
            persona = personaExistente.get();
        } else {
            // 2. Crear persona nueva
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombres(dto.getNombres());
            nuevaPersona.setApellidos(dto.getApellidos());
            nuevaPersona.setTipoIdentificacion(dto.getTipoIdentificacion());
            nuevaPersona.setNumeroIdentificacion(dto.getNumeroIdentificacion());
            nuevaPersona.setFechaNacimiento(dto.getFechaNacimiento());
            persona = personaRepository.save(nuevaPersona);
        }

        // 3. Crear cuenta vinculada
        Cuenta cuenta = new Cuenta();
        cuenta.setEmail(dto.getEmail());
        cuenta.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        cuenta.setRol(Rol.valueOf(dto.getRol()));
        cuenta.setEstado(EstadoCuenta.ACTIVA);
        cuenta.setPersona(persona);

        return cuentaRepository.save(cuenta);
    }
}