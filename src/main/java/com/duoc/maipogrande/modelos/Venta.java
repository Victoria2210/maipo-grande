package com.duoc.maipogrande.modelos;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.reverseOrder;

/**
 * Clase que almacena los valores que seran requeridos para su correspondiente implementacion dentro de la pagina WEB
 */
@Entity
@Table(name = "Ventas")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "buscarVentasParaSubasta",
                procedureName = "BUSCARVENTASPARASUBASTA",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "i",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "buscarVentasActivasProductor",
                procedureName = "BUSCARVENTASACTIVASPRODUCTOR",
                resultClasses = {Venta.class},
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
        @NamedStoredProcedureQuery(
                name = "buscarVentasActivasTran",
                procedureName = "BUSCARVENTASACTIVASTRAN",
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
        @NamedStoredProcedureQuery(
                name = "buscarVentaParaIdParaSubasta",
                procedureName = "BUSCARVENTAPORIDPARASUBASTA",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "buscarVentasParaTransporte",
                procedureName = "BUSCARVENTASPARATRANSPORTE",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "i",
                                type = Short.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "buscarVentaPorIdParaTransporte",
                procedureName = "BUSCARVENTAPORIDPARATRANSPORTE",
                resultClasses = {Venta.class},
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
        @NamedStoredProcedureQuery(
                name = "buscarVentaDetalleProdu",
                procedureName = "BUSCARVENTADETALLEPRODU",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idVenta",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProd",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "buscarVentaDetalleTran",
                procedureName = "BUSCARVENTADETALLETRAN",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idVenta",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idTran",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "traerVentasActivasPorIdCli",
                procedureName = "traerVentasActivasPorIdCli",
                resultClasses = {Venta.class},
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
        @NamedStoredProcedureQuery(
                name = "traerVentaCliente",
                procedureName = "traerVentaCliente",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idVenta",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idCli",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "rechazarVenta",
                procedureName = "rechazarVenta",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idVenta",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "pdfRuta",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "descripcionRep",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "tipoRep",
                                type = Character.class
                        )
                }
        ),
        @NamedStoredProcedureQuery(
                name = "traerVentasHistoricasPorId",
                procedureName = "traerVentasHistoricasPorId",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idCli",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "traerVentasHistoricasProductor",
                procedureName = "traerVentasHistoricasProductor",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProd",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "traerVentasHistoricasTrans",
                procedureName = "traerVentasHistoricasTrans",
                resultClasses = {Venta.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idTran",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "aceptarVenta",
                procedureName = "aceptarVenta",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idVenta",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "precioTotal",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "descripcionRep",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "pdfRuta",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "tipoRep",
                                type = Character.class
                        ),
                }
        ),
})
public class Venta {
    // Variable que almacena el id de la venta, esto se le asigna a cada uno para su correspondiente identificacion
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    // Variable que almacena el pais de origen de la venta
    @NotNull
    @Column(length = 60, nullable = false)
    private String paisOrigen;
    // Variable que almacena el tipo de venta, entre los cuales existen intero e externo
    @NotNull
    @Column(nullable = false, length = 1)
    private Character tipoVenta;
    // Variable que almacena el estado de la venta
    @Column(length = 1, nullable = false)
    private Character estadoVenta;
    // Variable que hereda de la clase Administrador, esto con el fin de verificar quien aprobo o rechazo la venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ADM")
    private Administrador administrador;
    // Varible que hereda de la clase solicitud, esto con el din de determinar la solicitud creada para la venta
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SOL")
    private Solicitud solicitud;
    // Variable que hereda de la clase OfertaProducto, esto con el fin determinar la oferta final del producto para la venta
    @OneToMany(mappedBy = "venta")
    private List<OfertaProducto> ofertaProductos;
    @OneToMany(mappedBy = "venta")
    private List<OfertaTransportista> ofertaTransportistas;
    @OneToMany(mappedBy = "venta")
    private List<Reporte> reportes;

    // Inicio de los metodos accesadores y mutadores
    public Venta() {
    }


    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<OfertaTransportista> getOfertaTransportistas() {
        return ofertaTransportistas;
    }

    public void setOfertaTransportistas(List<OfertaTransportista> ofertaTransportistas) {
        this.ofertaTransportistas = ofertaTransportistas;
    }

