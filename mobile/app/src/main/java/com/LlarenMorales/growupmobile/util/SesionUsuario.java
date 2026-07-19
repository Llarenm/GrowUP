package com.LlarenMorales.growupmobile.util;

import com.LlarenMorales.growupmobile.data.model.Cuenta;
import com.LlarenMorales.growupmobile.data.model.Rol;

public class SesionUsuario {
    private static SesionUsuario instance;
    private Cuenta cuentaActual;

    private SesionUsuario() {}

    public static SesionUsuario getInstance() {
        if (instance == null) instance = new SesionUsuario();
        return instance;
    }

    public void iniciarSesion(Cuenta cuenta) { this.cuentaActual = cuenta; }
    public void cerrarSesion() { this.cuentaActual = null; }
    public Cuenta getCuentaActual() { return cuentaActual; }

    public boolean esCoordinador() {
        return cuentaActual != null && cuentaActual.getRol() == Rol.COORDINADOR;
    }
}