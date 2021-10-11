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
 * Servlet implementation class SvBuscarPaciente
 */
@WebServlet("/svbuscarpacientesolo")
public class SvBuscarPacienteSolo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nivelAcceso = request.getParameter("acceso");
		HttpSession session = request.getSession(false);

		if ((session != null) || (nivelAcceso.equals("publico"))) {
			Paciente p = new Paciente();
			LogicPacientes lp = new LogicPacientes();

			String dniString = request.getParameter("dni");
			String rol = request.getParameter("rol");

			if (dniString != "") {

				try {
					int dni = Integer.parseInt(dniString);
					p.setDni(dni);

					Paciente pac = lp.buscarPacienteSolo(p);

					if (nivelAcceso.equals("publico")) {
						request.setAttribute("datosPaciente", pac);
						request.getRequestDispatcher("reservarTurnosPublico.jsp").forward(request, response);
					} else {

						switch (rol) {
						case "reservarturnos":
							request.setAttribute("datosPaciente", pac);
							request.getRequestDispatcher("reservarTurnos.jsp").forward(request, response);

						case "agregaratencion":
							request.setAttribute("datosPaciente", pac);
							request.getRequestDispatcher("agregarAtencion.jsp").forward(request, response);
						}
					}

				} catch (Exception e) {
					if (nivelAcceso.equals("publico")) {
						response.sendRedirect("errorPublico.html");
					} else {
						response.sendRedirect("error.html");
					}
				}
			} else {
				if (nivelAcceso.equals("publico")) {
					response.sendRedirect("errorDatosIngresadosPublico.html");
				} else {
					response.sendRedirect("errorDatosIngresados.html");
				}
			}
		} else {
			response.sendRedirect("errorSesion.html");
		}
	}

}
