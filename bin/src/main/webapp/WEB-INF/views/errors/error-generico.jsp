<%--
  Created by IntelliJ IDEA.
  User: Matias
  Date: 02-11-2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagina Error</title>
</head>
<body>
<h2>Error</h2>
<div>
    Status code: <b>${status.toString()}</b>
    <div>Exception Message: <b>${e.message}</b></div>
    <div>Detail Message: <b>${e.toString()}</b></div>

</div>
</body>
</html>
