package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Clase que almacena los datos correspondientes a los contratos
 */
@Entity
@Table(name = "Contratos")
public class Contrato {
    // Variable que almacena el id del contrato, este sirve para identificar el contrato
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContra;
    // Variable que almacena la fecha de inicio del contrato
    @Column(nullable = false)
    private LocalDate fechaInicioContra;
    // Variable que almacena la fecha de termino del contraro
    @Column(nullable = false)
    private LocalDate fechaTerminoContra;
    // Variable que almacena un PDF del contrato, el cual puede ser visualizado en pa pagina
    @Column(length = 150)
    private String pdfContra;
    // Variable que almacena el tipo de contrato, entre los roles estan el transportista y el productor
    @NotNull
    @Column(nullable = false, length = 1)
    private Character tipoContra;
    // Varible que almacena el estado del contrato, entre estos existen vigente y vencido
    @NotNull
    @Column(nullable = false, length = 1)
    private Character estadoContra;
    // Inicio de los metodos accesadores y mutadores
    public Contrato() {
    }

    public Character getEstadoContra() {
        return estadoContra;
    }

    public void setEstadoContra(Character estadoContra) {
        this.estadoContra = estadoContra;
    }

    public Long getIdContra() {
        return idContra;
    }

    public void setIdContra(Long idContra) {
        this.idContra = idContra;
    }

    public LocalDate getFechaInicioContra() {
        return fechaInicioContra;
    }

    public void setFechaInicioContra(LocalDate fechaInicioContra) {
        this.fechaInicioContra = fechaInicioContra;
    }

    public LocalDate getFechaTerminoContra() {
        return fechaTerminoContra;
    }

    public void setFechaTerminoContra(LocalDate fechaTerminoContra) {
        this.fechaTerminoContra = fechaTerminoContra;
    }

    public String getPdfContra() {
        return pdfContra;
    }

    public void setPdfContra(String pdfContra) {
        this.pdfContra = pdfContra;
    }

    public Character getTipoContra() {
        return tipoContra;
    }

    public void setTipoContra(Character tipoContra) {
        this.tipoContra = tipoContra;
    }
}
