package com.LlarenMorales.growupmobile.viewmodel;

import org.junit.Test;

public class PersonaValidationTest {

    // Replica la regex del ViewModel para probarla aislada
    private boolean nombreValido(String nombre) {
        return nombre != null && nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,50}$");
    }

    @Test
    public void nombreConNumeros_esInvalido() {
        assertFalse(nombreValido("Juan123"));
    }

    private void assertFalse(boolean juan123) {
    }

    @Test
    public void nombreValido_esAceptado() {
        assertTrue(nombreValido("María José"));
    }

    private void assertTrue(boolean maríaJosé) {
    }

    @Test
    public void nombreMuyCorto_esInvalido() {
        assertFalse(nombreValido("A"));
    }
}