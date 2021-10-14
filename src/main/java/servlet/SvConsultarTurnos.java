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

		String nivelAcceso = request.getParameter("acceso");
		HttpSession session = request.getSession(false);

		if ((session != null) || (nivelAcceso.equals("publico"))) {
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

					if (nivelAcceso.equals("publico")) {
						request.getRequestDispatcher("mostrarListaTurnosPublico.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("mostrarListaTurnos.jsp").forward(request, response);
					}

				} catch (Exception e) {
					if (nivelAcceso.equals("publico")) {
						response.sendRedirect("errorPublico.html");
					} else {
						response.sendRedirect("mostrarRespuesta.jsp?mensaje=Hubo un error");
					}
				}
			}

			else {
				if (nivelAcceso.equals("publico")) {
					response.sendRedirect("errorDatosIngresadosPublico.html");
				} else {
					response.sendRedirect("mostrarRespuesta.jsp?mensaje=Error en los datos ingresados");
				}
			}
		} else {
			response.sendRedirect("errorSesion.html");
		}
	}

}
