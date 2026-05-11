package com.growup.gestionestudiantes.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "persona")
public class Persona {
    public Persona() {
    }

    public Persona(Integer id, String nombres, String apellidos,
                   String tipoIdentificacion, String numeroIdentificacion,
                   LocalDate fechaNacimiento) {

        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_persona")
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "El nombre solo puede contener letras"
    )
    @Column(name = "Nombres")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$",
            message = "El apellido solo puede contener letras"
    )
    @Column(name = "Apellidos")
    private String apellidos;


    @NotBlank(message = "El tipo de documento es obligatorio")
    @Pattern(
            regexp = "^(CC|CE|TI|PPT)$",
            message = "El tipo de documento debe ser CC, CE, TI o PPT"
    )
    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;

    @NotBlank(message = "El número de identificación es obligatorio")
    @Size(min = 8, max = 15, message = "La longitud de número de identificación es incorrecta")
    @Pattern(
            regexp = "^[a-zA-Z0-9]+$",
            message = "El número de identificación solo puede contener números y letras"
    )
    @Column(name = "numero_identificacion", unique = true)
    private String numeroIdentificacion;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    @Column(name = "Fecha_nacimiento")
    private LocalDate fechaNacimiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}