package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Tratamiento;
import logic.LogicTratamientos;

/**
 * Servlet implementation class SvBorrarTratamiento
 */
@WebServlet("/svborrartratamiento")
public class SvBorrarTratamiento extends HttpServlet {
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
			Tratamiento t = new Tratamiento();
			LogicTratamientos lt = new LogicTratamientos();

			String idString = request.getParameter("tratamiento");

			if (idString != "") {

				try {
					int id = Integer.parseInt(idString);
					t.setIdTratamiento(id);

					lt.borrarTratamiento(t);
					response.sendRedirect("exitoBorrado.html");
				}

				catch (Exception e) {
					response.sendRedirect("errorBorrado.html");
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
