package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Atencion;
import entities.Odontologo;
import entities.Paciente;
import entities.Tratamiento;
import logic.LogicAtenciones;

@WebServlet("/svagregaratencion")
public class SvAgregarAtencion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			// CREO INSTANCIAS
			Atencion a = new Atencion();
			Tratamiento t = new Tratamiento();
			Odontologo o = new Odontologo();
			Paciente p = new Paciente();
			LogicAtenciones la = new LogicAtenciones();

			String dniString = request.getParameter("dni");
			String matriculaString = request.getParameter("doctor");
			String tratamientoString = request.getParameter("idtrat");
			String observaciones = request.getParameter("observaciones");
			String fechaString = request.getParameter("fecha");

			if (dniString != "" && matriculaString != "" && tratamientoString != "" && fechaString != "") {

				try {
					// VALIDO QUE HAYA DATOS INGRESADOS (OBSERVACIONES PUEDE SER NULL)
					int dni = Integer.parseInt(dniString);
					int matricula = Integer.parseInt(matriculaString);
					int tratamiento = Integer.parseInt(tratamientoString);
					LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

					// LE ASIGNO A LA NUEVA INSTANCIA LOS VALORES DE LAS VARIABLES
					p.setDni(dni);
					a.setPaciente(p);

					o.setMatricula(matricula);
					a.setOdontologo(o);

					t.setIdTratamiento(tratamiento);
					a.setTratamiento(t);

					a.setObservaciones(observaciones);
					a.setFecha(fecha);

					la.agregarAtencion(a);

					response.sendRedirect("respuestaPrivado.jsp?mensaje=Atencion registrada"); 
				}

				catch (Exception x) {
					response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en el registro"); 
				}

			} else {
				response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en los datos ingresados"); 
			}

		} else {
			response.sendRedirect("respuestaPublico.jsp?mensaje=Sesion no iniciada"); 
		}
	}
}