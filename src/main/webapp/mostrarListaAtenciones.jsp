<%@page import="entities.Atencion"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entities.Odontologo"%>
<%@page import="java.util.LinkedList"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>atenciones</title>

<link rel="stylesheet" type="text/css" href="estilo_lista.css">

<% 
LinkedList<Atencion> atenciones =(LinkedList)request.getAttribute("tablaAtenciones");

boolean vacia = atenciones.isEmpty();

if(vacia==false){
%>

</head>
<body>

	<table>
	
	<td><b> ID ATENCIÓN </b></td>
	<td><b> FECHA ATENCIÓN </b></td>
	<td><b> DESCRIPCIÓN </b></td>
	<td><b> OBSERVACIONES </b></td>
	<td><b> PRECIO TRATAMIENTO </b></td>
	<td><b> APELLIDO DOCTOR </b></td>
	<td><b> NOMBRE DOCTOR </b></td>
	<td><b> DNI PACIENTE </b></td>
	<td><b> APELLIDO PACIENTE </b></td>
	<td><b> NOMBRE PACIENTE </b></td>

	<%
	for(Atencion a : atenciones){
		%>
		<tr>
			<td><%=a.getIdAtencion() %></td>
			<td><%=a.getFecha() %></td>
			<td><%=a.getTratamiento().getDescripcion() %></td>
			<td><%=a.getObservaciones() %></td>
			<td>$<%=a.getTratamiento().getCosto() %></td>
			<td><%=a.getOdontologo().getApellido() %></td>
			<td><%=a.getOdontologo().getNombre() %></td>
			<td><%=a.getPaciente().getDni() %></td>
			<td><%=a.getPaciente().getApellido() %></td>
			<td><%=a.getPaciente().getNombre() %></td>
		</tr>
		<% 
	}
}
		else
		{
		%>
		   <h1>No hay atenciones coincidentes</h1>
		 
		<%}
		%>
	</table>

<p>	<form method="post" action="menuAtencion.html">
		<button id="logout" type="submit">Volver al menú</button>
	</form>

</body>
</html>