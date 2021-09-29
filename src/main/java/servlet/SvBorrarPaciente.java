package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Paciente;
import logic.LogicPacientes;

/**
 * Servlet implementation class SvBorrarPaciente
 */
@WebServlet("/svborrarpaciente")
public class SvBorrarPaciente extends HttpServlet {
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
			Paciente p = new Paciente();
			LogicPacientes lp = new LogicPacientes();

			String dniString = request.getParameter("dni");

			if (dniString != "") {

				try {
					int dni = Integer.parseInt(dniString);
					p.setDni(dni);

					lp.borrarPaciente(p);
					response.sendRedirect("exitoBorrado.html");
				}

				catch (Exception e) {
					response.sendRedirect("errorBorrado.html");
				}
			} else {
				response.sendRedirect("errorDatosIngresados.html");
			}

		} else {
			response.sendRedirect("errorSesion.html");
		}

	}

}
