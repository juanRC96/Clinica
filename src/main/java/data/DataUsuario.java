package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Usuario;

public class DataUsuario {

	// METODO PARA VALIDAR INICIO SESION
	// BUSCO USUARIO EN BASE DE DATOS Y LO DEVUELVO AL SERVLET, SI NO HAY USUARIO,
	// NO COINCIDEN LOS DATOS INGRESADOS CON LOS DE LA BASE DE DATOS
	public Usuario iniciarSesion(Usuario us) {
		Usuario usuario = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String username = us.getUsuario();
		String password = us.getContraseña();

		try {
			con = DbConnector.getConexion();
			ps = con.prepareStatement("SELECT usuario,clave FROM usuarios WHERE usuario = ? AND clave = ?");

			// ASIGNO VALORES A LOS SIGNOS DE PREGUNTA
			ps.setString(1, username);
			ps.setString(2, password);

			// EJECUTO LA QUERY
			rs = ps.executeQuery();

			while (rs.next()) {
				usuario = new Usuario();
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setContraseña(rs.getString("clave"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// CIERRO LAS CONEXIONES
		finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return usuario;
	}

}
