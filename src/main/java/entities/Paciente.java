package entities;

import java.time.LocalDate;

public class Paciente {

	int dni;
	String nombre;
	String apellido;
	LocalDate fecha_nac;
	String direccion;
	String telefono;
	ObraSocial os;
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(LocalDate fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public ObraSocial getOs() {
		return os;
	}
	public void setOs(ObraSocial os) {
		this.os = os;
	}
	
	@Override
	public String toString() {
		return "Paciente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha_nac=" + fecha_nac
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", os=" + os + "]";
	}
	
}
