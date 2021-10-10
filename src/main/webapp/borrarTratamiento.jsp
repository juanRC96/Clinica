<%@page import="entities.Tratamiento"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicTratamientos"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>borrar tratamiento</title>

<link rel="stylesheet" type="text/css" href="estilo_ingresarDatos.css">

</head>
<body>

	<form method="post" action="./svborrartratamiento">
		<h1>
			<span style="font-family: Candara">Clinica UTN</span>
		</h1>
		<h2>
			<span style="font-family: Candara">Borrar tratamiento</span>
		</h2>

		<p>
			<%
			//CONSULTO LA LISTA DE TRATAMIENTOS
			LogicTratamientos lt = new LogicTratamientos();
			LinkedList<Tratamiento> tratamientos = lt.MostrarTratamientos();
			%>
			Seleccione un tipo de tratamiento <select name="tratamiento">
				<option selected value="">Elige una opción</option>
				<%
				for (Tratamiento t : tratamientos) {
				%>
				<option value=<%=t.getIdTratamiento()%>><%=t.getDescripcion()%></option>
				<%
				}
				%>
			</select>
		</p>

		<p>
			<button id=bt1 type=submit>Aceptar</button>
			<button id=bt2 type=reset>Borrar</button>
		</p>

	</form>

	<form method="post" action="menuTratamientos.html">
		<p>
			<button id="bt3" type="submit">Menú</button>
	</form>

</body>
</html>