package logic;

import java.util.LinkedList;

import data.DataAtencion;
import entities.Atencion;

public class LogicAtenciones {

	DataAtencion da = new DataAtencion();

	// MOSTRAR LISTA ATENCIONES
	public LinkedList<Atencion> mostrarAtenciones() {
		return da.mostrarAtenciones();
	}

	// AGREGAR ATENCIONES
	public void agregarAtencion(Atencion a) throws Exception {
		try {
			da.agregarAtencion(a);
		} catch (Exception e) {
			throw e;
		}
	}

	// BORRAR ATENCION
	public void borrarAtencion(Atencion a) throws Exception {
		try {
			da.borrarAtencion(a);
		} catch (Exception e) {
			throw e;
		}
	}

}
