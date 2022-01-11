package com.duoc.maipogrande.controladores;

import com.duoc.maipogrande.modelos.*;
import com.duoc.maipogrande.modelos.utilidades.Frutas;
import com.duoc.maipogrande.paginador.Pagina;
import com.duoc.maipogrande.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
public class ClientesControlador {

    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    ProductorServicio productorServicio;
    @Autowired
    TransportistaServicio transportistaServicio;
    @Autowired
    ProductoServicio productoServicio;
    @Autowired
    PdfServicio pdfServicio;

    String[] rutas = {"https://apipdf.s3.us-east-2.amazonaws.com/reportes/aceptado/","https://apipdf.s3.us-east-2.amazonaws.com/reportes/rechazado/"};


    /**
     * Metodo que permite el redireccionamiento de pagina y que ademas logra realizar el papel de control de inicio de sesiones
     *
     * @param model      interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @param principal  Control el ingreso de entidades a la pagina, segun sus roles
     * @param session    Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param logout     Anotacion que indica que parametro se solicito, en este caso, logout el cual permite cerrar sesion dentro del sitio
     * @param error      Anotacion que indica que parametro se solicito, en este caso, error que permite determinar que problemas se detectan
     * @param attributes Manda informacion a la vista, cuando esta es redirigida mediante una ruta que va hacia un metodo
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
    @RequestMapping(value = {"/","clienteInterno","clienteExterno","transportista","productor"}, method = RequestMethod.GET)
    public String index(Model model,
                        Principal principal,
                        HttpSession session,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(name = "pagina", required = false, defaultValue = "0") String p,
                        RedirectAttributes attributes) {
        String mensaje;
        if (principal != null) {
            String rol;
            List<?> ventasActivas;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            rol = authentication.getAuthorities().stream().map(o -> ((GrantedAuthority) o).getAuthority()).collect(Collectors.joining());
            switch (rol) {
                case "ROLE_CLIENTE_EXTERNO":
                    ventasActivas = clienteServicio.traerVentasActivasPorIdCli(Long.parseLong(principal.getName()));
                    attributes.addFlashAttribute("alerta",model.asMap().get("alerta"));
                    session.setAttribute("ventasActivas", ventasActivas);
                    return "clienteExterno";
                case "ROLE_CLIENTE_INTERNO":
                    ventasActivas = clienteServicio.traerVentasActivasPorIdCli(Long.parseLong(principal.getName()));
                    session.setAttribute("ventasActivas", ventasActivas);
                    return "clienteInterno";
                case "ROLE_PRODUCTOR":
                    Integer pagina = 0;
                    Integer paginaActual = 0;
                    if (p != null) {
                        try {
                            pagina = Integer.parseInt(p);
                            pagina--;
                            if(pagina < 0)
                            {
                                pagina= 0;
                            }
                            paginaActual = pagina;
                            pagina =  pagina * 4;
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    List<Venta> ventas = productorServicio.ventasParaSubasta(pagina);
                    ventasActivas = productorServicio.ventasActivasProductor(((Productor)session.getAttribute("productor")).getIdProd());
                    int totalPaginas = productorServicio.contarVentasSubasta();
                    Pagina paginador = new Pagina((short) totalPaginas,(short)(paginaActual+1));
                    model.addAttribute("ventas",ventas);
                    session.setAttribute("ventasActivas",ventasActivas);
                    model.addAttribute("paginador",paginador);
                    model.addAttribute("paginaActual",(paginaActual==0)?1: paginaActual+1);
                    Contrato contrato = ((Productor)session.getAttribute("productor")).getContrato();
                    long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(),contrato.getFechaTerminoContra());
                    String estado = "";
                    if (diasRestantes <= 31 && diasRestantes >=1) {
                        estado="Alerta";
                    }else if(diasRestantes<=0){
                        estado="Expirado";
                    }else {
                        estado ="Al día";
                    }
                    session.setAttribute("estadoContrato", estado);
                    ventasActivas = productorServicio.ventasActivasProductor(Long.parseLong(principal.getName()));
                    session.setAttribute("ventasActivas", ventasActivas);
                    return "productor";
                case "ROLE_TRANSPORTISTA":
                    pagina = 0;
                    paginaActual = 0;
                    if (p != null) {
                        try {
                            pagina = Integer.parseInt(p);
                            pagina--;
                            if (pagina < 0) {
                                pagina = 0;
                            }
                            paginaActual = pagina;
                            pagina = pagina * 4;
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    ventas = transportistaServicio.ventasParaSubastaTrans(pagina.shortValue());
                    totalPaginas = transportistaServicio.contarVentasSubasta();
                    paginador = new Pagina((short) totalPaginas, (short) (paginaActual + 1));
                    model.addAttribute("ventas", ventas);
                    model.addAttribute("paginador", paginador);
                    contrato = ((Transportista)session.getAttribute("transportista")).getContrato();
                     diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(),contrato.getFechaTerminoContra());
                    if (diasRestantes <= 31 && diasRestantes >=1) {
                        estado="Alerta";
                    }else if(diasRestantes<=0){
                        estado="Expirado";
                    }else {
                        estado ="Al día";
                    }
                    session.setAttribute("estadoContrato", estado);
                    ventasActivas = transportistaServicio.ventasActivasTran(Long.parseLong(principal.getName()));
                    session.setAttribute("ventasActivas", ventasActivas);
                    return "transportista";
            }
        }
        if (logout != null) {
            mensaje = "Sesion cerrada correctamente";
            model.addAttribute("logout", mensaje);
        }
        if (error != null) {
            mensaje = "Error en las credenciales";
            model.addAttribute("error", mensaje);
        }
        return "index";
    }



    /**
     * Metodo que permite el redireccionamiento segun el flujo del metodo, ademas logra cargar las unidades de medida para su respectivo uso
     *
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
    @Secured("ROLE_CLIENTE_INTERNO")
    @GetMapping(value = "/clienteInterno/crearSolicitud")
    public String paginaAñadirSolcitudInterno(Model model) {
        Solicitud solicitud = new Solicitud();
        Map<String, String> tipoUnidadMasa = new HashMap<String, String>() {{
            put("KG", "Kilogramos");
            put("T", "Toneladas");
        }};
        Frutas[] frutas = Stream.of(Frutas.values()).sorted(Comparator.comparing(Frutas::name)).toArray(Frutas[]::new);
        model.addAttribute("frutas", frutas);
        model.addAttribute("tipoUnidadMasa", tipoUnidadMasa);
        model.addAttribute("solicitud", solicitud);

        return "añadirSolicitudClienteInterno";
    }

    /**
     * Metodo que permite la creacion de solicitudes para el cliente interno
     *
     * @param solicitud               Clase {@code com.duoc.maipogrande.modelos.Solicitud} proveniente de la vista ClienteInterno
     * @param bindingResult           Valida si existen errores en la clase {@code com.duoc.maipogrande.modelos.Solicitud}
     * @param session                 Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param nombresProductos        Anotacion que indica que parametro se solicito, en este caso, nombreProductos el cual proporciona el nombre del producto
     * @param cantidadProductosString Anotacion que indica que parametro se solicito, en este caso, cantidadProductos el cual proporciona la cantidad de productos
     * @param unidadMasas             Anotacion que indica que parametro se solicito, en este caso, unidadMasas el cual proporciona el tipo de medidad
     * @return Permite la redirecion correspondiente a la vista, para luego ejecutar el metodo correspondiente
     */
    @Secured("ROLE_CLIENTE_INTERNO")
    @PostMapping(value = "/clienteInterno/crearSolicitud")
    public String peticionPostAñadirSolicitudInterno(@Valid @ModelAttribute("solicitud") Solicitud solicitud,
                                                     BindingResult bindingResult,
                                                     HttpSession session,
                                                     @RequestParam(name = "nombreproducto[]", required = false) String[] nombresProductos,
                                                     @RequestParam(name = "cantidadproducto[]", required = false) String[] cantidadProductosString,
                                                     @RequestParam(name = "unidadMasa[]", required = false) String[] unidadMasas) {
        if (bindingResult.hasErrors()) {
            return "redirect:/clienteInterno/crearSolicitud";
        }
        Integer[] cantidadProductos;
        try {
            cantidadProductos = Stream.of(cantidadProductosString)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            for (int i = 0; i < unidadMasas.length; i++) {
                if (unidadMasas[i].trim().isEmpty() || nombresProductos[i].trim().isEmpty()) {
                    return "redirect:/clienteInterno/crearSolicitud";
                }
            }
        } catch (NumberFormatException e) {
            return "redirect:/clienteInterno/crearSolicitud";
        }
        List<ProductoSolicitado> productoSolicitados = new ArrayList<>();
        IntStream.range(0, nombresProductos.length)
                .forEach(i -> {
                    productoSolicitados.add(new ProductoSolicitado(nombresProductos[i], unidadMasas[i], cantidadProductos[i]));
                });
        solicitud.setEstadoSol('E');
        solicitud.setPaisDestinoSol("CL");
        solicitud.setCliente(new Cliente());
        solicitud.getCliente().setIdCli(((Cliente) session.getAttribute("clienteInterno")).getIdCli());
        clienteServicio.crearSolicitud(solicitud);
        clienteServicio.crearProductosSolicitados(productoSolicitados);
        return "redirect:/clienteInterno";
    }

