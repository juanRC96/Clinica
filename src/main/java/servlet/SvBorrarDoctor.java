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
 * Servlet implementation class SvBorrarDoctor
 */
@WebServlet("/svborrardoctor")
public class SvBorrarDoctor extends HttpServlet {
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

			if (matriculaString != "") {

				try {
					int matricula = Integer.parseInt(matriculaString);
					o.setMatricula(matricula);

					lo.borrarDoctor(o);
					response.sendRedirect("mostrarRespuesta.jsp?mensaje=Odontologo borrado"); 
				} catch (Exception e) {
					response.sendRedirect("mostrarRespuesta.jsp?mensaje=Error en el borrado"); 
				}
			} else {
				response.sendRedirect("mostrarRespuesta.jsp?mensaje=Error en los datos ingresados"); 
			}

		} else {
			response.sendRedirect("errorSesion.html");
		}

	}

}
