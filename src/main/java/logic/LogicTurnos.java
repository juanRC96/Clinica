package logic;

import java.time.LocalDate;
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

	// VERIFICAR SI LA FECHA ES VALIDA(fecha de hoy menor a la ingresada)
	public boolean fechaValida(Turno t) {
		boolean fechaok = false;
		LocalDate hoy = LocalDate.now();
		if (hoy.isBefore(t.getFecha())) {
			fechaok = true;
		}
		return fechaok;
	}

	// RESERVO EL TURNO
	public boolean reservarTurno(Turno t) throws Exception {
		boolean reservado = false;
		boolean fechaValida = this.fechaValida(t);
		boolean disponible = this.estaDisponible(t);

		if (fechaValida == true && disponible == true) {
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
