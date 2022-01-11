package com.duoc.maipogrande.servicios;
import com.duoc.maipogrande.modelos.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoServicio {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Producto> buscarProductosPorId(Long id, short i) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProductosPorIdProd");
            query.setParameter("id",id);
            query.setParameter("i",i);
            query.execute();
            return query.getResultList();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Integer contarProductos(long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("contarProductos");
            query.setParameter("id",id);
            query.execute();
            Integer filas = ((BigDecimal) query.getSingleResult()).intValue();
            return filas;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Integer contarProductosConFiltro(long id,String nombre,short i) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("contarProductosConFiltro");
            query.setParameter("id",id);
            query.setParameter("nombre",nombre);
            query.execute();
            Integer filas = ((BigDecimal) query.getSingleResult()).intValue();
            return filas;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Producto buscarProductosPorIdProducto(Long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProductosPorId");
            query.setParameter("id",id);
            query.execute();
            return (Producto) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Producto> buscarProductosPorNombre(String nombre,Long id, short i) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarProductosPorNombre");
            query.setParameter("nombre",nombre);
            query.setParameter("id",id);
            query.setParameter("i",i);
            query.execute();
            return query.getResultList();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean eliminarProducto(Long id){
        try{
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("eliminarProductoPorId");
            query.setParameter("id", id);
            query.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean crearProducto(Producto producto, Blob imagen, Long idProd)
    {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("crearProducto");
            query.setParameter("nombre",producto.getNombreProdu());
            query.setParameter("precio",producto.getPrecioProdu());
            query.setParameter("imagen",imagen);
            query.setParameter("unidadMasa",producto.getUnidadMasaProdu());
            query.setParameter("stock",producto.getStockProdu());
            query.setParameter("tipo",producto.getTipoComercializacionProdu());
            query.setParameter("calidad",producto.getCalidadProdu());
            query.setParameter("fechaIngreso",LocalDateTime.now());
            query.setParameter("idProd",idProd);
            query.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean actualizarProducto(Producto productoActualizado,Blob imagen)
    {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("actualizarProducto");
            query.setParameter("nombre",productoActualizado.getNombreProdu());
            query.setParameter("precio",productoActualizado.getPrecioProdu());
            query.setParameter("imagen",imagen);
            query.setParameter("unidadMasa",productoActualizado.getUnidadMasaProdu());
            query.setParameter("stock",productoActualizado.getStockProdu());
            query.setParameter("tipo",productoActualizado.getTipoComercializacionProdu());
            query.setParameter("calidad",productoActualizado.getCalidadProdu());
            query.setParameter("idProdu",productoActualizado.getIdProdu());
            query.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean actualizarStockProducto(Integer idProdu, Integer stockProdu)
    {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("actualizarStockProducto");
            query.setParameter("idProdu", idProdu);
            query.setParameter("stockProdu", stockProdu);
            query.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public List<Producto> productosDisponibles(Long idProductoSolicitado,Long idProd,Character tipoVenta) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("productosDisponibles");
            query.setParameter("idProductoSolicitado",idProductoSolicitado);
            query.setParameter("idProd",idProd);
            query.setParameter("tipoVenta",tipoVenta);
            query.execute();
            return query.getResultList();
        }
        catch (Exception e)
        {
            return null;
        }
    }

}
