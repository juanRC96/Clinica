<%@page import="entities.ObraSocial"%>
<%@page import="logic.LogicObraSocial"%>
<%@page import="java.util.LinkedList"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>borrar obra social</title>

<link rel="stylesheet" type="text/css" href="estilo_ingresarDatos.css">

</head>
<body>

	<form method="post" action="./svborrarobrasocial">
		<h1>
			<span style="font-family: Candara">Clinica UTN</span>
		</h1>
		<h2>
			<span style="font-family: Candara">Borrar obra social</span>
		</h2>

		<p>
			<%
			//TRAIGO LISTA DE OBRAS SOCIALES
			LogicObraSocial lo = new LogicObraSocial();
			LinkedList<ObraSocial> obrassociales = lo.mostrarObrasSociales();
			%>
			Seleccione la obra social a borrar <select name="idobrasocial">
				<option selected value="">Elige una opción</option>
				<%
				for (ObraSocial o : obrassociales) {
				%>
				<option value=<%=o.getId()%>><%=o.getNombre()%></option>
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

	<form method="post" action="menuObrasSociales.html">
		<p>
			<button id="bt3" type="submit">Volver</button>
	</form>

</body>
</html>