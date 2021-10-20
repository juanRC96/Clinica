package servlet;

import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Odontologo;
import entities.Paciente;
//import entities.Odontologo;
//import entities.Paciente;
import entities.Turno;
import logic.LogicTurnos;

/**
 * Servlet implementation class Servlet5
 */
@WebServlet("/svregistro")
public class SvRegistroTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nivelAcceso = request.getParameter("acceso");
		HttpSession session = request.getSession(false);

		if ((session != null) || (nivelAcceso.equals("publico"))) {

			// CREO INSTANCIAS
			Turno t = new Turno();
			Paciente p = new Paciente();
			Odontologo o = new Odontologo();
			LogicTurnos lt = new LogicTurnos();

			String dniString = request.getParameter("dni");
			String matriculaString = request.getParameter("matricula");
			String fechaString = request.getParameter("fecha");
			String horaString = request.getParameter("horario");

			if (dniString != "" && matriculaString != "" && fechaString != "" && horaString != "") {

				try {
					int dni = Integer.parseInt(dniString);
					int matricula = Integer.parseInt(matriculaString);

					LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					LocalTime horario = LocalTime.parse(horaString, DateTimeFormatter.ofPattern("HH:mm:ss"));

					// LE ASIGNO A LA NUEVA INSTANCIA LOS VALORES DE LAS VARIABLES
					p.setDni(dni);
					t.setPaciente(p);

					o.setMatricula(matricula);
					t.setOdontologo(o);

					t.setFecha(fecha);
					t.setHora(horario);

					boolean reservado = lt.reservarTurno(t);

					if (reservado == true) {
						request.setAttribute("turno", t);

						if (nivelAcceso.equals("publico")) {
							request.getRequestDispatcher("mostrarDetalleTurnoReservadoPublico.jsp").forward(request,
									response);
						} else {
							request.getRequestDispatcher("mostrarDetalleTurnoReservado.jsp").forward(request, response);
						}

					} else {
						if (nivelAcceso.equals("publico")) {
							response.sendRedirect("respuestaPublico.jsp?mensaje=Turno ocupado");
						} else {
							response.sendRedirect("respuestaPrivado.jsp?mensaje=Turno ocupado");
						}
					}

				} catch (Exception e) {
					if (nivelAcceso.equals("publico")) {
						response.sendRedirect("respuestaPublico.jsp?mensaje=Error en el registro del turno");
					} else {
						response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en el registro del turno");
					}
				}
			} else {
				if (nivelAcceso.equals("publico")) {
					response.sendRedirect("respuestaPublico.jsp?mensaje=Error en los datos ingresados");
				} else {
					response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en los datos ingresados");
				}
			}
		} else {
			response.sendRedirect("respuestaPublico.jsp?mensaje=Sesion no iniciada"); 
		}
	}
}
