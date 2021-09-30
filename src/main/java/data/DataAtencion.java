package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Atencion;
import entities.Odontologo;
import entities.Paciente;
import entities.Tratamiento;

public class DataAtencion {

	// MOSTRAR ATENCIONES
	public LinkedList<Atencion> mostrarAtenciones() {
		Connection con = null;
		ResultSet rs = null;

		LinkedList<Atencion> atenciones = new LinkedList<Atencion>();

		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement(
					"SELECT idAtencion,fecha,descripcion,observaciones,costo,oapellido,onombre,pac.dni,papellido,pnombre FROM atencion ate\r\n"
							+ "INNER JOIN tratamiento tra ON tra.idTratamiento=ate.tratamiento\r\n"
							+ "INNER JOIN paciente pac ON pac.dni=ate.dni\r\n"
							+ "INNER JOIN odontologo odo ON odo.matricula=ate.doctor\r\n" + "ORDER BY fecha DESC");

			rs = ps.executeQuery();

			while (rs.next()) {
				Atencion a = new Atencion();
				Tratamiento t = new Tratamiento();
				Paciente p = new Paciente();
				Odontologo o = new Odontologo();

				a.setIdAtencion(rs.getInt(1));
				a.setFecha(rs.getDate(2).toLocalDate());
				t.setDescripcion(rs.getString(3));

				if (rs.getString(4) == null) {
					a.setObservaciones("-");
				}

				else {
					a.setObservaciones(rs.getString(4));
				}

				t.setCosto(rs.getInt(5));
				o.setApellido(rs.getString(6));
				o.setNombre(rs.getString(7));
				p.setDni(rs.getInt(8));
				p.setApellido(rs.getString(9));
				p.setNombre(rs.getString(10));

				a.setTratamiento(t);
				a.setOdontologo(o);
				a.setPaciente(p);

				atenciones.add(a);
			}

		}

		catch (Exception p) {
			System.err.println("Hubo un error en la conexion");
		}

		return atenciones;

	}

	// AGREGAR ATENCION
	public void agregarAtencion(Atencion a) throws Exception {
		Connection con = null;

		int dni = a.getPaciente().getDni();
		int matricula = a.getOdontologo().getMatricula();
		int tratamiento = a.getTratamiento().getIdTratamiento();
		String observaciones = a.getObservaciones();
		LocalDate fecha = a.getFecha();

		try {

			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO atencion(dni,doctor,tratamiento,observaciones,fecha) " + "VALUES(?,?,?,?,?)");

			ps.setInt(1, dni);
			ps.setInt(2, matricula);
			ps.setInt(3, tratamiento);
			ps.setString(4, observaciones);
			ps.setDate(5, java.sql.Date.valueOf(fecha));

			ps.executeUpdate();
			con.close();

		}

		catch (Exception x) {
			System.err.println("Hubo un error en la conexion");
			throw x;
		}

	}

	// BORRAR ATENCION
	public void borrarAtencion(Atencion a) throws Exception {
		Connection con = null;
		int id = a.getIdAtencion();

		try {
			con = DbConnector.getConexion();
			PreparedStatement ps = con.prepareStatement("DELETE FROM atencion WHERE idAtencion=?");
			ps.setInt(1, id);
			ps.execute();
			con.close();
		}

		catch (Exception e) {
			System.err.println("Hubo un error en la conexion");
			throw e;
		}

	}

}
