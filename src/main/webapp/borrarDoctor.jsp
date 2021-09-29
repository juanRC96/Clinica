<%@page import="entities.Odontologo"%>
<%@page import="logic.LogicOdontologos"%>
<%@page import="java.util.LinkedList"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>borrar doctor</title>

<link rel="stylesheet" type="text/css" href="estilo_ingresarDatos.css">

</head>
<body>

<form method="post" action="./svborrardoctor">
	<h1>
		<span style="font-family: Candara">Clinica UTN</span>
	</h1>
	<h2>
		<span style="font-family: Candara">Borrar doctor</span>
	</h2>
	
	<p>
<%
//TRAIGO LISTA DE ODONTOLOGOS
LogicOdontologos lo = new LogicOdontologos();
LinkedList<Odontologo> odontologos = lo.mostrarOdontologos();
%>
Seleccione el doctor a borrar  <select name="matricula">
			<option selected value="0">Elige una opción</option>
<%
for(Odontologo o : odontologos)
{%>
<option value=<%=o.getMatricula()%>><%=o.getApellido()%>, <%=o.getNombre()%></option>
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

<form method="post" action="menuDoctores.html"><p><button id="bt3" type="submit">Volver</button></form>

</body>
</html>