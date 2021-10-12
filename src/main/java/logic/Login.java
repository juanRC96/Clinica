package logic;

import data.DataUsuario;
import entities.Usuario;

public class Login {

	DataUsuario du = new DataUsuario();

	public Usuario iniciarSesion(Usuario u) throws Exception {
		return du.iniciarSesion(u);
	}
}
