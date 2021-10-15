package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Usuario;
import logic.Login;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/servletlogin")
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("usuario");
		String pass = request.getParameter("clave");

		Usuario us = new Usuario();
		Login lg = new Login();

		us.setUsuario(user);
		us.setContraseña(pass);

		try {
			Usuario usuario = lg.iniciarSesion(us);

			if (usuario == null) {
				response.sendRedirect("respuestaPublico.jsp?mensaje=Usuario o contraseña incorrecto"); 
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("menuGeneral.html");
			}
		} catch (Exception e) {
			response.sendRedirect("respuestaPublico.jsp?mensaje=Hubo un error"); 
		}
	}

}
