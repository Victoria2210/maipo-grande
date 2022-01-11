package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Clase utilizada para la almacenacion de los datos correspondientes al administrador
 */
@Entity
@Table(name = "Administradores")
public class Administrador {
    // Valor auto incrementable que identifica al administrador
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdm;
    // Valor que contiene el nombre del administrador
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreAdm;
    // Valor que contiene el apellido del administrador
    @NotNull
    @Column(length = 100, nullable = false)
    private String apellidosAdm;
    // Valor que contiene el rut del administrador
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutAdm;
    // Valor que contiene el email del administrador
    @NotNull
    @Column(length = 100, nullable = false,  unique = true)
    private String emailAdm;
    // Valor que almacena la contraseña designada para el administrador
    @NotNull
    @Column(length = 60, nullable = false)
    private String contraseñaAdm;
    // Valor que almacena la fecha de la creacion de la cuenta para el administrador
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaCreacionAdm;
    // Valor que almacena el telefono de contacto del administrador
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoAdm;

    // Controlador sin parametros
    public Administrador() {
    }
    // Inicio de los metodos accesadores y mutadores
    public Long getIdAdm() {
        return idAdm;
    }

    public void setIdAdm(Long idAdm) {
        this.idAdm = idAdm;
    }

    public String getNombreAdm() {
        return nombreAdm;
    }

    public void setNombreAdm(String nombreAdm) {
        this.nombreAdm = nombreAdm;
    }

    public String getApellidosAdm() {
        return apellidosAdm;
    }

    public void setApellidosAdm(String apellidosAdm) {
        this.apellidosAdm = apellidosAdm;
    }

    public String getRutAdm() {
        return rutAdm;
    }

    public void setRutAdm(String rutAdm) {
        this.rutAdm = rutAdm;
    }

    public String getEmailAdm() {
        return emailAdm;
    }

    public void setEmailAdm(String emailAdm) {
        this.emailAdm = emailAdm;
    }

    public String getContraseñaAdm() {
        return contraseñaAdm;
    }

    public void setContraseñaAdm(String contraseñaAdm) {
        this.contraseñaAdm = contraseñaAdm;
    }

    public LocalDateTime getFechaCreacionAdm() {
        return fechaCreacionAdm;
    }

    public void setFechaCreacionAdm(LocalDateTime fechaCreacionAdm) {
        this.fechaCreacionAdm = fechaCreacionAdm;
    }

    public String getTelefonoAdm() {
        return telefonoAdm;
    }

    public void setTelefonoAdm(String telefonoAdm) {
        this.telefonoAdm = telefonoAdm;
    }
}
