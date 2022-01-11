<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 18-08-2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="layout/includes.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		setInterval(function() {
			setTimeout(function() {
				$("#alert").slideUp(1500);
			});
		}, 4000);
	});
</script>
<title>Cliente</title>
</head>
<body>
	<jsp:include page="layout/cabecera.jsp" />
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebarCliente.jsp" />
		<main class="page-content">
			<div class="container">
				<c:if test="${alerta != null}">
					<div
						class="alert alert-warning alert-dismissible fade show text-right mb-2 mt-2 alerta-naranja"
						id="alert" role="alert" data-autohide="true" data-delay="5000">
						<strong>${alerta}</strong>
						<button type="button" class="close py-2" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</c:if>
				<h2
					class="letras text-center jumbotron-heading font-weight-bold mb-4">Cliente
					Externo</h2>
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
							onclick="window.location.href='/clienteExterno/crearSolicitud'"
							class="btn btn-secondary btn-block letras btn-lg">Agregar
							solicitud</button>

					</div>
				</div>
			</div>

		</main>
	</div>




</body>
</html>
