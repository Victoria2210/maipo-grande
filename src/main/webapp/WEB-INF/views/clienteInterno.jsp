<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 18-08-2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
			
			<h2
				class="letras text-center jumbotron-heading font-weight-bold mb-4">Cliente
				Interno</h2>
			<div class="card">
				<div class="card-body">
					<h5 class="letras text-center jumbotron-heading mb-0">ELECCION
						DE PRODUCTOS</h5>
					<h6 class="section-subheading text-center mb-0">
						Aquí podrá llenar un formulario para comprar productos
						</h3>
				</div>
				<div class="card-footer p-0">
					<button type="button"
					onclick="window.location.href='/clienteInterno/crearSolicitud'"
					class="btn btn-secondary btn-block letras">Agregar
						solicitud</button>

				</div>
			</div>
		</main>
	</div>
</body>
</html>
