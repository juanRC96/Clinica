package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

	int idTurno;
	LocalDate fecha;
	LocalTime hora;
	Paciente paciente;
	Odontologo odontologo;
	
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Odontologo getOdontologo() {
		return odontologo;
	}
	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", fecha=" + fecha + ", hora=" + hora + ", paciente=" + paciente
				+ ", odontologo=" + odontologo + "]";
	}

	
	
}
