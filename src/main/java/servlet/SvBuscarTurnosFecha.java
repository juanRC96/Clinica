package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Turno;
import logic.LogicTurnos;

/**
 * Servlet implementation class SvBuscarTurnosFecha
 */
@WebServlet("/svbuscarturnosfecha")
public class SvBuscarTurnosFecha extends HttpServlet {
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
			Turno t = new Turno();
			LogicTurnos lt = new LogicTurnos();

			String fechaString = request.getParameter("fecha");

			if (fechaString != "") {

				try {
					LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

					t.setFecha(fecha);

					LinkedList<Turno> turnos = lt.buscarTurnoFecha(t);
					request.setAttribute("tablaTurnos", turnos);
					request.getRequestDispatcher("mostrarListaTurnos.jsp").forward(request, response);

				} catch (Exception e) {
					response.sendRedirect("respuestaPrivado.jsp?mensaje=Hubo un error"); 
				}
			}

			else {
				response.sendRedirect("respuestaPrivado.jsp?mensaje=Error en los datos ingresados"); 
			}
		} else {
			response.sendRedirect("respuestaPublico.jsp?mensaje=Sesion no iniciada"); 
		}

	}

}
