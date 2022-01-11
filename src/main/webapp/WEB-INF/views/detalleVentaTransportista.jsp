<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Detalle venta</title>
<jsp:include page="layout/includes.jsp"></jsp:include>
<script src="/js/utilidades/detalleVentas.js"></script>
<script type="text/javascript">
		$(document).ready(() => {
			checkearEstado("${venta.estadoVenta}");
		});
	</script>
</head>
<body>
	<jsp:include page="layout/cabecera.jsp" />
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebarTransportista.jsp" />
		<
		<main class="page-content">
			<div class="container-fluid w-80 mt-2 pl-0 pr-0">
				<div class="row  ml-5 mr-5">
					<div class="col-lg pl-0 pr-0 mr-3 ml-3">
						<section class="page-section-one pb-3">
							<div class="container">
								<div class="row" style="margin-right: 0px !important;">
									<div class="col-lg-12 text-center">
										<h3
											class="letras text-center jumbotron-heading font-weight-bold">Venta
											N°${venta.idVenta}</h3>
									</div>
								</div>
							</div>
						</section>

						<div class="list-group list-group-horizontal" id="list-tab"
							role="tablist">
							<a class="list-group-item list-group-item-action"
								id="list-home-list" data-toggle="list" href="#list-home"
								role="tab" aria-controls="home">
								<div class="media">
									<div class="media-body">
										<strong class="titulo-media mb-0">SUBASTA I</strong>
										<p class="text-muted mb-0">Elección de productos</p>
									</div>
									<i class="fa fa-chevron-right align-self-center"></i>
								</div>
							</a>
							<c:if test="${venta.estadoVenta != 'P'.charAt(0)}">
								<a class="list-group-item list-group-item-action"
									id="list-profile-list" data-toggle="list" href="#list-profile"
									role="tab" aria-controls="profile">
									<div class="media">
										<div class="media-body">
											<strong class="titulo-media mb-0">SUBASTA II</strong>
											<p class="text-muted mb-0">Elección de transporte</p>
										</div>
										<i class="fa fa-chevron-right align-self-center"></i>
									</div>
								</a>
							</c:if>
							<c:if
								test="${venta.estadoVenta != 'P'.charAt(0) and venta.estadoVenta != 'T'.charAt(0)}">
								<a class="list-group-item list-group-item-action"
									id="list-messages-list" data-toggle="list"
									href="#list-messages" role="tab" aria-controls="messages">
									<div class="media">
										<div class="media-body">
											<strong class="titulo-media mb-0">ENVÍO</strong>
											<p class="text-muted mb-0">Proceso de envio</p>
										</div>
										<i class="fa fa-chevron-right align-self-center"></i>
									</div>
								</a>
							</c:if>
							<c:if test="${venta.estadoVenta == 'A'.charAt(0)}">
								<a class="list-group-item list-group-item-action"
									id="list-settings-list" data-toggle="list"
									href="#list-settings" role="tab" aria-controls="settings">
									<div class="media">
										<div class="media-body">
											<strong class="titulo-media mb-0">ORDEN FINALIZADA</strong>
											<p class="text-muted mb-0">Fin de la venta</p>
										</div>
										<i class="fa fa-chevron-right align-self-center"></i>
									</div>
								</a>
							</c:if>
						</div>

						<div class="tab-content mt-3" id="nav-tabContent">
							<div class="tab-pane fade show card" id="list-home"
								role="tabpanel" aria-labelledby="list-home-list">
								<section class="page-section-one">
									<c:if test="${venta.estadoVenta eq 'P'.charAt(0)}">
										<div class="alert alert-warning mb-0" role="alert">
											<div class="container">
												Sus productos se encuentran <strong>en espera</strong> de
												ser confirmados.
											</div>
										</div>
									</c:if>
									<c:if test="${venta.estadoVenta eq 'T'.charAt(0)}">
										<div class="alert alert-success mb-0" role="alert">
											<div class="container">
												Los productos han sido seleccionados, y pasaran al proceso
												de <strong>transporte</strong>.
											</div>
										</div>
									</c:if>
									<div class="container">
										<div class="row">
											<div class="col-lg-12 py-3">
												<h5 class="letras text-left jumbotron-heading mb-0">ELECCION
													DE PRODUCTOS</h5>

												<c:if test="${venta.estadoVenta eq 'P'.charAt(0)}">
													<h3 class="section-subheading text-muted mb-0">Sus
														productos ofertados todavia no han sido aceptados, aquí
														podra visualizar el mejor precio que ofrecio según el
														producto.</h3>
												</c:if>
												<c:if test="${venta.estadoVenta != 'P'.charAt(0)}">
													<h3 class="section-subheading text-muted mb-0">La información de el/los
                                                    proveedores se encuentra mas abajo, así como tamnbién el total por
                                                    estos</h3>
												</c:if>
											</div>
										</div>

										<!-- PRODUCTOS -->
                                        <hr>
                                        <c:forEach items="${venta.ofertaProductos}" var="op">
                                            <c:forEach var="id" items="${idRecorridas}">
                                                <c:if test="${(op.producto.productor.idProd == id.key) && (id.value == false)}">
                                                    <c:set var="x" value="${id.setValue(true)}"></c:set>
                                                    <h6 class="mb-0 letras">
                                                        <mark>INFORMACIÓN PROVEEDOR</mark>
                                                    </h6>
                                                    <table class="table table-sm table-borderless">
                                                        <tbody>
                                                        <tr>
                                                            <td class="lead p-0"><strong>Nombre del
                                                                proveedor</strong>
                                                            </td>
                                                            <td class="lead p-0">${op.producto.productor.nombreProd} ${op.producto.productor.apellidoProd}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="lead p-0"><strong>Rut</strong></td>
                                                            <td class="lead p-0">${op.producto.productor.rutProd}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="lead p-0"><strong>Correo</strong></td>
                                                            <td class="lead p-0">${op.producto.productor.emailProd}</td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <h6 class="mb-2 letras">PRODUCTOS:</h6>
                                                    <c:forEach items="${venta.ofertaProductos}" var="op2">
                                                        <c:if test="${op.producto.productor.idProd == op2.producto.productor.idProd}">
                                                            <div class="card mb-3">
                                                                <div class="card-header text-uppercase">
                                                                    <table class="table table-sm table-borderless mb-0">
                                                                        <tr>
                                                                            <td class="text-left font-weight-bold">${op2.productoSolicitado.nombreProdS}</td>
                                                                            <td class="text-right font-weight-bold">${op2.productoSolicitado.cantidadProdS} ${op2.productoSolicitado.unidadProdS}</td>
                                                                        </tr>
                                                                    </table>
                                                                </div>
                                                                <div class="card-body">
                                                                    <div class="row">
                                                                        <input type="hidden" name="idProds[]"
                                                                               value="22">
                                                                        <div class="col">
                                                                            <div class="form-group mb-0">
                                                                                <label>Producto ofrecido</label>
                                                                                <input class="form-control"
                                                                                       disabled
                                                                                       value="${op2.producto.nombreProdu}"
                                                                                       style="background-color:white;">
                                                                            </div>
                                                                        </div>
                                                                        <div class="col">
                                                                            <div class="form-group mb-0">
                                                                                <label>Precio X Kg</label>
                                                                                <div class="input-group">
                                                                                    <div class="input-group-prepend">
																	<span class="input-group-text"><i
                                                                            class="fa fa-dollar-sign"></i></span>
                                                                                    </div>
                                                                                    <input type="text"
                                                                                           class="form-control"
                                                                                           placeholder="Precio"
                                                                                           aria-describedby="inputGroupPrepend"
                                                                                           disabled
                                                                                           style="background-color:white;"
                                                                                           value="<fmt:formatNumber
                                                                                    type="number"
                                                                                    value="${op2.precioOferta}"></fmt:formatNumber>">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="card-footer">
                                                                    <table class="table table-sm table-borderless mb-0">
                                                                        <tr>
                                                                            <td class="text-left font-weight-bold">
                                                                                Total
                                                                                del
                                                                                producto
                                                                            </td>
                                                                            <td class="text-right font-weight-bold">
                                                                                $<fmt:formatNumber
                                                                                    type="number"
                                                                                    value="${op2.precioOferta * ((op2.productoSolicitado.unidadProdS == 'KG')? op2.productoSolicitado.cantidadProdS : op2.productoSolicitado.cantidadProdS * 1000)}"></fmt:formatNumber></td>
                                                                        </tr>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                    <hr>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
										<!-- PRODUCTOS -->
										<div class="card mb-3 border-secondary">
											<div class="card-body">
												<table class="table table-sm table-borderless mb-0">
													<tr>
														<td class="text-left font-weight-bold"><h5
																class="mb-0">TOTAL POR LOS PRODUCTOS</h5></td>
														<td class="text-right font-weight-bold"><h5
																class="mb-0">
																$
																<fmt:formatNumber type="number"
																	value="${totalProductos}"></fmt:formatNumber>
															</h5></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</section>
							</div>
							<c:if test="${venta.estadoVenta != 'P'.charAt(0)}">
								<div class="tab-pane fade card" id="list-profile"
									role="tabpanel" aria-labelledby="list-profile-list">

									<!-- ALERTAS -->
									<c:choose>
										<c:when test="${venta.estadoVenta == 'T'.charAt(0)}">
											<div class="alert alert-warning mb-0" role="alert">
												<div class="container">
													El transporte se encuentra <strong>en espera</strong> de
													ser confirmado.
												</div>
											</div>
										</c:when>
										<c:when test="${venta.estadoVenta == 'E'.charAt(0)}">
											<div class="alert alert-success mb-0" role="alert">
												<div class="container">
													Has sido seleccionado como transporte en el <strong>proceso
														de envío</strong>
												</div>
											</div>
										</c:when>
									</c:choose>
									<!-- FIN ALERTAS -->


									<section class="page-section-one">
										<div class="container">

											<!-- TITULOS -->
											<div class="row">
												<div class="col-lg-12 py-3">
													<h5 class="letras text-left jumbotron-heading mb-0">ELECCIÓN
														DE TRANSPORTISTA</h5>
													<c:if test="${venta.estadoVenta eq 'T'.charAt(0)}">
														<h3 class="section-subheading text-muted mb-0">Transporte
															en proceso de subasta, aquí podrá visualizar las mejores
															subastas hechas para su venta</h3>
													</c:if>
													<c:if test="${venta.estadoVenta != 'T'.charAt(0)}">
														<h3 class="section-subheading text-muted mb-0">La
															información del encargado de realizar el transporte de
															sus productos se encuentra mas abajo, al igual que el
															total del Transporte</h3>
													</c:if>
												</div>
											</div>
											<!-- FIN TITULOS -->

											<!-- DESTINO Y ORIGEN -->
											<div class="row text-center">
												<div class="col">
													<button type="button" class="btn btn-secondary"
														style="min-width: 250px;" disabled>ORIGEN</button>
													<p class="mb-0 text-uppercase font-weight-bold">Chile</p>
												</div>
												<div class="col p-0">
													<div class="line-container py-2">
														<span class="line arrow-left"></span> <label>
															<h3 class="m-0">
																<i class="fas fa-shipping-fast"></i>
															</h3>
														</label> <span class="line arrow-right"></span>
													</div>
												</div>
												<div class="col">
													<button type="button" class="btn btn-secondary"
														style="min-width: 250px;" disabled>DESTINO</button>
													<p class="mb-0">${venta.solicitud.direccionDestinoSol}</p>
													<p class="mb-0 text-uppercase font-weight-bold">${pais}</p>
												</div>
											</div>
											<!-- FIN DESTINO Y ORIGEN -->
											<h6 class="mb-0 letras">
												<mark>INFORMACIÓN TRANSPORTISTA</mark>
											</h6>
											<table class="table table-sm table-borderless">
												<tbody>
													<tr>
														<td class="lead p-0"><strong>Nombre del
																transportista</strong></td>
														<td class="lead p-0">${ofertaTran.transportista.nombreTran}
															${ofertaTran.transportista.apellidosTran}</td>
													</tr>
													<tr>
														<td class="lead p-0"><strong>Rut</strong></td>
														<td class="lead p-0">${ofertaTran.transportista.rutTran}</td>
													</tr>
													<tr>
														<td class="lead p-0"><strong>Correo</strong></td>
														<td class="lead p-0">${ofertaTran.transportista.emailTran}</td>
													</tr>
													<tr>
														<td class="lead p-0"><strong>Patente</strong></td>
														<td class="lead p-0">${ofertaTran.transportista.patente.toUpperCase()}</td>
													</tr>
												</tbody>
											</table>
											<div class="card mb-3 border-secondary">
												<div class="card-body">
													<table class="table table-sm table-borderless mb-0">
														<tr>
															<td class="text-left font-weight-bold"><h5
																	class="mb-0">TOTAL POR TRANSPORTE</h5></td>
															<td class="text-right font-weight-bold"><h5
																	class="mb-0">
																	$
																	<fmt:formatNumber type="number"
																		value="${ofertaTran.precioOfertaOfert}"></fmt:formatNumber>
																</h5></td>
														</tr>
													</table>
												</div>
											</div>

										</div>
									</section>
								</div>
							</c:if>

							<c:if test="${venta.estadoVenta == 'E'.charAt(0)}">
								<!-- CONTENIDO TAB 3 -->
								<div class="tab-pane fade card" id="list-messages"
									role="tabpanel" aria-labelledby="list-messages-list">
									<section class="page-section-one">
										<div class="container">

											<!-- TITULOS -->
											<div class="row">
												<div class="col-lg-12 pt-3">
													<h5 class="letras text-left jumbotron-heading mb-0">ENVÍO
														DE PRODUCTOS</h5>
													<c:if test="${venta.estadoVenta eq 'E'.charAt(0)}">
														<h3 class="section-subheading text-muted mb-0">Sus
															productos estan siendo enviados, aquí puede visualizar el
															detalle de la compra</h3>
													</c:if>
													<c:if test="${venta.estadoVenta != 'E'.charAt(0)}">
														<h3 class="section-subheading text-muted mb-0">Los
															productos han llegado a destino</h3>
													</c:if>
												</div>
											</div>
											<!-- FIN TITULOS -->

											<hr>

											<!-- ENVIANDO -->
											<div class="row">
												<div class="col-lg-12 pb-3">
													<h6 class="mb-2 letras">PRODUCTOS:</h6>
													<table class="table table-bordered table-sm">
														<thead>
															<tr>
																<th>Producto</th>
																<th>Cantidad</th>
																<th class="text-right">Precio x Kg</th>
																<th class="text-right">Precio Total</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${venta.ofertaProductos}" var="op">
																<tr>
																	<td>${op.producto.nombreProdu}</td>
																	<td>${op.productoSolicitado.cantidadProdS}
																		${op.productoSolicitado.unidadProdS}</td>
																	<td class="text-right">${op.precioOferta}</td>
																	<td class="text-right">$<fmt:formatNumber
																			type="number"
																			value="${op.precioOferta * ((op.productoSolicitado.unidadProdS == 'KG')? op.productoSolicitado.cantidadProdS : op.productoSolicitado.cantidadProdS * 1000)}"></fmt:formatNumber></td>
																</tr>
															</c:forEach>
														</tbody>

													</table>
													<div class="card mb-3 border-secondary">
														<div class="card-body p-2">
															<table class="table table-sm table-borderless mb-0">
																<tr>
																	<td class="text-left font-weight-bold"><h6
																			class="mb-0">TOTAL POR PRODUCTOS</h6></td>
																	<td class="text-right font-weight-bold">
																		<h6 class="mb-0">
																			$
																			<fmt:formatNumber type="number"
																				value="${totalProductos}"></fmt:formatNumber>
																		</h6>
																	</td>
																</tr>
															</table>
														</div>
													</div>
													<h6 class="mb-2 letras">TRANSPORTE:</h6>
													<div class="card mb-3 border-secondary">
														<div class="card-body p-2">
															<table class="table table-sm table-borderless mb-0">
																<tr>
																	<td class="text-left font-weight-bold"><h6
																			class="mb-0">TOTAL POR TRANSPORTE</h6></td>
																	<td class="text-right font-weight-bold">
																		<h6 class="mb-0">
																			$
																			<fmt:formatNumber type="number"
																				value="${ofertaTran.precioOfertaOfert}"></fmt:formatNumber>
																		</h6>
																	</td>
																</tr>
															</table>
														</div>
													</div>

													<div class="card mb-3 border-success bg-verdeclaro">
														<div class="card-body">
															<table class="table table-sm table-borderless mb-0">
																<tr>
																	<td class="text-left font-weight-bold"><h5
																			class="mb-0">TOTAL DE LA COMPRA</h5></td>
																	<td class="text-right font-weight-bold">
																		<h5 class="mb-0">
																			$
																			<fmt:formatNumber type="number"
																				value="${venta.ofertaTransportistas.get(0).precioOfertaOfert + totalProductos}"></fmt:formatNumber>
																		</h5>
																	</td>
																</tr>
															</table>
														</div>
													</div>
												</div>
											</div>
											<!-- FIN ENVIANDO -->
										</div>
									</section>
								</div>
							</c:if>

						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>