    @Secured("ROLE_CLIENTE_INTERNO")
    @GetMapping(value = "/clienteInterno/solicitudes")
    public String solicitudesClienteInterno(Model model, Principal principal) {
        List<Solicitud> solicitudes = clienteServicio.traerSolicitudesPorIdCli(Long.parseLong(principal.getName()));
        model.addAttribute("solicitudes", solicitudes);
        return "solicitudesClientes";
    }

    @Secured("ROLE_CLIENTE_INTERNO")
    @GetMapping(value = "/clienteInterno/detalleVenta/{id}")
    public String detalleVentaClienteInterno(Model model, Principal principal,HttpSession session, @PathVariable String id) {
        return detalleVentaClienteExterno(model,principal,id,session);
    }


    /**
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @return Permite la redirecion correspondiente a la vista, para luego ejecutar el metodo correspondiente
     */
    @Secured("ROLE_CLIENTE_EXTERNO")
    @GetMapping(value = "/clienteExterno/crearSolicitud")
    public String paginaAñadirSolcitud(Model model) {
        Solicitud solicitud = new Solicitud();
        Map<String, String> paises = solicitud.obtenerPaises();
        Map<String, String> tipoUnidadMasa = new HashMap<String, String>() {{
            put("KG", "Kilogramos");
            put("T", "Toneladas");
        }};
        Frutas[] frutas = Stream.of(Frutas.values()).sorted(Comparator.comparing(Frutas::name)).toArray(Frutas[]::new);
        model.addAttribute("frutas", frutas);
        model.addAttribute("paises", paises);
        model.addAttribute("tipoUnidadMasa", tipoUnidadMasa);
        model.addAttribute("solicitud", solicitud);
        return "añadirSolicitudClienteExterno";
    }

