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
<title>Solicitudes</title>
</head>
<body>
<jsp:include page="layout/cabecera.jsp" />
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebarCliente.jsp" />
			<main class="page-content">
				<div class="container-fluid w-80 mt-2 pl-0 pr-0">
					<div class="row  ml-5 mr-5">
						<div class="col-lg pl-0 pr-0 mr-3 ml-3">
							<!-- PUBLICACIONES -->
							<div class="col-lg">
								<section class="page-section-one pb-3" id="portfolio">
									<div class="container">
										<div class="row">
											<div class="col-lg-12 text-center">
												<h3 class="letras text-center">Solicitudes en espera</h3>
											</div>
										</div>
									</div>
								</section>
								<c:choose>
									<c:when test="${solicitudes.size() < 1}">
										<div class="alert alert-warning">No se encuentran Solicitudes</div>
									</c:when>
									<c:otherwise>
										<c:forEach items="${solicitudes}" var="s">
											<div class="card border-secondary mb-3">
												<div class="card-header text-uppercase text-center">
													<h4 class="letras mb-0">Solicitud N°${s.idSol}</h4>
												</div>
												<div class="card-body py-3 px-3 text-center">
													<div class="container">
														<div class="row justify-content-between">
															<div class="col-4">
																<p class="lead mb-0">
																	Fecha de inicio: ${s.fechaEmisionSol}
																</p>
															</div>
															<div class="col-4">
																<p class="lead mb-0">
																	Fecha de limite: ${s.fechalimiteSol}
																</p>
															</div>
														</div>
														<div class="row justify-content-md-center">
															<div class="col-8">
																<table class="table table-sm table-borderless border border-secondary mt-3">
																	<thead class="border-bottom">
																	<tr>
																		<th scope="col" class="lead"><strong>Producto</strong></th>
																		<th scope="col" class="text-right lead"><strong>Cantidad</strong></th>
																	</tr>
																	</thead>
																	<tbody>
																	<c:forEach items="${s.productoSolicitados}" var="prodS">
																		<tr>
																			<td class="lead">${prodS.nombreProdS}</td>
																			<td class="text-right lead">${prodS.cantidadProdS} ${prodS.unidadProdS}</td>
																		</tr>
																	</c:forEach>

																	</tbody>
																</table>
															</div>
														</div>
														<div class="">

														</div>
													</div>

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
