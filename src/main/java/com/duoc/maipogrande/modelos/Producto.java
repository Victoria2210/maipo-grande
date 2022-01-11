package com.duoc.maipogrande.modelos;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.time.LocalDateTime;

/**
 * Clase utilizada para guardas las variables requeridas para la futura implementacion, en este caso se almacenan los datos del producto
 */
@Entity
@Table(name = "Productos")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "buscarProductosPorIdProd",
                procedureName = "BUSCARPRODUCTOSPORIDPROD",
                resultClasses = {Producto.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Long.class
                        ),
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
                name = "productosDisponibles",
                procedureName = "PRODUCTOSDISPONIBLES",
                resultClasses = {Producto.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProductoSolicitado",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProd",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "tipoVenta",
                                type = Character.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.REF_CURSOR,
                                name = "q",
                                type = void.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "crearProducto",
                procedureName = "CREARPRODUCTO",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "nombre",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "precio",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "unidadMasa",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "imagen",
                                type = Blob.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "stock",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "tipo",
                                type = Character.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "calidad",
                                type = Byte.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "fechaIngreso",
                                type = LocalDateTime.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProd",
                                type = Long.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "buscarProductosPorId",
                procedureName = "BUSCARPRODUCTOSPORID",
                resultClasses = {Producto.class},
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
                name = "contarProductos",
                procedureName = "CONTARPRODUCTOS",
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
                name = "contarProductosConFiltro",
                procedureName = "CONTARPRODUCTOSCONFILTRO",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "nombre",
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
                name = "actualizarProducto",
                procedureName = "ACTUALIZARPRODUCTO",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProdu",
                                type = Long.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "nombre",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "precio",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "stock",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "unidadMasa",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "imagen",
                                type = Blob.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "tipo",
                                type = Character.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "calidad",
                                type = Byte.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "eliminarProductoPorId",
                procedureName = "BORRARPRODUCTO",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Long.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "actualizarStockProducto",
                procedureName = "actualizarStockProducto",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idProdu",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "stockProdu",
                                type = Integer.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "buscarProductosPorNombre",
                procedureName = "BUSCARPRODUCTOSPORNOMBRE",
                resultClasses = {Producto.class},
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "nombre",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "id",
                                type = Long.class
                        ),
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
})
public class Producto {
    // Variable que almacena el id del producto, este dato es auto incrementable y se le asigna a cada producto para luego poder ser identificado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdu;
    // Variable que almacena el nombre del producto
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreProdu;
    // Variable que almacena la fecha de ingreso del producto, esto con el fin de tener un registro
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaIngresoProdu;
    // Variable que almacena la calidad del producto, esto consta de una valoracion del 1 al 5, donde 1 es una pesima calidad y 5 una excelente
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Byte calidadProdu;
    // Variable que almacena el precio total del producto
    @Min(1)
    @Column(nullable = false)
    private Integer precioProdu;
    // Variable que almacena la unidad de mediad del producto
    @NotNull
    @Column(length = 2, nullable = false)
    private String unidadMasaProdu;
    // Variable que almacena una imagen para el reconocimiento mas certero del producto
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] imagenProdu;
    // Variable que almacena el tipo de comercializacion del producto, en este caso se tiene registro de los tipos como, externo y interno
    @NotNull
    @Column(length = 1, nullable = false)
    private Character tipoComercializacionProdu;
    // Variable que almacena el stock o cantidad todal del producto seleccionado
    @Min(0)
    @Column(nullable = false)
    private Integer stockProdu;
    // Variable que hereda de Productor, esta sirve para dar conocimiento del productor que publica sus productos
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROD")
    private Productor productor;
    //inicio de los metodos accesadores
    public Producto() {
    }

    public Long getIdProdu() {
        return idProdu;
    }

    public void setIdProdu(Long idProdu) {
        this.idProdu = idProdu;
    }

    public String getNombreProdu() {
        return nombreProdu;
    }

    public void setNombreProdu(String nombreProdu) {
        this.nombreProdu = nombreProdu;
    }


    public String getUnidadMasaProdu() {
        return unidadMasaProdu;
    }

    public void setUnidadMasaProdu(String unidadMasaProdu) {
        this.unidadMasaProdu = unidadMasaProdu;
    }

    public LocalDateTime getFechaIngresoProdu() {
        return fechaIngresoProdu;
    }

    public byte[] getImagenProdu() {
        return imagenProdu;
    }

    public void setImagenProdu(byte[] imagenProdu) {
        this.imagenProdu = imagenProdu;
    }

    public void setFechaIngresoProdu(LocalDateTime fechaIngresoProdu) {
        this.fechaIngresoProdu = fechaIngresoProdu;
    }

    public Byte getCalidadProdu() {
        return calidadProdu;
    }

    public void setCalidadProdu(Byte calidadProdu) {
        this.calidadProdu = calidadProdu;
    }

    public Integer getPrecioProdu() {
        return precioProdu;
    }

    public void setPrecioProdu(Integer precioProdu) {
        this.precioProdu = precioProdu;
    }

    public Character getTipoComercializacionProdu() {
        return tipoComercializacionProdu;
    }

    public void setTipoComercializacionProdu(Character tipoComercializacionProdu) {
        this.tipoComercializacionProdu = tipoComercializacionProdu;
    }

    public Integer getStockProdu() {
        return stockProdu;
    }

    public void setStockProdu(Integer stockProdu) {
        this.stockProdu = stockProdu;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public static String convertirImagen(byte[] blob )
    {
        return  "data:image/png;base64," + Base64.encodeBase64String(blob);
    }
}
