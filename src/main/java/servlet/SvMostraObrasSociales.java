package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.ObraSocial;
import logic.LogicObraSocial;

/**
 * Servlet implementation class SvMostraObrasSociales
 */
@WebServlet("/svmostraobrassociales")
public class SvMostraObrasSociales extends HttpServlet {
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
			LogicObraSocial lo = new LogicObraSocial();

			try {
				LinkedList<ObraSocial> obrassociales = lo.mostrarObrasSociales();
				request.setAttribute("tablaOS", obrassociales);
				request.getRequestDispatcher("mostrarListaOS.jsp").forward(request, response);
			} catch (Exception e) {
				response.sendRedirect("mostrarRespuesta.jsp?mensaje=Hubo un error");
			}
		}

		else {
			response.sendRedirect("errorSesion.html");
		}

	}

}
