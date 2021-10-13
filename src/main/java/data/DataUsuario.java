package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Usuario;

public class DataUsuario {

	public Usuario iniciarSesion(Usuario us) throws Exception {
		Usuario usuario = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String username = us.getUsuario();
		String password = us.getContraseña();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT usuario,clave FROM usuarios WHERE usuario = ? AND clave = ?");

			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();

			while (rs.next()) {
				usuario = new Usuario();
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setContraseña(rs.getString("clave"));
			}

		} catch (Exception e) {
			throw e;
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
			} catch (Exception e) {
				throw e;
			}
		}

		return usuario;
	}

}
