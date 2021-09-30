package logic;

import java.util.LinkedList;

import data.DataObraSocial;
import entities.ObraSocial;

public class LogicObraSocial {

	DataObraSocial dob = new DataObraSocial();

	// MOSTRAR OBRAS SOCIALES
	public LinkedList<ObraSocial> mostrarObrasSociales() {
		return dob.mostrarObrasSociales();
	}

	// AGREGAR OBRA SOCIAL
	public void agregarObraSocial(ObraSocial o) throws Exception {
		dob.agregarObraSocial(o);
	}

	// BORRAR OBRA SOCIAL
	public void borrarObraSocial(ObraSocial o) throws Exception {
		dob.borrarObraSocial(o);
	}

}
