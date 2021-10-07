<%@page import="entities.ObraSocial"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.LinkedList"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>doctores</title>

<link rel="stylesheet" type="text/css" href="estilo_lista.css">

<%
LinkedList<ObraSocial> obrassociales = (LinkedList) request.getAttribute("tablaOS");

boolean vacia = obrassociales.isEmpty();

if (vacia == false) {
%>

</head>
<body>

<h1>Obras sociales</h1>

	<table>

		<td><b> ID </b></td>
		<td><b> NOMBRE OBRA SOCIAL </b></td>

		<%
		for (ObraSocial o : obrassociales) {
		%>
		<tr>
			<td><%=o.getId()%></td>
			<td><%=o.getNombre()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<h1>No hay obras sociales coincidentes</h1>

		<%
		}
		%>
	</table>

	<p>
	<form method="post" action="menuObrasSociales.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>