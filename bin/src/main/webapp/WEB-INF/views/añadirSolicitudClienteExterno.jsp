<%--
  Created by IntelliJ IDEA.
  User: Matias
  Date: 26/09/2019
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="layout/includes.jsp"></jsp:include>
    <script src="/js/utilidades/añadirSolicitudCliente.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/i18n/defaults-*.min.js"></script>
    <title>Solicitud de venta</title>
</head>
<jsp:include page="layout/cabecera.jsp"/>
<body>
<div class="page-wrapper chiller-theme toggled">
    <jsp:include page="layout/sidebarCliente.jsp" />
    <!-- SIDEBAR -->
    <main class="page-content">
        <div class="container-fluid">
            <div class="row mr-3 ml-3">
                <div class="col-lg mt-4 mb-4">
                    <h3 class="letras text-center mb-4">Crear solicitud compra externa</h3>
                    <div class="card card-body">
                        <form:form novalidate="novalidate" method="POST"
                                   class="needs-validation" action=""
                                   enctype="multipart/form-data" modelAttribute="solicitud">
                            <div class="form-group">
                                <form:label path="descripcionSol">Descripción</form:label>
                                <form:textarea cssClass="form-control" style="resize:none" path="descripcionSol" id="txtDescripcion" required="true" rows="3"></form:textarea>
                                <div class="invalid-feedback">Añadir descripción</div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <form:label path="direccionDestinoSol">Dirección</form:label>
                                    <form:input path="direccionDestinoSol" required="true" cssClass="form-control"></form:input>
                                    <div class="invalid-feedback">Dirección obligatoria</div>
                                </div>
                                <div class="form-group col-md-6">
                                    <form:label path="paisDestinoSol">País de destino</form:label>
                                    <form:select path="paisDestinoSol" required="true" cssClass="form-control">
                                        <form:option value="">Seleccione</form:option>
                                        <form:options items="${paises}"></form:options>
                                    </form:select>
                                    <div class="invalid-feedback">Seleccione país</div>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <form:label path="fechaEmisionSol">Fecha emisión</form:label>
                                    <form:input path="fechaEmisionSol"  required="true" type="date" id="fechaEmision" cssClass="form-control"></form:input>
                                    <div class="invalid-feedback">Fecha obligatoria</div>
                                </div>
                                <div class="form-group col-md-6">
                                    <form:label path="fechalimiteSol">Fecha limite</form:label>
                                    <form:input path="fechalimiteSol"  required="true" type="date" id="fechaLimite" cssClass="form-control"></form:input>
                                    <div class="invalid-feedback">Fecha obligatoria</div>
                                </div>
                            </div>
                            <c:set var="frutasSeparadas" value=""></c:set>
                                <c:forEach items="${frutas}" var="t" varStatus="i">
                                    <c:choose>
                                    <c:when test="${i.last}">
                                        <c:set var="frutasSeparadas" value="${frutasSeparadas.concat(t.name())}"></c:set>
                                    </c:when>
                                        <c:otherwise>
                                            <c:set var="frutasSeparadas" value="${frutasSeparadas.concat(t.name())}."></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                            <input type="hidden" value="${frutasSeparadas}" id="frutas">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg pl-0 pr-0">
                                        <label class="">Añadir productos</label>
                                        <!-- DIV A REPETIR SEGUN PLUS -->
                                        <div class="card card-body mb-3">
                                            <div class="media" id="divProductos1">
                                                <div class="media-body">
                                                    <div class="form-row">
                                                        <div class="form-group  col-md-6">
                                                            <label>Nombre del producto</label>
                                                            <select class="selectpicker form-control" data-live-search="true" required="true" name="nombreproducto[]">
                                                                <c:forEach items="${frutas}" var="f">
                                                                    <option value="${f.name()}">${f.name()}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <div class="invalid-feedback">Nombre obligatorio</div>
                                                        </div>
                                                        <div class="form-group  col-md-6">
                                                            <label>Cantidad</label>
                                                            <div class="input-group">
                                                                <input class="form-control" required="true" onkeypress="return event.charCode >= 48 && event.charCode <= 57" name="cantidadproducto[]"/>
                                                                <div class="input-group-prepend">
                                                                    <select name="unidadMasa[]" required="true" class="form-control">
                                                                        <option value="">Tipo</option>
                                                                        <c:forEach items="${tipoUnidadMasa}" var="i">
                                                                            <option value="${i.key}">${i.value}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <div class="invalid-feedback">Cantidad obligatoria</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <a class="align-self-center ml-3 text-secondary">
                                                    <i class="fas fa-trash-alt fa-2x mt-2" id="1" name="trash"
                                                       style="cursor: pointer"></i>
                                                </a>
                                            </div>
                                            <div class="media" id="divProductos2">
                                                <div class="media-body">
                                                    <div class="form-row">
                                                        <div class="form-group  col-md-6">
                                                            <label>Nombre del producto</label>
                                                            <select class="selectpicker form-control" data-live-search="true" required="true" name="nombreproducto[]">
                                                                <c:forEach items="${frutas}" var="f">
                                                                    <option value="${f.name()}">${f.name()}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <div class="invalid-feedback">Nombre obligatorio</div>
                                                        </div>
                                                        <div class="form-group  col-md-6">
                                                            <label>Cantidad</label>
                                                            <div class="input-group">
                                                                <input class="form-control" required="true" onkeypress="return event.charCode >= 48 && event.charCode <= 57" name="cantidadproducto[]"/>
                                                                <div class="input-group-prepend">
                                                                    <select name="unidadMasa[]" required="true" class="form-control">
                                                                        <option value="">Tipo</option>
                                                                        <c:forEach items="${tipoUnidadMasa}" var="i">
                                                                            <option value="${i.key}">${i.value}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <div class="invalid-feedback">Cantidad obligatoria</div>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                                <a class="align-self-center ml-3 text-secondary">
                                                    <i class="fas fa-trash-alt fa-2x mt-2" id="2" name="trash"
                                                       style="cursor: pointer"></i>
                                                </a>
                                            </div>

                                            <button type="button" id="btnAgregar"
                                                    class="btn btn-outline-success btn-block">
                                                <i class="fas fa-plus"></i></button>
                                        </div>

                                        <!-- FIN DIV A REPETIR SEGUN PLUS -->

                                    </div>
                                </div>
                            </div>
                            <div class="btn-group btn-block" role="group"
                                 aria-label="Basic example">
                                <button type="submit" class="btn btn-success">Solicitar</button>
                                <button type="button" class="btn btn-danger"
                                        onclick="location.href='/clienteExterno';">Cancelar</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<jsp:include page="layout/modalCargando.jsp"></jsp:include>
</body>
</html>
