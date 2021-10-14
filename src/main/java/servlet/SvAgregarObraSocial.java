package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.ObraSocial;
import logic.LogicObraSocial;

/**
 * Servlet implementation class SvAgregarObraSocial
 */
@WebServlet("/svagregarobrasocial")
public class SvAgregarObraSocial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SvAgregarObraSocial() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			ObraSocial o = new ObraSocial();
			LogicObraSocial lo = new LogicObraSocial();

			String nombre = request.getParameter("nombre");

			if (nombre != "") {

				try {
					o.setNombre(nombre);
					lo.agregarObraSocial(o);
					response.sendRedirect("mostrarRespuesta.jsp?mensaje=Obra social registrada"); 
				} catch (Exception e) {
					response.sendRedirect("mostrarRespuesta.jsp?mensaje=Error en el registro"); 
				}

			} else {
				response.sendRedirect("mostrarRespuesta.jsp?mensaje=Error en los datos ingresados"); 
			}
		}

		else {
			response.sendRedirect("errorSesion.html");
		}

	}

}
