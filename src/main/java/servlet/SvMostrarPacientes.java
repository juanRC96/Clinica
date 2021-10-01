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
import logic.LogicPacientes;

/**
 * Servlet implementation class SvMostrarPacientes
 */
@WebServlet("/svmostrarpacientes")
public class SvMostrarPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			LogicPacientes lp = new LogicPacientes();

			try {
				LinkedList<Paciente> pacientes = lp.mostrarPacientes();

				request.setAttribute("tablaPacientes", pacientes);
				request.getRequestDispatcher("mostrarListaPacientes.jsp").forward(request, response);
			} catch (Exception e) {
				response.sendRedirect("error.html");
			}
		}

		else {
			response.sendRedirect("errorSesion.html");
		}
	}

}
