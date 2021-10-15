<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
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

<link rel="stylesheet" type="text/css" href="estilo_lista.css">

<%
Turno t = (Turno) request.getAttribute("turno");
%>
</head>
<body>

	<h1>Turno registrado</h1>

	<%
	LogicTurnos lt = new LogicTurnos();

	Turno tur = lt.buscarDatosTurno(t);

	LocalDate fechaTurno = t.getFecha();

	String formattedDate = fechaTurno.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
	%>

	<p style="text-align: center; color: white; font-size: 25px">
		El paciente <b><%=tur.getPaciente().getNombre()%> <%=tur.getPaciente().getApellido()%></b>
		con número de DNI <b><%=tur.getPaciente().getDni()%></b>, será
		atendido por el doctor <b><%=tur.getOdontologo().getNombre()%> <%=tur.getOdontologo().getApellido()%></b>
		el día <b><%=formattedDate%></b> a las <b><%=tur.getHora()%></b> hs.
	</p>

	<p>
	<form method="post" action="index.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>