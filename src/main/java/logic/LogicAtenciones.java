package logic;

import java.util.LinkedList;

import data.DataAtencion;
import entities.Atencion;

public class LogicAtenciones {

	DataAtencion da = new DataAtencion();

	// MOSTRAR LISTA ATENCIONES
	public LinkedList<Atencion> mostrarAtenciones() throws Exception {
		return da.mostrarAtenciones();
	}

	// AGREGAR ATENCIONES
	public void agregarAtencion(Atencion a) throws Exception {
		da.agregarAtencion(a);
	}

	// BORRAR ATENCION
	public void borrarAtencion(Atencion a) throws Exception {
		da.borrarAtencion(a);
	}
}
