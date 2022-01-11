<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <jsp:include page="layout/includes.jsp"></jsp:include>
    <title>Ventas Historicas</title>
</head>
<body>
<jsp:include page="layout/cabecera.jsp"/>
<div class="page-wrapper chiller-theme toggled">
    <c:choose>
        <c:when test="${productor != null}">
            <jsp:include page="layout/sidebar.jsp"/>
        </c:when>
        <c:when test="${clienteExterno != null || clienteInterno != null}">
            <jsp:include page="layout/sidebarCliente.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="layout/sidebarTransportista.jsp"/>
        </c:otherwise>
    </c:choose>

    <main class="page-content">
        <div class="container-fluid w-80 mt-2 pl-0 pr-0">
            <div class="row  ml-5 mr-5">
                <div class="col-lg pl-0 pr-0 mr-3 ml-3">
                    <h3 class="letras text-center mb-4">Ventas Historicas</h3>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover mb-0">
                            <thead>
                            <tr>
                                <th scope="col">Venta</th>
                                <th scope="col">Tipo de Venta</th>
                                <th scope="col">Resolución</th>
                                <th scope="col" class="text-right">Reporte</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ventas}" var="v">
                                <tr>
                                    <td>Venta ${v.idVenta}</td>
                                    <td>Venta
                                        <c:if test="${v.tipoVenta eq 'E'.charAt(0)}">
                                            Externa
                                        </c:if>
                                        <c:if test="${v.tipoVenta eq 'I'.charAt(0)}">
                                            Interna
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${v.estadoVenta eq 'R'.charAt(0)}">
                                            Rechazada
                                        </c:if>
                                        <c:if test="${v.estadoVenta eq 'A'.charAt(0)}">
                                            Aceptada
                                        </c:if>
                                    </td>
                                    <td class="text-right"><a target="_blank" class="text-success h5"
                                                              href="<c:if test="${v.reportes.get(0).pdfRuta.contains('pdfAceptado')}">
                                       ${rutas[0].concat(v.reportes.get(0).pdfRuta)}
									</c:if><c:if test="${v.reportes.get(0).pdfRuta.contains('pdfRechazo')}">
                                       ${rutas[1].concat(v.reportes.get(0).pdfRuta)}
									</c:if>"
                                    ><i
                                            class="far fa-file-pdf"></i></a></td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <p class="text-right text-muted"><i class="far fa-file-pdf"></i>: Visualizar PDF</p>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>