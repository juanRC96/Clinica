package logic;

import data.DataUsuario;
import entities.Usuario;

public class Login {

	DataUsuario du = new DataUsuario();

	public Usuario iniciarSesion(Usuario u) {
		return du.iniciarSesion(u);
	}

}
