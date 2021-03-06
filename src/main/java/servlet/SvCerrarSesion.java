package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/svcerrarsesion")
public class SvCerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		// VERIFICO SI LA SESION YA ESTE CERRADA
		if (session == null) {
			response.sendRedirect("respuestaPublico.jsp?mensaje=No hay sesion iniciada");
		}

		else {
			session.invalidate();
			response.sendRedirect("respuestaPublico.jsp?mensaje=Sesion cerrada");
		}

	}

}
