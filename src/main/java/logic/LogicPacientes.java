package logic;

import java.util.LinkedList;

import data.DataPacientes;
import entities.Paciente;

public class LogicPacientes {

	DataPacientes dp = new DataPacientes();

	// LISTA CON TODOS LOS PACIENTES
	public LinkedList<Paciente> mostrarPacientes() throws Exception {
		return dp.mostrarPacientes();
	}

	// VERIFICO SI YA ESTA REGISTRADO EL PACIENTE
	public boolean verificarRegistro(Paciente p) throws Exception {
		boolean disponible = dp.verificarRegistro(p);
		return disponible;
	}

	// AGREGAR PACIENTE
	public void agregarPaciente(Paciente p) throws Exception {
		boolean sePuedeRegistrar = this.verificarRegistro(p);

		if (sePuedeRegistrar == true) {
			dp.agregarPaciente(p);
		} else {
			Exception e = new Exception();
			throw e;
		}
	}

	// BORRAR PACIENTE
	public void borrarPaciente(Paciente p) throws Exception {
		LinkedList<Paciente> pacientes = this.buscarPaciente(p);

		// verifico si existe un paciente con el dni ingresado
		boolean vacio = pacientes.isEmpty();

		if (vacio == false) {
			dp.borrarPaciente(p);
		}

		else {
			Exception e = new Exception();
			throw e;
		}
	}

	// BUSCAR PACIENTES POR DNI
	public LinkedList<Paciente> buscarPaciente(Paciente p) throws Exception {
		return dp.buscarPacienteporDni(p);
	}

	public Paciente buscarPacienteSolo(Paciente p) throws Exception {
		return dp.buscarPacienteSolo(p);
	}

}
