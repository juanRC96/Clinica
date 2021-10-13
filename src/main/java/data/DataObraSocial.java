package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.ObraSocial;

public class DataObraSocial {

	// MOSTRAR LISTADO CON LAS OBRAS SOCIALES
	public LinkedList<ObraSocial> mostrarObrasSociales() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		LinkedList<ObraSocial> obrassociales = new LinkedList<ObraSocial>();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT idObraSocial,osnombre FROM obra_social");
			rs = ps.executeQuery();

			while (rs.next()) {

				ObraSocial o = new ObraSocial();
				o.setId(rs.getInt(1));
				o.setNombre(rs.getString(2));

				obrassociales.add(o);
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return obrassociales;
	}

	// AGREGAR OBRA SOCIAL
	public void agregarObraSocial(ObraSocial o) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		String nombre = o.getNombre();

		try {

			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("INSERT INTO obra_social(osnombre) VALUES(?)");

			ps.setString(1, nombre);

			ps.executeUpdate();
			con.close();

		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// BORRAR OBRA SOCIAL
	public void borrarObraSocial(ObraSocial o) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		int id = o.getId();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("DELETE FROM obra_social WHERE idObraSocial=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			con.close();
		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
