package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Clase que almacena los datos correspondientes de las ofertas realizadas a los transportistas, para luego realizar las acciones correspondientes
 */
@Entity
@Table(name = "OFERTAS_TRANSPORTISTAS")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "crearOfertaTransportista",
                procedureName = "CREAROFERTATRANSPORTISTA",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "precioOferta",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idTran",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idVenta",
                                type = Long.class
                        ),

                }
        ),
})
public class OfertaTransportista {
    // Dato que almacena de forma progresiva la id de la oferta ofrecida para el transportista
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOfert;
    // Dato que almcena el monto total de la oferta ofrecida
    @Min(1)
    @Column(nullable = false)
    private Integer precioOfertaOfert;
    // Dato que hereda de la clase transportista, con esta variable podemos determinar quien es el responsable de realizar el transporte
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TRAN")
    private Transportista transportista;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VENTA")
    private  Venta venta;
    // Inicio de los metodos accesadores y mutadores
    public OfertaTransportista() {
        transportista = new Transportista();
        venta = new Venta();
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Long getIdOfert() {
        return idOfert;
    }

    public void setIdOfert(Long idOfert) {
        this.idOfert = idOfert;
    }

    public Integer getPrecioOfertaOfert() {
        return precioOfertaOfert;
    }

    public void setPrecioOfertaOfert(Integer precioOfertaOfert) {
        this.precioOfertaOfert = precioOfertaOfert;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }
}
