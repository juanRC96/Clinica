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

	<h1>Turnos registrados</h1>

	<table>

		<td><b> DNI PACIENTE </b></td>
		<td><b> PACIENTE </b></td>
		<td><b> FECHA TURNO</b></td>
		<td><b> HORA TURNO</b></td>
		<td><b> DOCTOR </b></td>

		<%
		for (Turno t : turnos) {

			int mesValor = t.getFecha().getMonthValue();
			String mesEspanol = null;

			switch (mesValor) {
			case 1:
				mesEspanol = "enero";
				break;
			case 2:
				mesEspanol = "febrero";
				break;
			case 3:
				mesEspanol = "marzo";
				break;
			case 4:
				mesEspanol = "abril";
				break;
			case 5:
				mesEspanol = "mayo";
				break;
			case 6:
				mesEspanol = "junio";
				break;
			case 7:
				mesEspanol = "julio";
				break;
			case 8:
				mesEspanol = "agosto";
				break;
			case 9:
				mesEspanol = "septiembre";
				break;
			case 10:
				mesEspanol = "octubre";
				break;
			case 11:
				mesEspanol = "noviembre";
				break;
			case 12:
				mesEspanol = "diciembre";
				break;
			}
		%>
		<tr>
			<td><%=t.getPaciente().getDni()%></td>
			<td><%=t.getPaciente().getApellido()%>, <%=t.getPaciente().getNombre()%></td>
			<td><%=t.getFecha().getDayOfMonth()%> de <%=mesEspanol%> del <%=t.getFecha().getYear()%></td>
			<td><%=t.getHora()%> hs.</td>
			<td><%=t.getOdontologo().getApellido()%>, <%=t.getOdontologo().getNombre()%></td>
		</tr>
		<%
		}
		} else {
		%>

		<h2>No hay turnos coincidentes</h2>

		<%
		}
		%>

	</table>

	<p>
	<form method="post" action="index.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>