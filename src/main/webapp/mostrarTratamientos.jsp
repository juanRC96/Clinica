<%@page import="entities.Tratamiento"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entities.Odontologo"%>
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
LinkedList<Tratamiento> tratamientos = (LinkedList) request.getAttribute("tablaTratamientos");

boolean vacia = tratamientos.isEmpty();

if (vacia == false) {
%>

</head>
<body>

<h1>Tratamientos</h1>

	<table>

		<td><b> ID </b></td>
		<td><b> DESCRIPCION </b></td>
		<td><b> PRECIO </b></td>

		<%
		for (Tratamiento t : tratamientos) {
		%>
		<tr>
			<td><%=t.getIdTratamiento()%></td>
			<td><%=t.getDescripcion()%></td>
			<td><%=t.getCosto()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<h1>No hay tratamientos coincidentes</h1>

		<%
		}
		%>
	</table>

	<p>
	<form method="post" action="menuTratamientos.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>