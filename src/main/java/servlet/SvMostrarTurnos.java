package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.LogicTurnos;
import entities.Turno;

/**
 * Servlet implementation class Servlet4
 */
@WebServlet("/servletmostrarturnos")
public class SvMostrarTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// VALIDO QUE LA SESION HAYA SIDO INICIADA false will return current session if
		// current session exists. If not, it will not create a new session.
		HttpSession session = request.getSession(false);
		if (session != null) {
			LogicTurnos lt = new LogicTurnos();

			try {
				LinkedList<Turno> turnos = lt.mostrarTurnos();
				request.setAttribute("tablaTurnos", turnos);
				request.getRequestDispatcher("mostrarListaTurnos.jsp").forward(request, response);
			} catch (Exception e) {
				response.sendRedirect("mostrarRespuesta.jsp?mensaje=Hubo un error");
			}
		} else {
			response.sendRedirect("errorSesion.html");
		}

	}
}
