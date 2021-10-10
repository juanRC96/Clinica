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

	<h1>Turno registrado</h1>

	<%
	LogicTurnos lt = new LogicTurnos();

	Turno tur = lt.buscarDatosTurno(t);
	
	//esto lo podria hacer con DateTimeFormatter pero no se como (consultar)
	int mesValor = t.getFecha().getMonthValue();
	String mesEspanol=null;
	
	switch(mesValor)
	{
	case 1: mesEspanol="enero";
	break;
	case 2: mesEspanol="febrero";
	break;
	case 3: mesEspanol="marzo";
	break;
	case 4: mesEspanol="abril";
	break;
	case 5: mesEspanol="mayo";
	break;
	case 6: mesEspanol="junio";
	break;
	case 7: mesEspanol="julio";
	break;
	case 8: mesEspanol="agosto";
	break;
	case 9: mesEspanol="septiembre";
	break;
	case 10: mesEspanol="octubre";
	break;
	case 11: mesEspanol="noviembre";
	break;
	case 12: mesEspanol="diciembre";
	break;
	}
	
	%>

	<p style="text-align: center ; color:white ; font-size: 25px">
		El paciente <b><%=tur.getPaciente().getNombre()%> <%=tur.getPaciente().getApellido()%></b>
		con número de DNI <b><%=tur.getPaciente().getDni()%></b>, será
		atendido por el doctor <b><%=tur.getOdontologo().getNombre()%> <%=tur.getOdontologo().getApellido()%></b> el día <b><%=tur.getFecha().getDayOfMonth()%> de <%=mesEspanol%> del <%=tur.getFecha().getYear()%></b>
		a las <b><%=tur.getHora()%></b> hs.
	</p>

	<p>
	<form method="post" action="index.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>