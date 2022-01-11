<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 18-08-2019
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="layout/includes.jsp"></jsp:include>
    <title>Transportista</title>
    <script type="text/javascript">
        $(document).ready(function() {
            setInterval(function () {
                setTimeout(function () {
                    $("#alert").slideUp(1500);
                });
            },4000);
        });
    </script>
</head>
<body>
<jsp:include page="layout/cabecera.jsp" />
<div class="page-wrapper chiller-theme toggled">
    <jsp:include page="layout/sidebarTransportista.jsp" />
    <main class="page-content">
        <div class="container-fluid w-80 mt-2 pl-0 pr-0">
            <div class="row  ml-5 mr-5">
                <div class="col-lg pl-0 pr-0 mr-3 ml-3">
                    <!-- PUBLICACIONES -->
                    <div class="col-lg">
                        <c:if test="${alerta != null}">
                            <div
                                    class="alert alert-warning alert-dismissible fade show text-right mb-3 mt-2 alerta-naranja"
                                    id="alert" role="alert" data-autohide="true" data-delay="5000">
                                <strong>${alerta}</strong>
                                <button type="button" class="close py-2" data-dismiss="alert"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </c:if>
                        <section class="page-section-one pb-3" id="portfolio">
                            <div class="container">
                                <div class="row" style="margin-right: 0px !important;">
                                    <div class="col-lg-12 text-center">
                                        <h3 class="letras text-center jumbotron-heading font-weight-bold">Envios
                                            disponibles</h3>
                                        <h3 class="section-subheading text-muted">Ventas
                                            disponibles para ofrecer transporte</h3>
                                    </div>
                                </div>
                            </div>
                        </section>


            <c:choose>
                <c:when test="${ventas.size() < 1}">
                    no hay ventas para subasta disponible
                </c:when>
                <c:otherwise>
                    <c:forEach items="${ventas}" var="v">
                        <div class="card border-secondary mb-3">
                            <div class="card-header text-uppercase text-center">
                                <h4 class="letras mb-0">Venta N°${v.idVenta}</h4>
                            </div>
                            <div class="card-body py-3 px-3 text-center">
                                <p class="lead mb-0">
                                    Tipo de venta:
                                    <c:if test="${v.tipoVenta eq 'E'.charAt(0)}">
                                        Externa
                                    </c:if>
                                    <c:if test="${v.tipoVenta eq 'I'.charAt(0)}">
                                        Interna
                                    </c:if>
                                    <br> Fecha de inicio: ${v.solicitud.fechaEmisionSol}<br>
                                    Fecha de termino: ${v.solicitud.fechalimiteSol}
                                </p>
                            </div>
                            <div class="card-footer py-0 px-0">
                                <button type="button"
                                        onclick="window.location.href='subastaTransportista/${v.idVenta}'"
                                        class="btn btn-secondary btn-block letras rounded-0 ">Ver
                                    detalle
                                </button>
                            </div>
                        </div>

                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
