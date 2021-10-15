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
 * Servlet implementation class SvAgregarTratamiento
 */
@WebServlet("/svagregartratamiento")
public class SvAgregarTratamiento extends HttpServlet {
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

			String descripcion = request.getParameter("descripcion");
			String precioString = request.getParameter("precio");

			if (descripcion != "" && precioString != "") {

				try {
					int precio = Integer.parseInt(precioString);

					t.setDescripcion(descripcion);
					t.setCosto(precio);

					lt.agregarTratamiento(t);

					response.sendRedirect("respuestaPrivado.jsp?mensaje=Tratamiento registrado"); 
				} catch (Exception e) {
					response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en el registro"); 
				}

			} else {
				response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en los datos ingresados"); 
			}

		} else {
			response.sendRedirect("respuestaPublico.jsp?mensaje=Sesion no iniciada"); 
		}

	}

}
