package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Tratamiento;

public class DataTratamiento {

	// MOSTRAR LISTA CON TODOS LOS TRATAMIENTOS
	public LinkedList<Tratamiento> MostratTratamientos() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		LinkedList<Tratamiento> tratamientos = new LinkedList<Tratamiento>();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT idTratamiento,descripcion,costo FROM tratamiento");
			rs = ps.executeQuery();

			while (rs.next()) {
				Tratamiento t = new Tratamiento();
				t.setIdTratamiento(rs.getInt(1));
				t.setDescripcion(rs.getString(2));
				t.setCosto(rs.getInt(3));

				tratamientos.add(t);
			}

		} catch (Exception e) {
			System.err.println("Hubo un error");
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
		return tratamientos;
	}

	// AGREGAR UN TRATAMIENTO
	public void agregarTratamiento(Tratamiento t) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		String descripcion = t.getDescripcion();
		int precio = t.getCosto();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("INSERT INTO tratamiento(descripcion,costo) VALUES(?,?)");
			ps.setString(1, descripcion);
			ps.setInt(2, precio);

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.err.println("Hubo un error");
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

	// BORRAR TRATAMIENTO
	public void borrarTratamiento(Tratamiento t) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		int id = t.getIdTratamiento();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("DELETE FROM tratamiento WHERE idTratamiento=?");
			ps.setInt(1, id);

			ps.executeUpdate();
			con.close();
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
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

	// MODIFICAR PRECIO DE TRATAMIENTO
	public void modificarPrecioTrat(Tratamiento t) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		int id = t.getIdTratamiento();
		int precio = t.getCosto();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("UPDATE tratamiento SET costo = ? WHERE idTratamiento = ?");

			ps.setInt(1, precio);
			ps.setInt(2, id);

			ps.executeUpdate();

		} catch (Exception e) {
			System.err.println("Hubo un error");
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
