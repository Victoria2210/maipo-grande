package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Clase que se utiliza para almacenar las varibles para los reportes, los cuales seran utilizados con posterioridad para gener informes de las solicitudes
 */
@Entity
@Table(name = "Reportes")
public class Reporte {
    // Variable que se utiliza para almacenar el id del reporte, este es auto incrementable y es asignado a cada reporte para su facil identificacion
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRep;
    // Variable que se utiliza para almacenar el asunto del reporte
    @NotNull
    @Column(length = 150, nullable = false)
    private String pdfRuta;
    // Variable que se utiliza para almacenar la descripcion del reporte
    @NotNull
    @Column(length = 500, nullable = false)
    private String descripcionRep;
    // Variable que se utiliza para almacenar el tipo de reporte, esto conel fin de identificarla mas adelante
    @NotNull
    @Column(nullable = false, length = 1)
    private Character tipoRep;
    // Variable que hereda de la clase Venta con el fin de identificar a donde pertenece el reporte
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEN")
    private Venta venta;
    // Inicio de los metodos accesadores y mutadores
    public Reporte() {
    }

    public Reporte(@NotNull String pdfRuta, @NotNull String descripcionRep, @NotNull Character tipoRep, Long idVenta) {
        this.pdfRuta = pdfRuta;
        this.descripcionRep = descripcionRep;
        this.tipoRep = tipoRep;
        this.venta = new Venta();
        this.venta.setIdVenta(idVenta);
    }

    public Long getIdRep() {
        return idRep;
    }

    public void setIdRep(Long idRep) {
        this.idRep = idRep;
    }

    public String getPdfRuta() {
        return pdfRuta;
    }

    public void setPdfRuta(String pdfRuta) {
        this.pdfRuta = pdfRuta;
    }

    public String getDescripcionRep() {
        return descripcionRep;
    }

    public void setDescripcionRep(String descripcionRep) {
        this.descripcionRep = descripcionRep;
    }

    public Character getTipoRep() {
        return tipoRep;
    }

    public void setTipoRep(Character tipoRep) {
        this.tipoRep = tipoRep;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
