package logic;

import java.util.LinkedList;

import data.DataOdontologos;
import entities.Odontologo;

public class LogicOdontologos {

	DataOdontologos dod = new DataOdontologos();

	public LinkedList<Odontologo> mostrarOdontologos() {
		return dod.mostrarDoctores();
	}

	public void agregarDoctor(Odontologo o) throws Exception {
		dod.agregarDoctor(o);
	}

	public void borrarDoctor(Odontologo o) throws Exception {
		dod.BorrarDoctor(o);
	}

}
