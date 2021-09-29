package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Paciente;
import entities.Turno;
import logic.LogicTurnos;

/**
 * Servlet implementation class SvBuscarTurnoPorPaciente
 */
@WebServlet("/svconsultarturno")
public class SvConsultarTurnos extends HttpServlet {
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
			Turno t = new Turno();
			Paciente p = new Paciente();
			LogicTurnos lt = new LogicTurnos();

			String dniString = request.getParameter("dni");

			if (dniString != "") {

				try {
					int dni = Integer.parseInt(dniString);

					p.setDni(dni);
					t.setPaciente(p);

					LinkedList<Turno> turnos = lt.buscarTurnoporDni(t);
					request.setAttribute("tablaTurnos", turnos);
					request.getRequestDispatcher("mostrarListaTurnos.jsp").forward(request, response);

				} catch (Exception e) {
					response.sendRedirect("error.html");
				}
			}
			
			else {
				response.sendRedirect("errorDatosIngresados.html");
			}

		} else {
			response.sendRedirect("errorSesion.html");
		}

	}

}
