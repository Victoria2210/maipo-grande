package com.duoc.maipogrande.controladores;

import com.duoc.maipogrande.modelos.Contrato;
import com.duoc.maipogrande.modelos.OfertaTransportista;
import com.duoc.maipogrande.modelos.Transportista;
import com.duoc.maipogrande.modelos.Venta;
import com.duoc.maipogrande.servicios.TransportistaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TransportistaControlador {
    @Autowired
    private TransportistaServicio transportistaServicio;

    private Logger logger = LoggerFactory.getLogger(TransportistaControlador.class);
    String[] rutas = {"https://apipdf.s3.us-east-2.amazonaws.com/reportes/aceptado/","https://apipdf.s3.us-east-2.amazonaws.com/reportes/rechazado/"};



    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/subastaTransportista/{id}", method = RequestMethod.GET)
    public String paginaSubastaTransporista(@PathVariable(name = "id") String idString,
                                            Model model,
                                            HttpSession session) {
        Long id = null;
        boolean esAptoParaOfertar = false;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            return "redirect:/transportista";
        }
        Venta venta = transportistaServicio.buscarVentaPorId(id);
        if (!venta.getOfertaProductos().isEmpty()) {
            venta.ordernarTop3Transportistas();
        }
        Transportista transportista = (Transportista) session.getAttribute("transportista");
        esAptoParaOfertar = venta.transportistaAptoParaOfertar(venta, transportista);
        String pais = venta
                .getSolicitud()
                .obtenerPaises()
                .entrySet().stream()
                .filter(x -> venta.getSolicitud().getPaisDestinoSol().equals(x.getKey()))
                .map(x -> x.getValue())
                .collect(Collectors.joining());
        session.setAttribute("venta", venta);
        model.addAttribute("esAptoParaOfertar", esAptoParaOfertar);
        model.addAttribute("pais", pais);
        return "subastaTransportista";
    }

    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/subastaTransportista", method = RequestMethod.POST)
    public String crearOfertaTransportista(@RequestParam(name = "txtPrecio", required = false) String precioString,
                                           HttpSession session,
                                           @RequestHeader(value = "referer", required = false) String referrer,
                                           RedirectAttributes redirectAttributes) {
        Integer precio = null;
        try {
            precio = Integer.parseInt(precioString);
        } catch (NumberFormatException e) {
            return String.format("redirect:%s", referrer);
        }
        OfertaTransportista ofertaTransportista = new OfertaTransportista();
        ofertaTransportista.setPrecioOfertaOfert(precio);
        ofertaTransportista.getVenta().setIdVenta(((Venta) session.getAttribute("venta")).getIdVenta());
        ofertaTransportista.getTransportista().setIdTran(((Transportista) session.getAttribute("transportista")).getIdTran());
        transportistaServicio.crearOfertaTransportista(ofertaTransportista);
        redirectAttributes.addFlashAttribute("alerta", "Oferta ingresada con exito");
        return "redirect:/transportista";
    }
    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/detalleVentaTransportista/{id}", method = RequestMethod.GET)
    public String paginaVentaProductor(Model model,
                                       @PathVariable(value = "id",required = false) String idString,
                                       HttpSession session)
    {
        Long idVenta;
        Long idTran;
        try
        {
            idVenta = Long.parseLong(idString);
        }
        catch (NumberFormatException e)
        {
            return "redirect:/transportista";
        }
        idTran = ((Transportista)session.getAttribute("transportista")).getIdTran();
        Venta venta = transportistaServicio.buscarVentaDetalleTran(idVenta,idTran);
        if (venta == null) {
            return "redirect:/";
        }
            List<OfertaTransportista> ofertaTransportistas = venta.getOfertaTransportistas()
                    .stream()
                    .filter(ofertaTransportista -> ofertaTransportista.getTransportista().getIdTran().equals(((Transportista) session.getAttribute("transportista")).getIdTran()))
                    .collect(Collectors.toList());
            Venta ventaFiltrada = ofertaTransportistas.get(0).getVenta();
            ventaFiltrada.ordernarTop3Transportistas();
            Integer pesoTotal = venta.getSolicitud().getProductoSolicitados()
                    .stream()
                    .map(productoSolicitado -> (productoSolicitado.getUnidadProdS().equals('T') ? productoSolicitado.getCantidadProdS() * 1000 : productoSolicitado.getCantidadProdS()))
                    .reduce(0, (subtotal, element) -> subtotal + element);
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
        model.addAttribute("ofertaTran",ventaFiltrada.getOfertaTransportistas().get(0));
        model.addAttribute("pesoTotal", pesoTotal);
        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("idRecorridas", idRecorridas);
        model.addAttribute("pais", venta.getSolicitud().obtenerPaisPorEstandarISO());
        model.addAttribute(venta);
        return "detalleVentaTransportista";
    }
    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "transportista/contrato", method = RequestMethod.GET)
    public String mostrarContrato(Model model,
                                  HttpSession httpSession)
    {
        Contrato contrato = ((Transportista)httpSession.getAttribute("transportista")).getContrato();
        long restoDias = ChronoUnit.DAYS.between(LocalDate.now(),contrato.getFechaTerminoContra());
        long diasRestantes= 0;
        if(restoDias < 0) {
        	diasRestantes = 0;
        } else {
        	diasRestantes = restoDias;
        }
        model.addAttribute("contrato",contrato);
        model.addAttribute("diasRestantes",diasRestantes);
        return "transportistaContrato";
    }

    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/transportista/ventasHistoricas", method = RequestMethod.GET)
    public String ventasHistoricasClienteExterno(Principal principal,
                                                 Model model) {
        List<Venta> ventas = transportistaServicio.traerVentasHistoricasPorId(Long.valueOf(principal.getName()));
        if(ventas.isEmpty())
        {
            return "redirect:/";
        }
        model.addAttribute("ventas", ventas);
        model.addAttribute("rutas", rutas);
        return "ventasHistoricas";
    }
}
