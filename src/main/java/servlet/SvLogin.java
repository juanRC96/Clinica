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
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TRAIGO LOS DATOS DEL HTML Y LOS ASIGNO A VARIABLES
		String user = request.getParameter("usuario");
		String pass = request.getParameter("clave");
		
		//CREO INSTANCIAS DE USUARIO Y DATAUSUARIO
		Usuario us = new Usuario();
		Login lg = new Login();
		
		//ASIGNO A LA INSTANCIA USUARIO LOS VALORES DE LAS VARIABLES
		us.setUsuario(user);
		us.setContraseña(pass);
		
		//ASIGNO A OTRA INSTANCIA DE USUARIO EL VALOR DEL METODO INICIAR SESION QUE ME VA A DEVOLVER EL USUARIO COINCIDENTE CON LOS DATOS INGRESADOS
		Usuario usuario = lg.iniciarSesion(us);

		//SI ME DEVUELVE USUARIO=NULL ES PORQUE NO HAY USUARIOS Y CONTRA COINCIDENTES CON LOS INGRESADOS
			if (usuario == null) 
			{
				request.setAttribute("mensaje", "Error nombre de usuario y/o clave");
				response.sendRedirect("errorLogin.html");
			} 
			else 
			{
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("menuGeneral.html");
			}
		
	}

}
