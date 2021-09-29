package logic;

import java.util.LinkedList;

import data.DataTurnos;
import entities.Turno;

public class LogicTurnos {

	DataTurnos dt = new DataTurnos();

	// MOSTRAR TODOS LOS TURNOS
	public LinkedList<Turno> mostrarTurnos() throws Exception {
		return dt.mostrarTurnos();
	}

	// VALIDO DISPONIBILIDAD DEL TURNO
	public boolean estaDisponible(Turno t) throws Exception {
		boolean disponible = dt.validarDisponibilidad(t);
		return disponible;
	}

	// RESERVO EL TURNO
	public boolean reservarTurno(Turno t) throws Exception {
		boolean reservado = false;
		boolean disponible = this.estaDisponible(t);

		if (disponible == true) {
			dt.registrarTurno(t);
			reservado = true;
		} else {
			reservado = false;
		}

		return reservado;
	}

	// BORRAR TURNO
	public void borrarTurno(Turno t) throws Exception {
		boolean existe = this.existeTurno(t);

		if (existe == true) {
			dt.borrarTurno(t);
		} else {
			Exception e = new Exception();
			throw e;
		}
	}

	// BUSCAR TURNO POR DNI PACIENTE
	public LinkedList<Turno> buscarTurnoporDni(Turno t) throws Exception {
		return dt.buscarTurnoporDni(t);
	}

	// BUSCAR TURNO POR MATRICULA DOCTOR
	public LinkedList<Turno> buscarTurnoDoctor(Turno t) throws Exception {
		return dt.buscarTurnosMatricula(t);
	}

	// BUSCAR TURNOS POR FECHA
	public LinkedList<Turno> buscarTurnoFecha(Turno t) throws Exception {
		return dt.buscarTurnoporFecha(t);
	}

	// BUSCAR TURNO PARA MOSTRAR DATOS REGISTRADOS
	public Turno buscarDatosTurno(Turno t) throws Exception {
		return dt.buscarDatosTurno(t);
	}

	// VERIFICO SI EXISTE EL TURNO PARA SER BORRADO
	public boolean existeTurno(Turno t) throws Exception {
		return dt.existeTurno(t);
	}
}
