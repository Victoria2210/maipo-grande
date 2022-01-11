package com.duoc.maipogrande.modelos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase que se utiliza para el almacenamiento de los valores que se asignaran a la solicitud
 */
@Entity
@Table(name = "Solicitudes")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "crearSolicitud",
                procedureName = "CREARSOLICITUD",
                parameters = {
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "descripcion",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "direccion",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "estado",
                                type = Character.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "fechaEmision",
                                type = LocalDate.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "fechaLimite",
                                type = LocalDate.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "paisDestino",
                                type = String.class
                        ),
                        @StoredProcedureParameter(
                                mode = ParameterMode.IN,
                                name = "idCli",
                                type = Long.class
                        ),
                }
        ),
        @NamedStoredProcedureQuery(
                name = "traerSolicitudesPorIdCli",
                procedureName = "traerSolicitudesPorIdCli",
                resultClasses = {Solicitud.class},
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
        )
})
public class Solicitud {
    // Variable que se utiliza para el almacenamiento de la id de la solicitud, dato utilizado para facilitar la identificacion de la solicitud
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSol;
    // Variable que se utiliza para almacenar la descipcion de la solicitud
    @NotNull
    @Column(length = 200, nullable = false)
    @Size(max = 200)
    private String descripcionSol;
    // Variable que almacena la fecha de emision de la solicitud
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEmisionSol;
    // Varible que almacena la fecha limite de la solicitud
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechalimiteSol;
    // Variable que almacena el estado de la solicitud, entre estos se encuentra el estado de aceptada y rechazada
    @Column(length = 1, nullable = false)
    private Character estadoSol;
    // Variable que alamacena el pasi de destino del producto solicitado
    @NotNull
    @Column(length = 60, nullable = false)
    private String paisDestinoSol;
    // Variable que almacena la direccion para la entrega del producto solicitado
    @NotNull
    @Column(length = 100, nullable = false)
    @Size(max = 100)
    private String direccionDestinoSol;
    // variable heredada de la clase cliente, la cual sirve para determinar el tipo, en este caso se tiene registro de internos o externos
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLI")
    private Cliente cliente;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "solicitud")
    private List<ProductoSolicitado> productoSolicitados;

    // Inicio de los metodos accesadores y mutadores
    public Solicitud() {
    }

    public String getDescripcionSol() {
        return descripcionSol;
    }

    public void setDescripcionSol(String descripcionSol) {
        this.descripcionSol = descripcionSol;
    }

    public String getDireccionDestinoSol() {
        return direccionDestinoSol;
    }

    public void setDireccionDestinoSol(String direccionDestinoSol) {
        this.direccionDestinoSol = direccionDestinoSol;
    }

    public Long getIdSol() {
        return idSol;
    }

    public void setIdSol(Long idSol) {
        this.idSol = idSol;
    }

    public LocalDate getFechaEmisionSol() {
        return fechaEmisionSol;
    }

    public void setFechaEmisionSol(LocalDate fechaEmisionSol) {
        this.fechaEmisionSol = fechaEmisionSol;
    }

    public LocalDate getFechalimiteSol() {
        return fechalimiteSol;
    }

    public void setFechalimiteSol(LocalDate fechalimiteSol) {
        this.fechalimiteSol = fechalimiteSol;
    }

    public Character getEstadoSol() {
        return estadoSol;
    }

    public void setEstadoSol(Character estadoSol) {
        this.estadoSol = estadoSol;
    }

    public String getPaisDestinoSol() {
        return paisDestinoSol;
    }

    public void setPaisDestinoSol(String paisDestinoSol) {
        this.paisDestinoSol = paisDestinoSol;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProductoSolicitado> getProductoSolicitados() {
        return productoSolicitados;
    }

    public void setProductoSolicitados(List<ProductoSolicitado> productoSolicitados) {
        this.productoSolicitados = productoSolicitados;
    }

    public Map<String, String> obtenerPaises() {
        Map<String, String> paises = new TreeMap<>();
        Map<String, String> paisesOrdenados;
        Locale locale = new Locale("es", "ES");
        String[] locales = Locale.getISOCountries();
        for (String codigoPais : locales) {
            Locale objeto = new Locale("", codigoPais);
            paises.put(objeto.getCountry(), objeto.getDisplayCountry(locale));
        }
        paisesOrdenados = paises.entrySet().stream().sorted((Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (s, s2) -> s, LinkedHashMap::new));
        return paisesOrdenados;
    }

    public String obtenerPaisPorEstandarISO() {
        return obtenerPaises()
                .entrySet()
                .stream()
                .filter(x -> x.getKey().equals(this.paisDestinoSol))
                .map(x -> x.getValue())
                .collect(Collectors.joining());
    }
}
