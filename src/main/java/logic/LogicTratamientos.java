package logic;

import java.util.LinkedList;

import data.DataTratamiento;
import entities.Tratamiento;

public class LogicTratamientos {
	
	DataTratamiento dt = new DataTratamiento();

	//MOSTRAR LISTA CON TODOS LOS TRATAMIENTOS
	public LinkedList<Tratamiento> MostrarTratamientos() throws Exception
	{
		return dt.MostratTratamientos();
	}
	
	//AGREGAR TRATAMIENTOS
	public void agregarTratamiento(Tratamiento t) throws Exception
	{
		dt.agregarTratamiento(t);
	}
	
	//BORRAR TRATAMIENTO
	public void borrarTratamiento(Tratamiento t) throws Exception
	{
		dt.borrarTratamiento(t);
	}
	
	//MODIFICAR PRECIO DE UN TRATAMIENTO
	public void modificarPrecioTrat(Tratamiento t) throws Exception
	{
		dt.modificarPrecioTrat(t);
	}
}
