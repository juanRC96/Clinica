package entities;

public class ObraSocial {

	int id;
	String nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Obra_Social [id=" + id + ", nombre=" + nombre + "]";
	}

}
