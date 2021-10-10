package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.ObraSocial;
import entities.Paciente;

public class DataPacientes {

	// LISTADO CON TODOS LOS PACIENTES
	public LinkedList<Paciente> mostrarPacientes() {
		Connection con = null;
		ResultSet rs = null;

		LinkedList<Paciente> pacientes = new LinkedList<Paciente>();

		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement(
					"SELECT dni,pnombre,papellido,fecha_nac,direccion,tel,osnombre FROM paciente pac\r\n"
							+ "LEFT JOIN obra_social os ON pac.obra_social=os.idObraSocial");
			rs = ps.executeQuery();

			while (rs.next()) {

				Paciente p = new Paciente();
				ObraSocial o = new ObraSocial();
				p.setDni(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setApellido(rs.getString(3));
				p.setFecha_nac(rs.getDate(4).toLocalDate());
				p.setDireccion(rs.getString(5));
				p.setTelefono(rs.getString(6));

				if (rs.getString(7) == null) {
					o.setNombre("-");
				}

				else {
					o.setNombre(rs.getString(7));
				}

				p.setOs(o);

				pacientes.add(p);
			}

		}

		catch (Exception p) {
			System.err.println("Hubo un error en la conexion");
		}

		return pacientes;
	}

	// VERIFICO QUE EL PACIENTE NO ESTE REGISTRADO
	public boolean verificarRegistro(Paciente p) {
		Connection con = null;
		ResultSet rs = null;
		boolean disponible = false;

		int dni = p.getDni();

		try {

			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement("SELECT dni FROM paciente");
			rs = ps.executeQuery();

			while (rs.next()) {

				if (dni == rs.getInt(1)) // FALTA COMPROBAR SI LA HORA ESTA DENTRO DE LAS DE TRABAJO
				{
					disponible = false;
				} else {
					disponible = true;
				}
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
		}
		return disponible;
	}

	// AGREGAR PACIENTE A LA LISTA
	public void agregarPaciente(Paciente p) throws Exception {
		Connection con = null;

		// ASIGNO A VARIABLES LOS DATOS TRAIDOS EN TURNO T
		int dni = p.getDni();
		String nombre = p.getNombre();
		String apellido = p.getApellido();
		LocalDate fecha_nac = p.getFecha_nac();
		String direccion = p.getDireccion();
		String tel = p.getTelefono();

		ObraSocial os = p.getOs();

		if (os != null) {
			int obrasocial = os.getId();

			try {

				con = DbConnector.getConexion();
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO paciente(dni,pnombre,papellido,fecha_nac,direccion,tel,obra_social) "
								+ "VALUES(?,?,?,?,?,?,?)");

				ps.setInt(1, dni);
				ps.setString(2, nombre);
				ps.setString(3, apellido);
				ps.setDate(4, java.sql.Date.valueOf(fecha_nac));
				ps.setString(5, direccion);
				ps.setString(6, tel);
				ps.setInt(7, obrasocial);

				ps.executeUpdate();
				con.close();

			}

			catch (Exception e) {
				System.err.println("Hubo un error en la conexion");
				throw e;
			}

		}
		// asigno paciente sin obra social
		else {
			try {

				con = DbConnector.getConexion();
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO paciente(dni,pnombre,papellido,fecha_nac,direccion,tel) " + "VALUES(?,?,?,?,?,?)");

				ps.setInt(1, dni);
				ps.setString(2, nombre);
				ps.setString(3, apellido);
				ps.setDate(4, java.sql.Date.valueOf(fecha_nac));
				ps.setString(5, direccion);
				ps.setString(6, tel);

				ps.executeUpdate();
				con.close();
			}

			catch (Exception e) {
				System.err.println("Hubo un error en la conexion");
				throw e;
			}
		}
	}

	// BORRAR PACIENTE
	public void borrarPaciente(Paciente p) throws Exception {
		Connection con = null;
		int dni = p.getDni();

		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement("DELETE FROM paciente WHERE dni=?");
			ps.setInt(1, dni);
			
			ps.executeUpdate();
			con.close();
		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}

	}

	// BUSCAR PACIENTES POR DNI
	public LinkedList<Paciente> buscarPacienteporDni(Paciente pac) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		int dni = pac.getDni();

		LinkedList<Paciente> pacientes = new LinkedList<Paciente>();

		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con
					.prepareStatement("SELECT dni,pnombre,papellido,fecha_nac,direccion,tel FROM paciente WHERE dni=?");
			ps.setInt(1, dni);
			rs = ps.executeQuery();

			while (rs.next()) {

				Paciente p = new Paciente();
				p.setDni(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setApellido(rs.getString(3));
				p.setFecha_nac(rs.getDate(4).toLocalDate());
				p.setDireccion(rs.getString(5));
				p.setTelefono(rs.getString(6));

				pacientes.add(p);
			}

		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}

		return pacientes;
	}

	// BUSCAR SOLO UN PACIENTE
	public Paciente buscarPacienteSolo(Paciente pac) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		int dni = pac.getDni();
		Paciente p = new Paciente();

		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con
					.prepareStatement("SELECT dni,pnombre,papellido,fecha_nac,direccion,tel FROM paciente WHERE dni=?");
			ps.setInt(1, dni);
			rs = ps.executeQuery();

			// SI BIEN TRAIGO UN SOLO REGISTRO(PODRIA PONER RS.NEXT();), USO EL WHILE PORQUE
			// DE NO HABER VALORES COINCIDENTES NO GENERA UNA SQL EXCEPTION
			while (rs.next()) {
				p.setNombre(rs.getString(2));
				p.setApellido(rs.getString(3));
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}

		return p;
	}

}
