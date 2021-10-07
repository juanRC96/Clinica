<%@page import="logic.LogicTurnos"%>
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

<link rel="stylesheet" type="text/css" href="estilo_listaPublico.css">

<%
Turno t = (Turno) request.getAttribute("turno");
%>
</head>
<body>

	<h1>El turno quedó registrado con los siguientes datos</h1>

	<%
	LogicTurnos lt = new LogicTurnos();

	Turno tur = lt.buscarDatosTurno(t);
	%>

	<table>

		<td><b> APELLIDO DOCTOR </b></td>
		<td><b> NOMBRE DOCTOR </b></td>
		<td><b> FECHA TURNO</b></td>
		<td><b> HORA TURNO</b></td>
		<td><b> DNI PACIENTE </b></td>
		<td><b> APELLIDO PACIENTE </b></td>
		<td><b> NOMBRE PACIENTE </b></td>

		<tr>
			<td><%=tur.getOdontologo().getApellido()%></td>
			<td><%=tur.getOdontologo().getNombre()%></td>
			<td><%=tur.getFecha()%></td>
			<td><%=tur.getHora()%></td>
			<td><%=tur.getPaciente().getDni()%></td>
			<td><%=tur.getPaciente().getApellido()%></td>
			<td><%=tur.getPaciente().getNombre()%></td>
		</tr>

	</table>

	<p>
	<form method="post" action="index.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>