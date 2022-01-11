<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 18-08-2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="layout/includes.jsp"></jsp:include>
    <title>Cliente</title>
</head>
<body>
<jsp:include page="layout/cabecera.jsp" />
<div class="page-wrapper chiller-theme toggled">
    <jsp:include page="layout/sidebarCliente.jsp" />
    <main class="page-content">
        <div class="container" style="max-width: 45rem;">
            <h2 class="letras text-center jumbotron-heading font-weight-bold">Cliente Interno</h2>

            <button type="button"
                    onclick="window.location.href='/clienteInterno/crearSolicitud'"
                    class="btn btn-secondary btn-block letras">Agregar solicitud</button>
        </div>

    </main>
</div>
</body>
</html>
