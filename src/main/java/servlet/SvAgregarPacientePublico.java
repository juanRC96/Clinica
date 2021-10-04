package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.ObraSocial;
import entities.Paciente;
import logic.LogicPacientes;

/**
 * Servlet implementation class SvAgregarPaciente
 */
@WebServlet("/svagregarpacientepublico")
public class SvAgregarPacientePublico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// CREO INSTANCIAS
		Paciente p = new Paciente();
		ObraSocial os = new ObraSocial();
		LogicPacientes lp = new LogicPacientes();

		String dniString = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fechaString = request.getParameter("fecha_nac");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		String obrasocialString = request.getParameter("obrasocial");

		if (dniString != "" && nombre != "" && apellido != "" && fechaString != "" && direccion != ""
				&& telefono != "") {

			try {
				int dni = Integer.parseInt(dniString);

				if (obrasocialString != "") {
					int obrasocial = Integer.parseInt(obrasocialString);
					os.setId(obrasocial);
					p.setOs(os);
				}

				LocalDate fecha_nac = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

				// LE ASIGNO A LA NUEVA INSTANCIA LOS VALORES DE LAS VARIABLES
				p.setDni(dni);
				p.setNombre(nombre);
				p.setApellido(apellido);
				p.setFecha_nac(fecha_nac);
				p.setDireccion(direccion);
				p.setTelefono(telefono);
				lp.agregarPaciente(p);
				response.sendRedirect("exitoReservaPublico.html");

			} catch (Exception e) {
				response.sendRedirect("errorReservaPublico.html");
			}

		}

		else {
			response.sendRedirect("errorDatosIngresados.html");
		}

	}
}