    public List<OfertaProducto> getOfertaProductos() {
        return ofertaProductos;
    }

    public void setOfertaProductos(List<OfertaProducto> ofertaProductos) {
        this.ofertaProductos = ofertaProductos;
    }

    public Character getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(Character estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Character getTipoVenta() {
        return tipoVenta;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void setTipoVenta(Character tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public boolean transportistaAptoParaOfertar(Venta venta, Transportista transportista) {
        Integer sumaCantidad = venta.getSolicitud().getProductoSolicitados()
                .stream()
                .map(productoSolicitado -> {
                    if (productoSolicitado.getUnidadProdS().equals("T")) {
                        return productoSolicitado.getCantidadProdS() * 1000;
                    } else {
                        return productoSolicitado.getCantidadProdS();
                    }
                })
                .reduce(0, Integer::sum);
        Integer capacidadMaximaTran = Math.toIntExact(Math.round(transportista.getCapacidadCarga() * 1000));
        return capacidadMaximaTran >= sumaCantidad;
    }

    public void ordernarTop3Transportistas() {
        if (this.ofertaTransportistas.isEmpty()) {
            return;
        }
        for (int i = this.ofertaTransportistas.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                 int tamaño = (this.ofertaTransportistas.get(j).getTransportista().getTamaño().equalsIgnoreCase("Grande") ? 3 :
                              (this.ofertaTransportistas.get(j).getTransportista().getTamaño().equalsIgnoreCase("Mediano") ? 2 :
                                      (this.ofertaTransportistas.get(j).getTransportista().getTamaño().equalsIgnoreCase("Pequeño")) ? 1 : 0));
                 int tamaño2 = (this.ofertaTransportistas.get(j+1).getTransportista().getTamaño().equalsIgnoreCase("Grande") ? 3 :
                         (this.ofertaTransportistas.get(j+1).getTransportista().getTamaño().equalsIgnoreCase("Mediano") ? 2 :
                                 (this.ofertaTransportistas.get(j+1).getTransportista().getTamaño().equalsIgnoreCase("Pequeño")) ? 1 : 0));
                if (this.ofertaTransportistas.get(j).getPrecioOfertaOfert() >= this.ofertaTransportistas.get(j + 1).getPrecioOfertaOfert() &&
                        this.ofertaTransportistas.get(j).getTransportista().getRefrigeracion() >= this.ofertaTransportistas.get(j + 1).getTransportista().getRefrigeracion() &&
                         tamaño >= tamaño2) {
                    OfertaTransportista temp = this.ofertaTransportistas.get(j);
                    this.ofertaTransportistas.set(j, this.ofertaTransportistas.get(j + 1));
                    this.ofertaTransportistas.set(j + 1, temp);
                }
            }
        }
        if (this.ofertaTransportistas.size() == 1) {
            this.ofertaTransportistas.subList(1, this.ofertaTransportistas.size()).clear();
        } else if (this.ofertaTransportistas.size() == 2) {
            this.ofertaTransportistas.subList(2, this.ofertaTransportistas.size()).clear();
        } else {
            this.ofertaTransportistas.subList(3, this.ofertaTransportistas.size()).clear();
        }
    }
    public void ordernarTop3SubastaProductos() {
        if (this.ofertaProductos.isEmpty()) {
            return;
        }
        List<OfertaProducto> ofertaProductosFiltrados = new ArrayList<>();
        Long[] idProductosSolicitados = this.ofertaProductos.stream()
                .map(ofertaProducto -> ofertaProducto.getProductoSolicitado().getIdProdS())
                .distinct()
                .toArray(Long[]::new);
        this.ofertaProductos = this.ofertaProductos.stream()
                .sorted(Comparator.comparing(OfertaProducto::getPrecioOferta)
                        .thenComparing(reverseOrder(Comparator.comparing
                                (ofertaProducto -> ofertaProducto.getProducto().getCalidadProdu()))))
                .collect(Collectors.toList());

        IntStream.range(0, idProductosSolicitados.length)
                .forEach(i -> ofertaProductos
                        .stream()
                        .filter(ofertaProducto -> ofertaProducto.getProductoSolicitado().getIdProdS().equals(idProductosSolicitados[i]))
                        .limit(3)
                        .forEach(ofertaProductosFiltrados::add));
        this.ofertaProductos = ofertaProductosFiltrados;
    }
}
