package entities;

public class Odontologo {

	private int matricula;
	private String nombre;
	private String apellido;

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
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

	@Override
	public String toString() {
		return "Odontologo [matricula=" + matricula + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

}
