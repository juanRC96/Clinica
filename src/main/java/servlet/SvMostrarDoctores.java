package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Odontologo;
import logic.LogicOdontologos;



@WebServlet("/svmostrardoctores")
public class SvMostrarDoctores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);  
        if(session!=null)
        {  	
        	LogicOdontologos lo = new LogicOdontologos();
        
			
			try 
			{
				LinkedList<Odontologo> doctores = lo.mostrarOdontologos();
				request.setAttribute("tablaDoctores", doctores);
				request.getRequestDispatcher("mostrarListaDoctores.jsp").forward(request, response);
			} 
			catch (Exception e) 
			{
				response.sendRedirect("error.html");
			}
        }
        
        else
        {
        	response.sendRedirect("errorSesion.html");
        }

		
	}

}
