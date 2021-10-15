package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Tratamiento;
import logic.LogicTratamientos;

/**
 * Servlet implementation class SvMostrarTratamientos
 */
@WebServlet("/svmostratratamientos")
public class SvMostrarTratamientos extends HttpServlet {
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

			LogicTratamientos lt = new LogicTratamientos();
			try {
				LinkedList<Tratamiento> tratamientos = lt.MostrarTratamientos();
				request.setAttribute("tablaTratamientos", tratamientos);
				request.getRequestDispatcher("mostrarTratamientos.jsp").forward(request, response);
				;
			} catch (Exception e) {
				response.sendRedirect("respuestaPrivado.jsp?mensaje=Hubo un error");
			}
		} else {
			response.sendRedirect("respuestaPublico.jsp?mensaje=Sesion no iniciada"); 
		}
	}

}
