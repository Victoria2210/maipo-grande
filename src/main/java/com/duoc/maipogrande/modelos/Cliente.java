package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Clase que almacena los valores correspondientes al cliente, dentro del cual algunos metodos se ejecutan para cumplir su funcion determinada
 */
@Entity
@Table(name = "Clientes")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "buscarCliPorEmail",
                procedureName = "BUSCARCLIENTEPOREMAIL",
                resultClasses = {Cliente.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "email",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        )
        ,
        @NamedStoredProcedureQuery(
                name = "buscarCliPorId",
                procedureName = "BUSCARCLIENTEPORID",
                resultClasses = {Cliente.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        )
}
)
public class Cliente {
    // Valor que identifica al cliente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCli;
    // Valor que determina el nombre del cliente, este valor no puede estar vacio
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreCli;
    // Valor que determina el apellido del cliente, este valor no puede estar vacio
    @NotNull
    @Column(length = 100, nullable = false)
    private String apellidosCli;
    // Valor que determina el rut del cliente, este valor no puede estar vacio
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutCli;
    // Valor que determina el email del cliente, este valor no puede estar vacio
    @NotNull
    @Column(length = 100, nullable = false, unique = true)
    private String emailCli;
    // valor que almacena la contraseña designada por el cliente, este valor no puede estar vacio
    @NotNull
    @Column(length = 60, nullable = false)
    private String contraseñaCli;
    // Valor que determina la fecha de creacion de la cuenta del cliente
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaCreacionCli;
    // Valor que determina el numero de contacto del cliente, este valor es proporcionado por este
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoCli;
    // Valor que determina el tipo de cliente con el que se registra, este puede variar entre interno y externo
    @NotNull
    @Column(length = 1, nullable = false)
    private Character tipoCli;
    // Inicio de los metodos accesadores y mutadores
    public Cliente() {
    }

    public Long getIdCli() {
        return idCli;
    }

    public void setIdCli(Long idCli) {
        this.idCli = idCli;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getApellidosCli() {
        return apellidosCli;
    }

    public void setApellidosCli(String apellidosCli) {
        this.apellidosCli = apellidosCli;
    }

    public String getRutCli() {
        return rutCli;
    }

    public void setRutCli(String rutCli) {
        this.rutCli = rutCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getContraseñaCli() {
        return contraseñaCli;
    }

    public void setContraseñaCli(String contraseñaCli) {
        this.contraseñaCli = contraseñaCli;
    }

    public LocalDateTime getFechaCreacionCli() {
        return fechaCreacionCli;
    }

    public void setFechaCreacionCli(LocalDateTime fechaCreacionCli) {
        this.fechaCreacionCli = fechaCreacionCli;
    }

    public String getTelefonoCli() {
        return telefonoCli;
    }

    public void setTelefonoCli(String telefonoCli) {
        this.telefonoCli = telefonoCli;
    }

    public Character getTipoCli() {
        return tipoCli;
    }

    public void setTipoCli(Character tipoCli) {
        this.tipoCli = tipoCli;
    }
}
