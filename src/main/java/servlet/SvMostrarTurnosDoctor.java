package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Odontologo;
import entities.Turno;
import logic.LogicTurnos;

/**
 * Servlet implementation class SvMostrarTurnosDoctor
 */
@WebServlet("/svmostrarturnosdoctor")
public class SvMostrarTurnosDoctor extends HttpServlet {
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
			Odontologo o = new Odontologo();
			LogicTurnos lt = new LogicTurnos();

			String matriculaString = request.getParameter("matricula");

			if (matriculaString != "") {

				try {
					int matricula = Integer.parseInt(matriculaString);

					o.setMatricula(matricula);
					t.setOdontologo(o);

					LinkedList<Turno> turnos = lt.buscarTurnoDoctor(t);
					request.setAttribute("tablaTurnos", turnos);
					request.getRequestDispatcher("mostrarListaTurnos.jsp").forward(request, response);
				} catch (Exception e) {
					response.sendRedirect("error.html");
				}
			}

			else {
				response.sendRedirect("errorDatosIngresados.html");
			}
		}
		else {
			response.sendRedirect("errorSesion.html");
		}

	}

}
