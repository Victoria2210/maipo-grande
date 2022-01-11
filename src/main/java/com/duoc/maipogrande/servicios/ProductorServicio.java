package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.OfertaProducto;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.modelos.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

@Service
public class ProductorServicio {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    RestTemplate restTemplate;

    @Transactional(readOnly = true)
    public Productor buscarProductorPorEmail(String email) {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarProdPorEmail");
            query.setParameter("email", email);
            query.execute();
            return (Productor) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Productor buscarProdPorId(Long id) {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarProdPorId");
            query.setParameter("id", id);
            query.execute();
            return (Productor) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean crearOfertaProducto(OfertaProducto ofertaProducto, Long idProds) {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("crearOfertaProducto");
            query.setParameter("paisOrigen", ofertaProducto.getPaisOrigen());
            query.setParameter("precioOferta", ofertaProducto.getPrecioOferta());
            query.setParameter("unidadMasaOferta", ofertaProducto.getUnidadMasaOferta());
            query.setParameter("idProdu", ofertaProducto.getProducto().getIdProdu());
            query.setParameter("idVenta", ofertaProducto.getVenta().getIdVenta());
            query.setParameter("idProds", idProds);
            query.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Venta> ventasParaSubasta(Integer pagina) {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarVentasParaSubasta");
            query.setParameter("i", pagina);
            query.execute();
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Venta> ventasActivasProductor(Long id) {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarVentasActivasProductor");
            query.setParameter("id", id);
            query.execute();
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<OfertaProducto> buscarOfertasPorIdVenta(Long id) {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarOfertasPorIdVenta");
            query.setParameter("idVenta", id);
            query.execute();
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Venta buscarVentaPorIdParaSubasta(Integer id) {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarVentaParaIdParaSubasta");
            query.setParameter("id", id);
            query.execute();
            Venta venta = (Venta) query.getSingleResult();
            List<OfertaProducto> ofertaProductos = venta.getOfertaProductos()
                    .stream()
                    .sorted(Comparator.comparing(OfertaProducto::getPrecioOferta)
                            .thenComparing(reverseOrder(Comparator.comparing
                                    (ofertaProducto -> ofertaProducto.getProducto().getCalidadProdu()))))
                    .collect(Collectors.toList());
            venta.setOfertaProductos(ofertaProductos);
            return venta;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Integer contarVentasSubasta() {
        try {
            Integer filas = ((BigDecimal) entityManager.createNativeQuery("select CONTARVENTASUBASTA() from dual").
                    getSingleResult()).intValue();
            return filas;
        } catch (Exception e) {
            return 0;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Venta buscarVentaDetalleProdu(Long idVenta, Long idProd) {
        Venta venta = null;
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarVentaDetalleProdu");
            query.setParameter("idVenta", idVenta);
            query.setParameter("idProd", idProd);
            query.execute();
            venta = (Venta) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        Long[] idProds = venta.getOfertaProductos()
                .stream()
                .map(ofertaProducto -> ofertaProducto.getProductoSolicitado().getIdProdS())
                .distinct()
                .sorted()
                .toArray(Long[]::new);
        List<OfertaProducto> ofertaProductos = venta.getOfertaProductos()
                .stream()
                .sorted(Comparator.comparing(OfertaProducto::getPrecioOferta)
                        .thenComparing(reverseOrder(Comparator.comparing
                                (ofertaProducto -> ofertaProducto.getProducto().getCalidadProdu()))))
                .filter(ofertaProducto -> ofertaProducto.getProducto().getProductor().getIdProd() == idProd)
                .collect(Collectors.toList());
        List<OfertaProducto> ofertaProductosFiltrados = new ArrayList<>();
        for (int i = 0; i < idProds.length; i++)
        {
            for (int j = 0; j < ofertaProductos.size() ; j++) {
                if (ofertaProductos.get(j).getProductoSolicitado().getIdProdS().equals(idProds[i])) {
                    ofertaProductosFiltrados.add(ofertaProductos.get(j));
                    break;
                }
            }

        }
      /*  int j = 0;
        for (int i = 0; i < ofertaProductos.size(); i++) {
            if (ofertaProductos.get(i).getProductoSolicitado().getIdProdS() == idProds[j]) {
                ofertaProductosFiltrados.add(ofertaProductos.get(i));
                j++;
                if (j == idProds[idProds.length - 1] || idProds.length == 1) {
                    break;
                }
                continue;
            }
        }*/
        venta.setOfertaProductos(ofertaProductosFiltrados);
        return venta;
    }
    @Transactional(readOnly = true)
    public List<Venta> traerVentasHistoricasPorId(Long idProd)
    {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("traerVentasHistoricasProductor");
            query.setParameter("idProd", idProd);
            query.execute();
            return query.getResultList();
        }
        catch (Throwable e)
        {
            return null;
        }
    }

}
