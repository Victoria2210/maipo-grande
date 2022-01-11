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
<title>Subasta</title>
	<jsp:include page="layout/includes.jsp"></jsp:include>
	<script src="/js/utilidades/subastaProductor.js">
	</script>
</head>
<body>
<jsp:include page="layout/cabecera.jsp" />
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebar.jsp" />
		<form method="post" action="/subasta" novalidate class="needs-validation">
		<main class="page-content">
			<div class="container-fluid w-80 mt-2 pl-0 pr-0">
				<div class="row  ml-5 mr-5">
					<div class="col-lg pl-0 pr-0 mr-3 ml-3">
						<div class="container" style="max-width: 45rem;">
							<h3 class="letras text-center jumbotron-heading font-weight-bold">Subasta N°
								${venta.solicitud.idSol}</h3>

							<table class="table table-sm table-borderless">
								<tbody>
								<tr>
									<th colspan="2" class="lead text-uppercase texto-verde">Datos del cliente</th>
								</tr>
								<tr>
									<td class="lead"><strong>Nombre</strong></td>
									<td class="lead">${venta.solicitud.cliente.nombreCli} ${venta.solicitud.cliente.apellidosCli}</td>
									<td class="lead"><strong>Correo</strong></td>
									<td class="lead">${venta.solicitud.cliente.emailCli}</td>
								</tr>
								<tr>
									<th colspan="2" class="lead text-uppercase texto-verde">Datos de la venta</th>
								</tr>
								<tr>
									<td class="lead"><strong>Tipo de venta</strong></td>
									<td class="lead">
										<c:if test="${venta.tipoVenta eq 'E'.charAt(0)}">
											Venta Externa
										</c:if>
										<c:if test="${venta.tipoVenta eq 'I'.charAt(0)}">
											Venta Interna
										</c:if>

									</td>
									<td class="lead"><strong>Fecha limite</strong></td>
									<td class="lead">${venta.solicitud.fechalimiteSol}</td>
								</tr>
								<tr>
									<td class="lead"><strong>Dirección</strong></td>
									<td colspan="3" class="lead">${venta.solicitud.direccionDestinoSol}, ${pais}</td>
								</tr>
								<tr>
									<td class="lead"><strong>Descripción</strong></td>
									<td colspan="3" class="lead">${venta.solicitud.descripcionSol}</td>
								</tr>
								</tbody>
							</table>


						</div>
						<c:forEach items="${venta.solicitud.productoSolicitados}" var="p">
						<div class="card border-success mb-3">
							<div class="card-header text-uppercase">
								<table class="table table-sm table-borderless mb-0">
									<tr>
										<td class="text-left font-weight-bold">${p.nombreProdS}</td>
										<td class="text-right font-weight-bold">${p.cantidadProdS} ${p.unidadProdS}</td>
									</tr>
								</table>
							</div>
							<div class="card-body py-0 px-0">
								<button type="button"  id="btnOfertar${p.idProdS}" name="btnOfertar"  class="btn btn-success btn-block rounded-0">O F E R T A R</button>
							</div>
						</div>
						</c:forEach>
						<input type="submit"  value="Agregar oferta a subasta" id="btnAgregar" class="btn btn-block btn-lg mb-2" style="background-color: #FF5400; color: white;"/>

						<hr class="border border-secondary my-5">
						   <h5 class="letras jumbotron-heading">Mejores subastas</h5>
						   <c:forEach items="${venta.solicitud.productoSolicitados}" var="j" varStatus="index">
							   <div class="card border-success mb-4">
								   <div class="card-header text-uppercase">
									   <table class="table table-sm table-borderless mb-0">
										   <tr>
											   <td class="text-left font-weight-bold">${j.nombreProdS}</td>
											   <td class="text-right text-warning py-0"><h3 class="mb-0">
											   <i class="fas fa-trophy"></i>
											</h3></td>
									</tr>
								</table>
							</div>

							<div class="card-body text-cennter" >
								<table class="table table-sm table-borderless" style="max-width: 550px; margin: auto;">
									<thead>
										<tr>
											<th scope="col"></th>
											<th scope="col">Proveedor</th>
											<th scope="col" class="text-right">Precio X Kg</th>
										</tr>
									</thead>
									<c:choose>
										<c:when test="${venta.ofertaProductos.size()>0}">
											<c:set var="top3" value="1" scope="page"></c:set>
											<c:set var="done" value="false" />
											<c:forEach items="${venta.ofertaProductos}" var="op">
												<c:forEach var="map" items="${idProdsSolicitados}">
												<c:if test="${j.idProdS == map.value && op.idOferp == map.key && !done}">
													<tbody>
													<tr>
														<th scope="row" class="text-center">${top3}</th>
														<td>${op.producto.productor.nombreProd} ${op.producto.productor.apellidoProd}</td>
														<td class="text-right">${op.precioOferta}</td>
														<c:set var="top3" value="${top3 + 1}" scope="page" ></c:set>
														<c:if test="${top3 > 3}">
															<c:set var="done" value="true" />
														</c:if>
													</tr>
													</tbody>
												</c:if>
												</c:forEach>
											</c:forEach>
                                            <c:if test="${top3 == 1}">
                                                <tr>
                                                    <th scope="row">No hay ofertas para esta subasta</th>
                                                </tr>
                                            </c:if>
										</c:when>
										<c:otherwise>
                                            <tr>
                                                <th scope="row">No hay ofertas para esta subasta</th>
                                            </tr>
										</c:otherwise>
									</c:choose>
								</table>
							</div>
						</div>
                      </c:forEach>
					</div>
				</div>
			</div>
		</main>
		</form>
	</div>
<jsp:include page="layout/modalCargando.jsp"></jsp:include>
</body>
</html>
