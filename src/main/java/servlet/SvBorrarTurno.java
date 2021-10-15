package servlet;

import java.io.IOException;
//import java.sql.Date;
//import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Paciente;
//import entities.Odontologo;
//import entities.Paciente;
import entities.Turno;
import logic.LogicTurnos;

/**
 * Servlet implementation class SvBorrarTurno
 */
@WebServlet("/svborrarturno")
public class SvBorrarTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvBorrarTurno() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			Turno t = new Turno();
			Paciente p = new Paciente();
			LogicTurnos lt = new LogicTurnos();

			String dniString = request.getParameter("dni");
			String fechaString = request.getParameter("fecha");

			if (dniString != "" && fechaString != "") {

				try {
					int dni = Integer.parseInt(dniString);

					LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

					p.setDni(dni);
					t.setPaciente(p);

					t.setFecha(fecha);

					lt.borrarTurno(t);
					response.sendRedirect("respuestaPrivado.jsp?mensaje=Turno borrado"); 
				}

				catch (Exception e) {
					response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en el borrado"); 
				}
			}

			else {
				response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en los datos ingresados"); 
			}

		} else {
			response.sendRedirect("respuestaPublico.jsp?mensaje=Sesion no iniciada"); 
		}
	}

}
