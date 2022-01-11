package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * Clase utilizada para la almacenacion de los datos correspondientes a la Boleta
 */
@Entity
@Table(name = "Boletas")
public class Boleta {
    // Valor auto incrementable que identifica la boleta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBol;
    // Valor que almacena la fecha de emision de la boleta, este dato debe ser de la fecha actual
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaEmisionBol;
    // Valor que almacena el precio total en el final de la boleta, este valor no puede ser menor a 1
    @Min(1)
    @Column(nullable = false)
    private Integer precioTotal;
    // Valor que hereda de la tabla VENTAS el cual identifica la venta correspondiente
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEN")
    private Venta venta;
    // Inicio de los accesadores y mutadores
    public Boleta() {
    }

    public Boleta(LocalDateTime fechaEmisionBol, Integer precioTotal, Long idVenta) {
        this.fechaEmisionBol = fechaEmisionBol;
        this.precioTotal = precioTotal;
        this.venta = new Venta();
        this.venta.setIdVenta(idVenta);
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Long getIdBol() {
        return idBol;
    }

    public void setIdBol(Long idBol) {
        this.idBol = idBol;
    }

    public LocalDateTime getFechaEmisionBol() {
        return fechaEmisionBol;
    }

    public void setFechaEmisionBol(LocalDateTime fechaEmisionBol) {
        this.fechaEmisionBol = fechaEmisionBol;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
