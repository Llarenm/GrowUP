package com.growup.gestionestudiantes.service;

import com.growup.gestionestudiantes.dto.LoginDTO;
import com.growup.gestionestudiantes.model.Cuenta;
import com.growup.gestionestudiantes.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {

    @Mock
    private CuentaRepository repo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CuentaService cuentaService;

    @Test
    // hashSimulado — verifica que la contraseña se cifra antes de guardar.
    void hashSimulado () {

        //Arrange
        Cuenta cuenta = new Cuenta();
        cuenta.setContrasena("1234");
        when(passwordEncoder.encode("1234")).thenReturn("hash_simulado");

        //Act
        cuentaService.registrarCuenta(cuenta);

        //Assert
        assertEquals("hash_simulado", cuenta.getContrasena());
    }

    @Test
    // verificarRegistro — verifica que el objeto se persiste a través del repository.
    void verificarRegistro () {
        //Arrange
        Cuenta cuenta = new Cuenta();
        cuenta.setContrasena("1234");
        when(passwordEncoder.encode("1234")).thenReturn("registro_verificado");

        //Act
        cuentaService.registrarCuenta(cuenta);

        //Assert
        verify(repo).save(cuenta);
    }


    @Test
    //loginEmailNoExistente — el login devuelve null cuando el email no existe.
    void loginEmailNoExistente() {
        // Arrange
        LoginDTO login = new LoginDTO();
        login.setEmail("noexiste@gmail.com");
        login.setContrasena("1234");
        when(repo.findByEmail("noexiste@gmail.com"))
                .thenReturn(Optional.empty());

        // Act
        Cuenta resultado = cuentaService.login(login);

        // Assert
        assertNull(resultado);
    }

    @Test
    void loginEmailExistenteContrasenaIncorrecta() {
        // Arrange
        LoginDTO login = new LoginDTO();
        login.setEmail("existe@gmail.com");
        login.setContrasena("1234");
        Cuenta cuenta = new Cuenta();
        cuenta.setContrasena("hash_guardado");

        when(repo.findByEmail("existe@gmail.com"))
                .thenReturn(Optional.of((cuenta)));
        when(passwordEncoder.matches("1234", "hash_guardado"))
            .thenReturn(false);

        // Act
        Cuenta resultado = cuentaService.login(login);

        // Assert
        assertNull(resultado);


    }

    @Test
    void loginExitoso() {
        // Arrange
        LoginDTO login = new LoginDTO();
        login.setEmail("existe@gmail.com");
        login.setContrasena("1234");
        Cuenta cuenta = new Cuenta();
        cuenta.setContrasena("hash_guardado");

        when(repo.findByEmail("existe@gmail.com"))
                .thenReturn(Optional.of((cuenta)));
        when(passwordEncoder.matches("1234", "hash_guardado"))
                .thenReturn(true);

        // Act
        Cuenta resultado = cuentaService.login(login);

        // Assert
        assertEquals(cuenta, resultado);

        }


    }
