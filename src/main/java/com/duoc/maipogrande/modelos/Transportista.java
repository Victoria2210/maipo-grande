package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Clase utilizada para el almacenamiento de las variables asignadas al transportista
 */
@Entity
@Table(name = "Transportistas")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "buscarTranPorEmail",
                procedureName = "BUSCARTRANSPORTISTAPOREMAIL",
                resultClasses = {Transportista.class},
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
        ),
        @NamedStoredProcedureQuery(
                name = "buscarTranPorId",
                procedureName = "BUSCARTRANSPORTISTAPORID",
                resultClasses = {Transportista.class},
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
        ),
}
)
public class Transportista {
    // Variable que alamacena el id del transportista, este facilita la identificacion del transportista
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTran;
    // Variable que almacena el nombre del transportista
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreTran;
    // Variable que almacena el apellido del transportista
    @Column(length = 100)
    private String apellidosTran;
    // Varible que almacena el rut del transportista
    @NotNull
    @Column(length = 11, nullable = false)
    private String rutTran;
    // Variable que almacena el email de transportista, esto como medio de comunicacion e identificacion dentro de la pagina
    @NotNull
    @Column(length = 80, nullable = false,  unique = true)
    private String emailTran;
    // Variable que alamcena la contraseña asignada por el transportista a la cuenta
    @NotNull
    @Column(length = 60, nullable = false)
    private String contraseñaTran;
    // Variable que alamacena la fecha de creacion de la cuenta
    @NotNull
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaCreacion;
    // Variable que almacena el numero de contacto del transportista
    @NotNull
    @Column(length = 12, nullable = false)
    private String telefonoTran;
    // Variable que almacena la patente del vehiculo que conduce el transportista
    @NotNull
    @Column(length = 6, nullable = false)
    private String patente;
    // Variable que almacena el tamaño del transporte para ver la magnitud de este
    @NotNull
    @Column(length = 30, nullable = false)
    private String tamaño;
    // Variable que almacena la capacidad del transporte
    @NotNull
    @Column(nullable = false)
    private Double capacidadCarga;
    // Variable almacena el estado del transporte, esto con el fin de saber si cuenta o no con una correspondiente refrigeracion para el transporte de la carga asignada
    @NotNull
    @Column(length = 1, nullable = false)
    private Character refrigeracion;
    // Varible que hereda de contrato
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CONTRA")
    private Contrato contrato;
    // Inicio de los metodos accesadores y mutadores
    public Transportista() {
    }

    public Long getIdTran() {
        return idTran;
    }

    public void setIdTran(Long idTran) {
        this.idTran = idTran;
    }

    public String getNombreTran() {
        return nombreTran;
    }

    public void setNombreTran(String nombreTran) {
        this.nombreTran = nombreTran;
    }

    public String getApellidosTran() {
        return apellidosTran;
    }

    public void setApellidosTran(String apellidosTran) {
        this.apellidosTran = apellidosTran;
    }

    public String getRutTran() {
        return rutTran;
    }

    public void setRutTran(String rutTran) {
        this.rutTran = rutTran;
    }

    public String getEmailTran() {
        return emailTran;
    }

    public void setEmailTran(String emailTran) {
        this.emailTran = emailTran;
    }

    public String getContraseñaTran() {
        return contraseñaTran;
    }

    public void setContraseñaTran(String contrasekloiñaTran) {
        this.contraseñaTran = contraseñaTran;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTelefonoTran() {
        return telefonoTran;
    }

    public void setTelefonoTran(String telefonoTran) {
        this.telefonoTran = telefonoTran;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public Double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(Double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public Character getRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(Character refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
