<%@page import="entities.Paciente"%>
<%@page import="entities.Turno"%>
<%@page import="java.util.LinkedList"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>turnos</title>

<link rel="stylesheet" type="text/css" href="estilo_lista.css">

<%
LinkedList<Turno> turnos = (LinkedList) request.getAttribute("tablaTurnos");

boolean vacia = turnos.isEmpty();

if (vacia == false) {
%>
</head>
<body>

	<table>

		<td><b> APELLIDO DOCTOR </b></td>
		<td><b> NOMBRE DOCTOR </b></td>
		<td><b> FECHA TURNO</b></td>
		<td><b> HORA TURNO</b></td>
		<td><b> DNI PACIENTE </b></td>
		<td><b> APELLIDO PACIENTE </b></td>
		<td><b> NOMBRE PACIENTE </b></td>


		<%
		for (Turno t : turnos) {
		%>
		<tr>
			<td><%=t.getOdontologo().getApellido()%></td>
			<td><%=t.getOdontologo().getNombre()%></td>
			<td><%=t.getFecha()%></td>
			<td><%=t.getHora()%></td>
			<td><%=t.getPaciente().getDni()%></td>
			<td><%=t.getPaciente().getApellido()%></td>
			<td><%=t.getPaciente().getNombre()%></td>
		</tr>
		<%
		}
		} else {
		%>

		<h1>No hay turnos coincidentes</h1>

		<%
		}
		%>

	</table>

	<p>
	<form method="post" action="menuPublico.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>