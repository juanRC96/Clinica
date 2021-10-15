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

@WebServlet("/svborrarobrasocial")
public class SvBorrarObraSocial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SvBorrarObraSocial() {
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

			String idString = request.getParameter("idobrasocial");

			if (idString != "") {

				try {
					int id = Integer.parseInt(request.getParameter("idobrasocial"));
					o.setId(id);

					lo.borrarObraSocial(o);
					response.sendRedirect("respuestaPrivado.jsp?mensaje=Obra social borrada"); 
				} catch (Exception e) {
					response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en el borrado"); 
				}

			} else {
				response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en los datos ingresados"); 
			}

		} else {
			response.sendRedirect("respuestaPublico.jsp?mensaje=Sesion no iniciada"); 
		}

	}

}