    /**
     * Metodo que permite la creacion de solicitudes para el cliente interno
     *
     * @param solicitud               Clase {@code com.duoc.maipogrande.modelos.Solicitud} proveniente de la vista ClienteInterno
     * @param bindingResult           Valida si existen errores en la clase {@code com.duoc.maipogrande.modelos.Solicitud}
     * @param session                 Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param nombresProductos        Anotacion que indica que parametro se solicito, en este caso, nombreProductos el cual proporciona el nombre del producto
     * @param cantidadProductosString Anotacion que indica que parametro se solicito, en este caso, cantidadProductos el cual proporciona la cantidad de productos
     * @param unidadMasas             Anotacion que indica que parametro se solicito, en este caso, unidadMasas el cual proporciona el tipo de medidad
     * @return Permite la redirecion correspondiente a la vista, para luego ejecutar el metodo correspondiente
     */
    @Secured("ROLE_CLIENTE_EXTERNO")
    @PostMapping(value = "/clienteExterno/crearSolicitud")
    public String peticionPostAñadirSolicitud(@Valid @ModelAttribute("solicitud") Solicitud solicitud,
                                              BindingResult bindingResult,
                                              HttpSession session,
                                              @RequestParam(name = "nombreproducto[]", required = false) String[] nombresProductos,
                                              @RequestParam(name = "cantidadproducto[]", required = false) String[] cantidadProductosString,
                                              @RequestParam(name = "unidadMasa[]", required = false) String[] unidadMasas) {
        if (bindingResult.hasErrors()) {
            return "redirect:/clienteExterno/crearSolicitud";
        }
        Integer[] cantidadProductos;
        try {
            cantidadProductos = Stream.of(cantidadProductosString)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            for (int i = 0; i < unidadMasas.length; i++) {
                if (unidadMasas[i].trim().isEmpty() || nombresProductos[i].trim().isEmpty()) {
                    return "redirect:/clienteExterno/crearSolicitud";
                }
            }
        } catch (NumberFormatException e) {
            return "redirect:/clienteExterno/crearSolicitud";
        }
        List<ProductoSolicitado> productoSolicitados = new ArrayList<>();
        IntStream.range(0, nombresProductos.length)
                .forEach(i -> {
                    productoSolicitados.add(new ProductoSolicitado(nombresProductos[i], unidadMasas[i], cantidadProductos[i]));
                });
        solicitud.setEstadoSol('E');
        solicitud.setCliente(new Cliente());
        solicitud.getCliente().setIdCli(((Cliente) session.getAttribute("clienteExterno")).getIdCli());
        clienteServicio.crearSolicitud(solicitud);
        clienteServicio.crearProductosSolicitados(productoSolicitados);
        return "redirect:/clienteExterno";
    }

