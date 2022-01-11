<%--
  Created by IntelliJ IDEA.
  User: Matias
  Date: 24-11-2019
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<jsp:include page="layout/includes.jsp"></jsp:include>
<title>Transportista Contrato</title>
</head>
<body>
	<jsp:include page="layout/cabecera.jsp" />
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebarTransportista.jsp"></jsp:include>
		<main class="page-content">
			<div class="container-fluid w-80 mt-2 pl-0 pr-0">
				<div class="row  ml-5 mr-5">
					<div class="col-lg pl-0 pr-0 mr-3 ml-3 text-center">
						<h3 class="letras text-center mb-4">Contrato</h3>

						<c:if test="${diasRestantes eq 0}">
							<div class="alert alert-danger border border-danger" role="alert">
								Su contrato se encuentra <strong>caducado</strong>
							</div>
						</c:if>
						<c:if test="${diasRestantes ge 1}">
							<div class="alert alert-warning border border-warning" role="alert">
								Su contrato se encuentra a <strong>${diasRestantes}
									días</strong> de caducar
							</div>
						</c:if>

						<table class="table table-sm table-borderless mb-4">
							<tr>
								<td class="text-left">Fecha de inicio del contrato: <strong>${contrato.fechaInicioContra}</strong></td>
								<td class="text-right">Fecha de caducación: <strong>${contrato.fechaTerminoContra}</strong></td>
							</tr>
						</table>
						<object
							data="https://apipdf.s3.us-east-2.amazonaws.com/${contrato.pdfContra}"
							type="application/pdf" class="w-100" height="500px">
							<embed
								src="https://apipdf.s3.us-east-2.amazonaws.com/${contrato.pdfContra}"
								type="application/pdf">
							<p>
								Este navegador no soporta vista de pdfs. Porfavor descargar el
								pdf para verlo: <a
									href="https://apipdf.s3.us-east-2.amazonaws.com/${contrato.pdfContra}">Download
									PDF</a>.
							</p>
							</embed>
						</object>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>
