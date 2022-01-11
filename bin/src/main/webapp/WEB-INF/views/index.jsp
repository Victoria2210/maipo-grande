<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 15-08-2019
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Maipo Grande</title>
<link rel="stylesheet" href="\css\bootstrap.min.css">
<link rel="stylesheet" href="\css\styles.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>
<link rel="shortcut icon" type="image/png" href="img\icono-maipo.png" />
<script src="js/utilidades/index.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		setInterval(function() {
			setTimeout(function() {
				$("#alert").slideUp(1500);
			});
		}, 4000);
	});
</script>
</head>
<body>
	<!-- HEADER -->
	<header class="py-0 bg-dark text-white-50">
		<div class="text-center py-1">
			<a href="#" class="text-light mr-4"><i
				class='fab fa-facebook-square'></i></a> <a href="#"
				class="text-light mr-4"><i class='fab fa-twitter-square'></i></a> <a
				href="#" class="text-light"><i class='fab fa-instagram'></i></a>
		</div>
	</header>
	<c:if test="${logout != null}">
		<div
			class="alert alert-warning alert-dismissible fade show text-right mb-0 alerta-naranja"
			id="alert" role="alert" data-autohide="true" data-delay="5000">
			<strong>${logout}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>

	<c:if test="${error != null}">
		<div
			class="alert alert-danger alert-dismissible fade show text-right mb-0 alerta-roja"
			id="alert" role="alert" data-autohide="true" data-delay="5000">
			<strong>${error}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>


	<!-- NAVBAR -->
	<nav
		class="navbar navbar-expand-lg navbar-light bg-lg sticky-top navbar-verde">
		<a class="navbar-brand" href="#"> <img src="img/logo-maipo.png"
			height="50" class="d-inline-block align-top" alt="">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
			</ul>
			<form class="form-inline my-2 my-lg-0" action="/" method="post">
				<input class="form-control my-2 my-sm-0 mr-1 ml-1" type="text"
					placeholder="Correo Electronico" name="username"> <input
					class="form-control my-2 my-sm-0 mr-1 ml-1" type="password"
					placeholder="Contraseña" name="password">
				<button class="btn btnes btn-success my-2 my-sm-0 mr-1 ml-1 letras"
					type="submit">Ingresar</button>
			</form>
		</div>
	</nav>
	<header class="sc-main">
		<!-- TEXTO O IMAGENES -->
		<div class="sc-header-content"></div>
	</header>
	<!-- FIN HEADER -->



	<!-- LOGO/PRESENTACION -->
	<section class="bg-light page-section-one pb-3" id="portfolio">
		<div class="">
			<div class="container">
				<div class="row" style="margin-right: 0px !important;">
					<div class="col-lg-12 text-center">
						<img class="d-block mx-auto mt-5 mb-3" src="img/logo-maipo-bg.png"
							alt="" height="110">
						<h3 class="section-subheading text-muted mb-5">¡Maipo Grande
							es la feria virtual mas grande del pais! En nuestra página se
							encuentran registradas las mayores empresas dedicadas a la
							producción de frutas de la zona metropolitana.</h3>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- FIN LOGO/PRESENTACION -->

	<!-- IMAGENES -->
	<section id="imagenes">
		<div class="container-fluid p-0">
			<div class="row no-gutters">
				<div class="col-lg-4 col-sm-6">
					<a class="imagenes-box" href="#"> <img class="img-fluid"
						src="img/venta-1.jpg" alt="">
						<div class="imagenes-box-caption">
							<div class="project-category text-white-50">Proveedores</div>
							<div class="project-name">Los mejores proveedores</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6">
					<a class="imagenes-box" href="#"> <img class="img-fluid"
						src="img/venta-2.jpg" alt="">
						<div class="imagenes-box-caption">
							<div class="project-category text-white-50">Ventas</div>
							<div class="project-name">Ventas nacionales e
								internacionales</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6">
					<a class="imagenes-box" href="#"> <img class="img-fluid"
						src="img/venta-3.jpg" alt="">
						<div class="imagenes-box-caption">
							<div class="project-category text-white-50">Fruta</div>
							<div class="project-name">La mejor fruta del pais</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</section>

	<!-- NUESTROS SERVICIOS -->
	<section class="page-section" id="services">
		<div class="container">
			<h2 class="text-center mt-0 letras">Nuestros servicios</h2>
			<hr class="divider my-4">
			<div class="row">
				<div class="col-lg-3 col-md-6 text-center">
					<div class="mt-5">
						<i class="fas fa-4x fa-mobile-alt text-primary mb-4"></i>
						<h3 class="h4 mb-2">Responsivo</h3>
						<p class="text-muted mb-0">Podrás ver tus ventas en cualquier dispositivo</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 text-center">
					<div class="mt-5">
						<i class="fas fa-4x fa-laptop-code text-primary mb-4"></i>
						<h3 class="h4 mb-2">Actualizaciones</h3>
						<p class="text-muted mb-0">Estamos mejorando el sitio de manera constante</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 text-center">
					<div class="mt-5">
						<i class="fas fa-4x fa-globe text-primary mb-4"></i>
						<h3 class="h4 mb-2">Información</h3>
						<p class="text-muted mb-0">Tendras información sobre tus pedidos en todo momento</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 text-center">
					<div class="mt-5">
						<i class="fas fa-4x fa-lock text-primary mb-4"></i>
						<h3 class="h4 mb-2">Seguridad</h3>
						<p class="text-muted mb-0">Encriptación en contraseñas para sus cuentas</p>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- INFORMACION -->
	<section class="page-section bg-primary" id="about">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-8 text-center">
					<h2 class="text-white mt-0">¡Siguenos!</h2>
					<hr class="divider light my-4">
					<p class="text-white-50 mb-4"></p>
					<ul class="list-inline list-social">
						<li class="list-inline-item social-twitter"><a href="#">
								<i class="fab fa-twitter p-3"></i>
						</a></li>
						<li class="list-inline-item social-facebook"><a href="#">
								<i class="fab fa-facebook-f p-3"></i>
						</a></li>
						<li class="list-inline-item social-google-plus"><a href="#">
								<i class="fab fa-google-plus-g p-3"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<!-- FOOTER -->
	<footer class="py-2 bg-dark text-white-50">
		<div class="footer-copyright text-center py-3">
			© 2019 Copyright: <a href="#" class="text-success"> Quality
				Solution Team</a>
		</div>
	</footer>
	<!-- FIN FOOTER -->

	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
