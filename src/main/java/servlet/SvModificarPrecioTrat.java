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
 * Servlet implementation class SvModificarPrecioTrat
 */
@WebServlet("/svmodificarpreciotrat")
public class SvModificarPrecioTrat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvModificarPrecioTrat() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			Tratamiento t = new Tratamiento();
			LogicTratamientos lt = new LogicTratamientos();

			String idString = request.getParameter("tratamiento");
			String precioString = request.getParameter("precio");

			if (idString != "" && precioString != "") {

				try {
					int id = Integer.parseInt(idString);
					int precio = Integer.parseInt(precioString);

					t.setIdTratamiento(id);
					t.setCosto(precio);

					lt.modificarPrecioTrat(t);

					response.sendRedirect("exitoReserva.html");
				} catch (Exception e) {
					response.sendRedirect("error.html");
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
