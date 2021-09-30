package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import entities.ObraSocial;

public class DataObraSocial {

	// MOSTRAR LISTADO CON LAS OBRAS SOCIALES
	public LinkedList<ObraSocial> mostrarObrasSociales() {
		Connection con = null;
		ResultSet rs = null;

		LinkedList<ObraSocial> obrassociales = new LinkedList<ObraSocial>();

		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement("SELECT idObraSocial,osnombre FROM obra_social");
			rs = ps.executeQuery();

			while (rs.next()) {

				ObraSocial o = new ObraSocial();
				o.setId(rs.getInt(1));
				o.setNombre(rs.getString(2));

				obrassociales.add(o);
			}

		}

		catch (Exception p) {
			System.err.println("Hubo un error en la conexion");
		}

		return obrassociales;
	}

	// AGREGAR OBRA SOCIAL
	public void agregarObraSocial(ObraSocial o) throws Exception {
		Connection con = null;

		String nombre = o.getNombre();

		try {

			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement("INSERT INTO obra_social(osnombre) " + "VALUES(?)");

			ps.setString(1, nombre);

			ps.executeUpdate();
			con.close();

		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}
	}

	// BORRAR OBRA SOCIAL
	public void borrarObraSocial(ObraSocial o) throws Exception {
		Connection con = null;
		int id = o.getId();

		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement("DELETE FROM obra_social WHERE idObraSocial=?");
			ps.setInt(1, id);
			ps.execute();
			con.close();
		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}
	}

}
