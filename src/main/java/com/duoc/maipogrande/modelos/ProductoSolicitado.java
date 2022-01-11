package com.duoc.maipogrande.modelos;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que permite el almacenamiento de los datos de la solicitud creada
 */
@Entity
@Table(name = "Productos_Solicitados")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "crearProductosSolicitados",
                procedureName = "CREARPRODUCTOSOLICITADOS",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "nombreProds",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "cantidadProds",
                                type = Integer.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "unidadProds",
                                type = String.class
                        ),
                }
        )
})
public class ProductoSolicitado {
    //  Variable asignada a toda solicitud para su correcta identificacion
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProdS;
    // Varible que almacena el nombre de la solicitud
    @NotNull
    @Column(length = 50, nullable = false)
    private String nombreProdS;
    // Variable que almacena la unidad de medida del prodcuto
    @NotNull
    @Size(max = 2)
    @Column(length = 2, nullable = false)
    private String unidadProdS;
    // Variable que almacena la cantidad de productos para la solicitud
    @Min(1)
    @Column(nullable = false)
    private Integer cantidadProdS;
    // Variable heredada de la Solicitud
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SOL")
    private Solicitud solicitud;
    // Inicio de los metodos accesadores y mutadores
    public ProductoSolicitado() {
    }

    public ProductoSolicitado(@NotNull String nombreProdS, @NotNull @Size(max = 2) String unidadProdS, @Min(1) Integer cantidadProdS) {
        this.nombreProdS = nombreProdS;
        this.unidadProdS = unidadProdS;
        this.cantidadProdS = cantidadProdS;
    }

    public Long getIdProdS() {
        return idProdS;
    }

    public void setIdProdS(Long idProdS) {
        this.idProdS = idProdS;
    }

    public String getNombreProdS() {
        return nombreProdS;
    }

    public void setNombreProdS(String nombreProdS) {
        this.nombreProdS = nombreProdS;
    }

    public String getUnidadProdS() {
        return unidadProdS;
    }

    public void setUnidadProdS(String unidadProdS) {
        this.unidadProdS = unidadProdS;
    }

    public Integer getCantidadProdS() {
        return cantidadProdS;
    }

    public void setCantidadProdS(Integer cantidadProdS) {
        this.cantidadProdS = cantidadProdS;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
}
