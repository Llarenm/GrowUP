package com.LlarenMorales.growupmobile.data.model;

public class LoginDTO {
    private String email;
    private String contrasena;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LoginDTO(String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
    }
    // getters y setters
}