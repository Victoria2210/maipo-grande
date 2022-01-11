package com.duoc.maipogrande.modelos.auditoria;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOG_VENTAS")
public class VentasLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVentasLog;
    @Column(nullable = false,columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss tt")
    private LocalDateTime fechaVentasLog;
    @Column(nullable = false)
    private Long idAdminVentasLog;
    @Column(nullable = false)
    private Long idProcesoVenta;
    @Column(nullable = false, length = 1)
    private Character estadoVentaLog;
    @Column(nullable = false, length = 15)
    private String accionVentasLog;
}
