package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * clase que permite el almacenamiento de los valores que se asignaran al consultor
 */
@Entity
@Table(name = "Consultores")
public class Consultor {
    // Valor autoincrementable que identifica al consultor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCon;
    // Valor que almacena el nombre del consultor, este dato es ingresado por el administrador quew registra al consultor
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreCon;
    // Valor que almacena el apellido del consultor, este es ingresado por el administrador el cual registra al consultor
    @NotNull
    @Column(length = 100, nullable = false)
    private String apellidosCon;
    // Valor que almacena el rut del consultor
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutCon;
    // Valor que almacena el email del consultor
    @NotNull
    @Column(length = 100, nullable = false,  unique = true)
    private String emailCon;
    // Valor que almacena la contraseña del consultor, esta es ingresado por este mismo
    @NotNull
    @Column(length = 60, nullable = false)
    private String contraseñaCon;
    // Valor que almacena la fecha de creacion de la cuenta
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaCreacionCon;
    // Valor que almacena un numero de contacto, en este caso el de un telefono celular
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoCon;
    // Valor que hereda de la clase Administrador
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ADM")
    private Administrador administrador;
    // Inicio de los metodos de accesadores y mutadores
    public Consultor() {
    }

    public Long getIdCon() {
        return idCon;
    }

    public void setIdCon(Long idCon) {
        this.idCon = idCon;
    }

    public String getNombreCon() {
        return nombreCon;
    }

    public void setNombreCon(String nombreCon) {
        this.nombreCon = nombreCon;
    }

    public String getApellidosCon() {
        return apellidosCon;
    }

    public void setApellidosCon(String apellidosCon) {
        this.apellidosCon = apellidosCon;
    }

    public String getRutCon() {
        return rutCon;
    }

    public void setRutCon(String rutCon) {
        this.rutCon = rutCon;
    }

    public String getEmailCon() {
        return emailCon;
    }

    public void setEmailCon(String emailCon) {
        this.emailCon = emailCon;
    }

    public String getContraseñaCon() {
        return contraseñaCon;
    }

    public void setContraseñaCon(String contraseñaCon) {
        this.contraseñaCon = contraseñaCon;
    }

    public LocalDateTime getFechaCreacionCon() {
        return fechaCreacionCon;
    }

    public void setFechaCreacionCon(LocalDateTime fechaCreacionCon) {
        this.fechaCreacionCon = fechaCreacionCon;
    }

    public String getTelefonoCon() {
        return telefonoCon;
    }

    public void setTelefonoCon(String telefonoCon) {
        this.telefonoCon = telefonoCon;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