    @Secured("ROLE_CLIENTE_EXTERNO")
    @GetMapping(value = "/clienteExterno/solicitudes")
    public String solicitudesClienteExterno(Model model, Principal principal) {
        List<Solicitud> solicitudes = clienteServicio.traerSolicitudesPorIdCli(Long.parseLong(principal.getName()));
        model.addAttribute("solicitudes", solicitudes);
        return "solicitudesClientes";
    }

    @Secured("ROLE_CLIENTE_EXTERNO")
    @GetMapping(value = "/clienteExterno/detalleVenta/{id}")
    public String detalleVentaClienteExterno(Model model, Principal principal, @PathVariable String id, HttpSession session) {
        Long idNumerico;
        try {
            idNumerico = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "redirect:/";
        }
        Venta venta = clienteServicio.traerVentaCliente(idNumerico, Long.parseLong(principal.getName()));
        if (venta == null) {
            return "redirect:/";
        }
        if (venta.getEstadoVenta().equals('P')) {
            venta.ordernarTop3SubastaProductos();
            Integer[] totales = venta.getOfertaProductos().
                    stream()
                    .map(ofertaProducto -> ofertaProducto.getPrecioOferta() * ((ofertaProducto.getProductoSolicitado().getUnidadProdS().equals("T"))? ofertaProducto.getProductoSolicitado().getCantidadProdS()*1000: ofertaProducto.getProductoSolicitado().getCantidadProdS()))
                    .toArray(Integer[]::new);
            model.addAttribute("totales", totales);
        } else if (venta.getEstadoVenta().equals('T')) {
            venta.ordernarTop3Transportistas();
            Integer pesoTotal = venta.getSolicitud().getProductoSolicitados()
                    .stream()
                    .map(productoSolicitado -> (productoSolicitado.getUnidadProdS().equals('T') ? productoSolicitado.getCantidadProdS() * 1000 : productoSolicitado.getCantidadProdS()))
                    .reduce(0, (subtotal, element) -> subtotal + element);
            model.addAttribute("pesoTotal", pesoTotal);
        }
        Long[] idsProductores = venta.getOfertaProductos().stream().map(ofertaProducto -> ofertaProducto.getProducto().getProductor().getIdProd())
                .distinct()
                .toArray(Long[]::new);
        Map<Long, Boolean> idRecorridas = new HashMap<Long, Boolean>() {{
            Arrays.stream(idsProductores).forEach(id -> put(id, false));
        }};
        Integer totalProductos = venta.getOfertaProductos()
                .stream()
                .map(ofertaProducto -> ofertaProducto.getPrecioOferta() * ((ofertaProducto.getProductoSolicitado().getUnidadProdS().equals("KG")) ? ofertaProducto.getProductoSolicitado().getCantidadProdS() : ofertaProducto.getProductoSolicitado().getCantidadProdS() * 1000))
                .reduce(0, (subtotal, element) -> subtotal + element);
        model.addAttribute("pais", venta.getSolicitud().obtenerPaisPorEstandarISO());
        model.addAttribute("idRecorridas", idRecorridas);
        model.addAttribute("totalProductos", totalProductos);
        session.setAttribute("venta", venta);

        return "detalleVentaCliente";
    }

    @Secured({"ROLE_CLIENTE_INTERNO", "ROLE_CLIENTE_EXTERNO"})
    @PostMapping(value = "cliente/rechazarVenta")
    public String rechazarVenta(HttpSession session,
                                @RequestParam("txtRechazo") String txtRechazo,
                                RedirectAttributes redirectAttributes) {
        Venta venta = (Venta) session.getAttribute("venta");
        String nombrePdf = pdfServicio.generarPdfRechazo(venta, txtRechazo);
        if (nombrePdf != null) {
            Reporte reporte = new Reporte(nombrePdf, "Rechazo de venta", 'R', venta.getIdVenta());
            clienteServicio.rechazarVentaPorid(reporte);
        }
        session.removeAttribute("venta");
        String mensaje = "Venta Rechazada";
        redirectAttributes.addFlashAttribute("alerta", mensaje);
        return "redirect:/";
    }

