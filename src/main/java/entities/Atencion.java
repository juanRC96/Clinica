package entities;

import java.time.LocalDate;

public class Atencion {

	int idAtencion;
	String observaciones;
	LocalDate fecha;
	Tratamiento tratamiento;
	Paciente paciente;
	Odontologo odontologo;
	
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
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
	
	@Override
	public String toString() {
		return "Atencion [idAtencion=" + idAtencion + ", observaciones=" + observaciones + ", fecha=" + fecha
				+ ", tratamiento=" + tratamiento + ", paciente=" + paciente + ", odontologo=" + odontologo + "]";
	}

}
