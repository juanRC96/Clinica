package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Atencion;
import logic.LogicAtenciones;

/**
 * Servlet implementation class SvBorrarAtencion
 */
@WebServlet("/svborraratencion")
public class SvBorrarAtencion extends HttpServlet {
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
			Atencion a = new Atencion();
			LogicAtenciones la = new LogicAtenciones();

			String idString = request.getParameter("id");

			if (idString != "") {

				try {
					int id = Integer.parseInt(idString);
					a.setIdAtencion(id);

					la.borrarAtencion(a);
					response.sendRedirect("mostrarRespuesta.jsp?mensaje=Atencion borrado"); 
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
