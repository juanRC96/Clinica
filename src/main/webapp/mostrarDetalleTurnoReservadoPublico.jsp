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
	case 1: mesEspanol="Enero";
	break;
	case 2: mesEspanol="Febrero";
	break;
	case 3: mesEspanol="Marzo";
	break;
	case 4: mesEspanol="Abril";
	break;
	case 5: mesEspanol="Mayo";
	break;
	case 6: mesEspanol="Junio";
	break;
	case 7: mesEspanol="Julio";
	break;
	case 8: mesEspanol="Agosto";
	break;
	case 9: mesEspanol="Septiembre";
	break;
	case 10: mesEspanol="Octubre";
	break;
	case 11: mesEspanol="Noviembre";
	break;
	case 12: mesEspanol="Diciembre";
	break;
	}
	
	%>

	<p style="text-align: center ; color:white ; font-size: 25px">
		El paciente <b><%=tur.getPaciente().getApellido()%>, <%=tur.getPaciente().getNombre()%></b>
		con número de documento <b><%=tur.getPaciente().getDni()%></b> será
		atendido por el doctor <b><%=tur.getOdontologo().getApellido()%>,
			<%=tur.getOdontologo().getNombre()%></b> el día <b><%=tur.getFecha().getDayOfMonth()%> de <%=mesEspanol%> del <%=tur.getFecha().getYear()%></b>
		a las <b><%=tur.getHora()%></b> hs.
	</p>

	<p>
	<form method="post" action="index.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>