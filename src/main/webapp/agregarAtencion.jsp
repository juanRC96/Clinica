<%@page import="entities.Tratamiento"%>
<%@page import="logic.LogicTratamientos"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Odontologo"%>
<%@page import="logic.LogicOdontologos"%>
<%@page import="entities.Paciente"%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>agregar atencion</title>

<link rel="stylesheet" type="text/css" href="estilo_ingresarDatos.css">
<link rel="stylesheet"		href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src= "https://code.jquery.com/jquery-1.12.4.js"></script>
<script src= "https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script> 

$(function (){
	$("#datepicker").datepicker();
});
</script>

</head>

<body>

	<h1>
		<span style="font-family: Candara">Clinica UTN</span>
	</h1>
	<h2>
		<span style="font-family: Candara">Registrar atención</span>
	</h2>

<form method="post" action="./svbuscarpacientesolo" action="agregarAtencion.jsp">
	
	<input type=hidden name="rol" value="agregaratencion">
	<p>Ingrese el DNI: <input type=text name="dni" size=50></p>
	
	<p><button type=submit>Buscar paciente</button>
	<%
	Paciente p = (Paciente)request.getAttribute("datosPaciente");
			
			//VALIDO QUE HAYA ALGUN PACIENTE CON EL DNI INGRESADO, EN EL CASO DE QUE NO, NOTIFICO 
			if (p!=null){
	%>
	
	<%
		String nombre = p.getNombre();
			String apellido = p.getApellido();
			
			if(nombre!=null && apellido!=null){
		%>
	
	<p>Paciente:<b> <%=apellido%>, <%=nombre%> </b></p>
	
	<%
		}
			
			else{
		%>
		<p style="color: #FF0000">Paciente no registrado</p>
		<%
		}
				}
		%>

</form>	

<form method="post" action="./svagregaratencion">

<%
//ACA VALIDO QUE SE HAYA INGRESADO UN VALOR EN EL CAMPO DNI
if(request.getParameter("dni")!=null)
{
%>
	<input name="dni" type="hidden" value="<%=request.getParameter("dni")%>">
<%
}
else
{
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
<p>Seleccione un profesional  <select name="doctor">
			<option selected value="0">Elige una opción</option>
<%
for(Odontologo o : doctores)
{
%>
<option value=<%=o.getMatricula()%>><%=o.getApellido()%>, <%=o.getNombre()%></option>
<%
}
%>
</select>

<p>

<%
//CONSULTO LA LISTA DE TRATAMIENTOS
LogicTratamientos lt = new LogicTratamientos();
LinkedList<Tratamiento> tratamientos = lt.MostrarTratamientos();
%>
Seleccione un tipo de tratamiento  <select name="idtrat">
			<option selected value="0">Elige una opción</option>
<%
for(Tratamiento t : tratamientos)
{%>
<option value=<%=t.getIdTratamiento() %>><%=t.getDescripcion()%></option>
<%
}
%>
</select>
</p>

<p>Ingrese observaciones <input type=text name="observaciones" size=50></p>

<p>
	<label for="datepicker">Ingrese fecha </label>
	<input type="text" name="fecha" id="datepicker">
</p>

<p>
	<button id=bt1 type=submit>Aceptar</button>
	<button id=bt2 type=reset>Borrar</button>
</p>


</form>

<form method="post" action="menuAtencion.html"><p><button id="bt3" type="submit">Volver</button></form>

</body>
</html>