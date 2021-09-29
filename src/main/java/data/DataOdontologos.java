package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import entities.Odontologo;

public class DataOdontologos {
	
	//CREO Y CARGO LA LISTA DE DOCTORES
	public LinkedList<Odontologo> mostrarDoctores()
	{
		Connection con = null;
		ResultSet rs = null;
		
		LinkedList<Odontologo> doctores = new LinkedList<Odontologo>();
		
		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement("SELECT matricula,onombre,oapellido FROM odontologo");
			rs = ps.executeQuery();

			while (rs.next()) {

				Odontologo o = new Odontologo();
				
				o.setMatricula(rs.getInt(1));
				o.setNombre(rs.getString(2));
				o.setApellido(rs.getString(3));

				doctores.add(o);
			}
		
		} 
		
		catch (Exception p) 
		{
			System.err.println("Hubo un error en la conexion");
		}
		
		return doctores;
	}
	
	//AGREGAR DOCTOR
	public void agregarDoctor(Odontologo o) throws Exception
	{
		Connection con = null;
		
		int matricula = o.getMatricula();
		String apellido = o.getApellido();
		String nombre = o.getNombre();
		
		try {
			
			con = DbConnector.getConexion();
			PreparedStatement ps =con.prepareStatement("INSERT INTO odontologo(matricula,oapellido,onombre) " + "VALUES(?,?,?)");
			
			ps.setInt (1, matricula);
			ps.setString (2, apellido);
			ps.setString (3, nombre);
			
			ps.executeUpdate();
			con.close();
			
		}
		
		catch (Exception e) 
		{
			System.err.println("Hubo un error en la conexion");
			throw e;
		}
	}
	
	//BORRAR ODONTOLOGO
	public void BorrarDoctor(Odontologo o) throws Exception
	{
		Connection con = null;
		int matricula = o.getMatricula();
		
		try 
		{
			con = DbConnector.getConexion();
			PreparedStatement ps=con.prepareStatement("DELETE FROM odontologo WHERE matricula=?");
			ps.setInt(1, matricula);
			ps.execute();
			con.close();	
		}	
		
		catch (Exception e) 
		{
			System.err.println("Hubo un error en la conexion");
			throw e;
		}
	}
}
