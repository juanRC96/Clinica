<%@page import="entities.ObraSocial"%>
<%@page import="entities.Paciente"%>
<%@page import="java.util.LinkedList"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pacientes</title>

<link rel="stylesheet" type="text/css" href="estilo_lista.css">

<%
LinkedList<Paciente> pacientes = (LinkedList) request.getAttribute("tablaPacientes");

String obraSoc;

boolean vacia = pacientes.isEmpty();

if (vacia == false) {
%>

</head>
<body>

	<h1>Pacientes</h1>

	<table>

		<td><b> DNI </b></td>
		<td><b> NOMBRE </b></td>
		<td><b> APELLIDO </b></td>
		<td><b> FECHA NACIMIENTO </b></td>
		<td><b> DIRECCIÓN </b></td>
		<td><b> TELÉFONO </b></td>
		<td><b> OBRA SOCIAL </b></td>

		<%
		for (Paciente p : pacientes) {

			String nombreOs = p.getOs().getNombre();

			if (nombreOs == null) {
				nombreOs = "-";
			} else {
				nombreOs = p.getOs().getNombre();
			}
		%>
		<tr>
			<td><%=p.getDni()%></td>
			<td><%=p.getNombre()%></td>
			<td><%=p.getApellido()%></td>
			<td><%=p.getFecha_nac()%></td>
			<td><%=p.getDireccion()%></td>
			<td><%=p.getTelefono()%></td>
			<td><%=nombreOs%></td>
		</tr>
		<%
		}
		} else {
		%>
		<h1>No hay pacientes coincidentes</h1>

		<%
		}
		%>
	</table>

	<p>
	<form method="post" action="menuPacientes.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>