    @Secured({"ROLE_CLIENTE_INTERNO", "ROLE_CLIENTE_EXTERNO"})
    @PostMapping(value = "cliente/aceptarVenta")
    public String aceptarVenta(HttpSession session,
                               @RequestParam("txtAceptar") String txtAceptar,
                               RedirectAttributes redirectAttributes) {
        Venta venta = (Venta) session.getAttribute("venta");
        venta.getOfertaProductos().forEach(ofertaProducto -> {
            if (ofertaProducto.getProducto().getUnidadMasaProdu().equals(ofertaProducto.getProductoSolicitado().getUnidadProdS()))
            {
                Integer nuevoStockProdu = ofertaProducto.getProducto().getStockProdu() - ofertaProducto.getProductoSolicitado().getCantidadProdS();
                ofertaProducto.getProducto().setStockProdu(nuevoStockProdu);
                productoServicio.actualizarStockProducto(ofertaProducto.getProducto().getIdProdu().intValue(), nuevoStockProdu);
            }
            else
            {
                if(ofertaProducto.getProducto().getUnidadMasaProdu().equals("T") && ofertaProducto.getProductoSolicitado().getUnidadProdS().equals("KG"))
                {
                    Integer nuevoStockProdu = ofertaProducto.getProducto().getStockProdu() - (ofertaProducto.getProductoSolicitado().getCantidadProdS() / 1000);
                    ofertaProducto.getProducto().setStockProdu(nuevoStockProdu);
                    productoServicio.actualizarStockProducto(ofertaProducto.getProducto().getIdProdu().intValue(), nuevoStockProdu);
                }
                else
                {
                    Integer nuevoStockProdu = ofertaProducto.getProducto().getStockProdu() - (ofertaProducto.getProductoSolicitado().getCantidadProdS() * 1000);
                    ofertaProducto.getProducto().setStockProdu(nuevoStockProdu);
                    productoServicio.actualizarStockProducto(ofertaProducto.getProducto().getIdProdu().intValue(), nuevoStockProdu);
                }

            }
        });
        String nombrePdf = pdfServicio.generarPdfAceptar(venta);
        if (nombrePdf != null) {
            Reporte reporte = new Reporte(nombrePdf, txtAceptar, 'A', venta.getIdVenta());
            Integer sumaTotal = venta.getOfertaProductos().stream()
                    .map(ofertaProducto -> (ofertaProducto.getProductoSolicitado().getUnidadProdS().equals("T")) ? (ofertaProducto.getProductoSolicitado().getCantidadProdS() * 1000) * ofertaProducto.getPrecioOferta() : ofertaProducto.getProductoSolicitado().getCantidadProdS() * ofertaProducto.getPrecioOferta())
                    .reduce((integer, integer2) -> integer + integer2).orElse(null);
            sumaTotal += venta.getOfertaTransportistas().get(0).getPrecioOfertaOfert();
            Boleta boleta = new Boleta(LocalDateTime.now(), sumaTotal, venta.getIdVenta());
            clienteServicio.aceptarVentaPorId(reporte, boleta);

        }
        redirectAttributes.addAttribute("alerta", "Venta Aceptada");
        session.removeAttribute("venta");
        return"redirect:/";
    }

  
    @Secured("ROLE_CLIENTE_EXTERNO")
    @RequestMapping(value = "/clienteExterno/ventasHistoricas", method = RequestMethod.GET)
    public String ventasHistoricasClienteExterno(Principal principal,
                                                 Model model) {
        List<Venta> ventas = clienteServicio.traerVentasHistoricasPorId(Long.valueOf(principal.getName()));
        if(ventas.isEmpty())
        {
            return "redirect:/";
        }
        model.addAttribute("ventas", ventas);
        model.addAttribute("rutas", rutas);
        return "ventasHistoricas";
    }
    
    @Secured("ROLE_CLIENTE_INTERNO")
    @RequestMapping(value = "/clienteInterno/ventasHistoricas", method = RequestMethod.GET)
    public String ventasHistoricasClienteInterno(Model model, Principal principal) {
        List<Venta> ventas = clienteServicio.traerVentasHistoricasPorId(Long.valueOf(principal.getName()));
        if(ventas.isEmpty())
        {
            return "redirect:/";
        }
        model.addAttribute("ventas", ventas);
        model.addAttribute("rutas", rutas);
        return "ventasHistoricas";
    }

}
