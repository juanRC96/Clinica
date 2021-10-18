<%@page import="entities.Odontologo"%>
<%@page import="logic.LogicOdontologos"%>
<%@page import="entities.Paciente"%>
<%@page import="java.util.LinkedList"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>reservarturnos</title>

<link rel="stylesheet" type="text/css" href="estilo_ingresarDatos.css">

</head>
<body>

	<h1>
		<span style="font-family: Candara">Clinica UTN</span>
	</h1>
	<h2>
		<span style="font-family: Candara">Reserva tu turno</span>
	</h2>


	<form method="post" action="./svbuscarpacientesolo"
		action="reservarTurnos.jsp">

		<input type=hidden name="acceso" value="publico" size=50>

		<p>
			Ingrese el DNI: <input type=text name="dni" size=50>
		</p>

		<p>
			<button type=submit>Buscar paciente</button>
			<%
			Paciente p = (Paciente) request.getAttribute("datosPaciente");

			//VALIDO QUE HAYA ALGUN PACIENTE CON EL DNI INGRESADO, EN EL CASO DE QUE NO, NOTIFICO 
			if (p != null) {
			%>

			<%
			String nombre = p.getNombre();
			String apellido = p.getApellido();

			if (nombre != null && apellido != null) {
			%>
		
		<p>
			Paciente:<b> <%=apellido%>, <%=nombre%>
			</b>
		</p>

		<%
		}

		else {
		%>
		<p style="color: #FF0000">Paciente no registrado</p>
		<%
		}
		}
		%>

	</form>

	<form method="post" action="./svregistro">

		<input type=hidden name="acceso" value="publico" size=50>

		<%
		//ACA VALIDO QUE SE HAYA INGRESADO UN VALOR EN EL CAMPO DNI
		if (request.getParameter("dni") != null) {
		%>
		<input name="dni" type="hidden"
			value="<%=request.getParameter("dni")%>">
		<%
		} else {
		%>
		<input name="dni" type="hidden" value=0>
		<%
		}
		%>

		<%
		//CONSULTO LA LISTA DE DOCTORES
		LogicOdontologos lo = new LogicOdontologos();
		LinkedList<Odontologo> doctores = lo.mostrarOdontologos();
		%>
		<p>
			Seleccione un profesional <select name="matricula">
				<option selected value="0">Elige una opción</option>
				<%
				for (Odontologo o : doctores) {
				%>
				<option value=<%=o.getMatricula()%>><%=o.getApellido()%>,
					<%=o.getNombre()%></option>
				<%
				}
				%>
			</select>
		<div>
			<p>

				<label>Ingrese la fecha </label> <input type="date" id="start"
					name="fecha" min="" max="">

			</p>
		</div>

		<div>
			<p>
				Seleccione un horario <select name="horario">
					<option selected value="0">Elige una opción</option>
					<option value="09:00:00">09:00</option>
					<option value="09:30:00">09:30</option>
					<option value="10:00:00">10:00</option>
					<option value="10:30:00">10:30</option>
					<option value="11:00:00">11:00</option>
					<option value="11:30:00">11:30</option>
					<option value="12:00:00">12:00</option>
					<option value="13:00:00">13:00</option>
					<option value="13:30:00">13:30</option>
					<option value="14:00:00">14:00</option>
					<option value="14:30:00">14:30</option>
					<option value="15:00:00">15:00</option>
					<option value="15:30:00">15:30</option>
					<option value="16:00:00">16:00</option>
				</select>
			</p>
		</div>

		<p>
			<button id=bt1 type=submit>Aceptar</button>
			<button id=bt2 type=reset>Borrar</button>
		</p>

	</form>

	<form method="post" action="index.html">
		<p>
			<button id="bt3" type="submit">Volver</button>
	</form>

</body>
</html>