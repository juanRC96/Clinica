<%@page import="entities.ObraSocial"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicObraSocial"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>agregar paciente</title>

<link rel="stylesheet" type="text/css" href="estilo_ingresarDatos.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

</head>
<body>

	<h1>
		<span style="font-family: Candara">Clinica UTN</span>
	</h1>

	<h2>
		<span style="font-family: Candara">Agregar paciente</span>
	</h2>

	<form method="post" action="./svagregarpaciente">


		<p>
			Ingrese DNI <input type=text name="dni" size=50>
		</p>
		<p>
			Ingrese nombre <input type=text name="nombre" size=50>
		</p>
		<p>
			Ingrese apellido <input type=text name="apellido" size=50>
		</p>

		<p>
			<label for="datepicker">Ingrese fecha de nacimiento</label> <input
				type="text" name="fecha_nac" id="datepicker">
		</p>


		<%
		//CONSULTO LA LISTA DE DOCTORES
		LogicObraSocial lo = new LogicObraSocial();
		LinkedList<ObraSocial> obrassociales = lo.mostrarObrasSociales();
		%>
		<p>
			Seleccione la obra social <select name="obrasocial">
				<option selected value="">Particular</option>
				<%
				for (ObraSocial o : obrassociales) {
				%>
				<option value=<%=o.getId()%>><%=o.getNombre()%></option>
				<%
				}
				%>
			</select>
		<p>
			Ingrese direccion <input type=text name="direccion" size=50>
		</p>
		<p>
			Ingrese telefono <input type=text name="telefono" size=50>
		</p>

		<p>
			<button id=bt1 type=submit>Aceptar</button>
			<button id=bt2 type=reset>Borrar</button>
		</p>

	</form>

	<form method="post" action="menuPacientes.html">
		<p>
			<button id="bt3" type="submit">Volver</button>
	</form>

</body>
</html>