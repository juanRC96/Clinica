<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>estado</title>
<link rel="stylesheet" type="text/css" href="estilo_menu.css">
</head>
<body>
	<h1 style="text-align: center"><%=request.getParameter("mensaje")%></h1>

	<form method="post" action="menuGeneral.html">
		<button id="iniciar" type="submit">Volver al menú</button>
	</form>

</body>
</html>