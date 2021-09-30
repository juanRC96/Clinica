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
LinkedList<Odontologo> doctores = (LinkedList) request.getAttribute("tablaDoctores");

boolean vacia = doctores.isEmpty();

if (vacia == false) {
%>

</head>
<body>

	<table>

		<td><b> MATRÍCULA </b></td>
		<td><b> NOMBRE </b></td>
		<td><b> APELLIDO </b></td>

		<%
		for (Odontologo o : doctores) {
		%>
		<tr>
			<td><%=o.getMatricula()%></td>
			<td><%=o.getNombre()%></td>
			<td><%=o.getApellido()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<h1>No hay doctores coincidentes</h1>

		<%
		}
		%>
	</table>

	<p>
	<form method="post" action="menuDoctores.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>