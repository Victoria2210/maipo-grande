package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.OfertaTransportista;
import com.duoc.maipogrande.modelos.Transportista;
import com.duoc.maipogrande.modelos.Venta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransportistaServicio {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Transportista buscarTransportistaPorEmail(String email) {
        try {
        StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarTranPorEmail");
        query.setParameter("email", email);
        query.execute();
        return (Transportista) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true)
    public Transportista buscarTranPorId(Long id) {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("buscarTranPorId");
            query.setParameter("id", id);
            query.execute();
            return (Transportista) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true)
    public List<Venta> ventasParaSubastaTrans(Short i)
    {
        try{
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarVentasParaTransporte");
            query.setParameter("i",i);
            query.execute();
            return query.getResultList();
        }catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true)
    public List<Integer> ventasActivasTran(Long id)
    {
        try{
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarVentasActivasTran");
            query.setParameter("id",id);
            query.execute();
            return query.getResultList();
        }catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Integer contarVentasSubasta() {
        try {
            Integer filas  = ((BigDecimal)entityManager.createNativeQuery("select CONTARVENTASUBASTATRAN() from dual").
                    getSingleResult()).intValue();
            return filas;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Venta buscarVentaPorId(Long id)
    {
        try{
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarVentaPorIdParaTransporte");
            query.setParameter("id",id);
            query.execute();
            return (Venta) query.getSingleResult();
        }catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(noRollbackFor = RuntimeException.class)
    public boolean crearOfertaTransportista(OfertaTransportista ofertaTransportista)
    {
        try {
            StoredProcedureQuery query  = entityManager.createNamedStoredProcedureQuery("crearOfertaTransportista");
            query.setParameter("precioOferta",ofertaTransportista.getPrecioOfertaOfert());
            query.setParameter("idTran",ofertaTransportista.getTransportista().getIdTran());
            query.setParameter("idVenta",ofertaTransportista.getVenta().getIdVenta());
            query.execute();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public Venta buscarVentaDetalleTran(Long idVenta,Long idTran)
    {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("buscarVentaDetalleTran");
            query.setParameter("idVenta",idVenta);
            query.setParameter("idTran",idTran);
            query.execute();
            return (Venta) query.getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Transactional(readOnly = true)
    public List<Venta> traerVentasHistoricasPorId(Long idTran)
    {
        try {
            StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("traerVentasHistoricasTrans");
            query.setParameter("idTran", idTran);
            query.execute();
            return query.getResultList();
        }
        catch (Throwable e)
        {
            return null;
        }
    }

}
