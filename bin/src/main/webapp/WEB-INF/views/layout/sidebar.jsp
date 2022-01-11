
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
          Created by IntelliJ IDEA.
          User: Matias
          Date: 22/09/2019
          Time: 23:25
          To change this template use File | Settings | File Templates.
        --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
</head>
<body>
	<!-- SIDEBAR -->
	<a id="show-sidebar" class="btn btn-sm btn-dark"
		style="margin-top: 73px; min-width: 65px" href="javascript:void(0)">
		<img class="img-responsive img-rounded" width="50"
		src="/img/icono-maipo.png">
	</a>
	<nav id="sidebar" class="sidebar-wrapper">
		<div style="height: 76px;"></div>
		<div class="sidebar-content">
			<div class="sidebar-brand">
				<a href="#">MAIPO GRANDE</a>
				<div id="close-sidebar">
					<i class="fas fa-times"></i>
				</div>
			</div>
			<div class="sidebar-header">
				<div class="user-pic">
					<img class="img-responsive img-rounded"
						src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg"
						alt="User picture">
				</div>
				<div class="user-info">
					<span class="user-name">${productor.nombreProd} <strong>${productor.apellidoProd}</strong>
					</span> <span class="user-role">Productor</span> <span class="user-status">
						<span class="badge badge-pill badge-success">${ventasActivas.size()}</span>
						<span>Ventas en proceso</span>
					</span>
				</div>
			</div>
			<!-- sidebar-search  -->
			<div class="sidebar-menu">
				<ul>
					<li class="header-menu"><span>General</span></li>
					<li class="sidebar"><a href="#"> <i
							class="far fa-id-card"></i> <span>Mi perfil</span>
					</a></li>
					<li class="sidebar"><a href="/productos"> <i
							class="far fa-lemon"></i> <span>Mis Productos</span>
					</a></li>
					<li class="sidebar-dropdown"><a href="javascript:void(0)"> <i
							class="fa fa-shopping-cart"></i> <span>Ventas en proceso</span> <span
							class="badge badge-pill badge-success">${ventasActivas.size()}</span>
					</a>
						<div class="sidebar-submenu">
							<ul>
								<c:forEach items="${ventasActivas}" var="v">
									<li><a href="/detalleVentaProductor/${v.idVenta}">Venta Nº${v.idVenta}</a></li>
								</c:forEach>
							</ul>
						</div></li>

					<li class="sidebar"><a href="#"> <i
							class="far fa-list-alt"></i> <span>Ventas historicas</span>
					</a></li>
					<li class="header-menu"><span>Extra</span></li>
					<li class="sidebar"><a href="#"> <i
							class="fa fa-book"></i> <span>Estado de contrato</span> <span
							class="badge badge-pill badge-danger">¡Alerta!</span>
					</a></li>
					<li style="height: 100px;"></li>
				</ul>
			</div>
			<!-- sidebar-menu  -->
		</div>
		<!-- sidebar-content  -->
		<div class="sidebar-footer py-3 text-white-50 text-center"
			style="min-height: 58px">
			<div class="footer-copyright ml-3">
				© 2019 Copyright: <a href="#" class="text-success">
					QualitySolution</a>
			</div>
		</div>
	</nav>
	<!-- FIN SIDEBAR -->

</body>
</html>
