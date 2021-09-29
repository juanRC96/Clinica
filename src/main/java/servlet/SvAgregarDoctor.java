package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Odontologo;
import logic.LogicOdontologos;

/**
 * Servlet implementation class SvAgregarDoctor
 */
@WebServlet("/svagregardoctor")
public class SvAgregarDoctor extends HttpServlet {
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
			Odontologo o = new Odontologo();
			LogicOdontologos lo = new LogicOdontologos();

			String matriculaString = request.getParameter("matricula");
			String apellido = request.getParameter("apellido");
			String nombre = request.getParameter("nombre");

			if (matriculaString != "" && apellido != "" && nombre != "") {

				try {
					int matricula = Integer.parseInt(matriculaString);

					o.setMatricula(matricula);
					o.setApellido(apellido);
					o.setNombre(nombre);

					lo.agregarDoctor(o);

					response.sendRedirect("exitoReserva.html");
				}

				catch (Exception e) {
					response.sendRedirect("errorReserva.html");
				}
			} else {
				response.sendRedirect("errorDatosIngresados.html");
			}

		} else {
			response.sendRedirect("errorSesion.html");
		}
	}

}
