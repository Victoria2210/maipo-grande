<%--
  Created by IntelliJ IDEA.
  User: Matias
  Date: 02-11-2019
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagina No Encontrada</title>
    <jsp:include page="../layout/includes.jsp"></jsp:include>
    <link rel="shortcut icon" type="image/png" href="\img\icono-maipo.png" />
    <link rel="stylesheet" href="\css\errorstyles.css">

</head>
<body>
<!-- NAVBAR -->
<nav
        class="navbar navbar-expand-lg navbar-light bg-lg sticky-top navbar-trasnparente">
    <a class="navbar-brand" href="/"> <img src="/img/logo-maipo.png"
                                           height="50" class="d-inline-block align-top" alt="">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

</nav>
<div id="clouds">
    <div class="cloud x1"></div>
    <div class="cloud x1_5"></div>
    <div class="cloud x2"></div>
    <div class="cloud x3"></div>
    <div class="cloud x4"></div>
    <div class="cloud x5"></div>
</div>

<div class="texto" align="center">
    4<img class="error" src="/img/icono-maipo.png" alt="Logo Maipo Grande">4
    <hr>
</div>
<div  align="center">
    <p class="subtexto">Esta página no ha sido<br>encontrada</p>
    <div>
        <button class="btn btn-success" onclick="window.location.href='/';">Volver atrás</button>
    </div>

</div>



</body>
</html>
