package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

import entities.Odontologo;
import entities.Paciente;
import entities.Turno;

public class DataTurnos {

	// SOLICITO A LA BASE DE DATOS TODOS LOS TURNOS, Y LOS METO EN UNA LINKEDLIST
	public LinkedList<Turno> mostrarTurnos() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		LinkedList<Turno> turnos = new LinkedList<Turno>();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT oapellido,onombre,fecha,hora,dni,papellido,pnombre FROM turno tur"
					+ " INNER JOIN paciente pac ON pac.dni=tur.dniPaciente"
					+ " INNER JOIN odontologo odo ON odo.matricula=tur.matricula"
					+ " ORDER BY tur.fecha ASC");
			rs = ps.executeQuery();

			while (rs.next()) {

				Turno t = new Turno();
				Paciente p = new Paciente();
				Odontologo o = new Odontologo();

				o.setApellido(rs.getString(1));
				o.setNombre(rs.getString(2));
				t.setOdontologo(o);

				t.setFecha(rs.getDate(3).toLocalDate());
				t.setHora(rs.getTime(4).toLocalTime());

				p.setDni(rs.getInt(5));
				p.setApellido(rs.getString(6));
				p.setNombre(rs.getString(7));
				t.setPaciente(p);

				turnos.add(t);
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		// finalmente cierro las conexiones
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return turnos;
	}

	// VALIDAR DISPONIBILIDAD DEL TURNO
	public boolean validarDisponibilidad(Turno t) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean disponible = false;

		int matricula = t.getOdontologo().getMatricula();
		LocalDate fecha = t.getFecha();
		LocalTime hora = t.getHora();

		try {

			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT matricula,fecha,hora FROM turno WHERE matricula=? and fecha=? and hora=?");
			ps.setInt(1, matricula);
			ps.setDate(2, java.sql.Date.valueOf(fecha));
			ps.setTime(3, java.sql.Time.valueOf(hora));

			rs = ps.executeQuery();

			if (rs.next() == false) {
				disponible = true;
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return disponible;
	}

	// REGISTRO EL TURNO, UNA VEZ QUE ESTA DISPONIBLE
	public void registrarTurno(Turno t) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		int dni = t.getPaciente().getDni();
		int matricula = t.getOdontologo().getMatricula();
		LocalDate fecha = t.getFecha();
		LocalTime hora = t.getHora();

		try {

			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("INSERT INTO turno(dniPaciente,matricula,fecha,hora) VALUES(?,?,?,?)");

			ps.setInt(1, dni);
			ps.setInt(2, matricula);
			ps.setDate(3, java.sql.Date.valueOf(fecha));
			ps.setTime(4, java.sql.Time.valueOf(hora));

			ps.executeUpdate();
			con.close();
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// BORRAR TURNO
	public void borrarTurno(Turno t) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		int dni = t.getPaciente().getDni();
		LocalDate fecha = t.getFecha();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("DELETE FROM turno WHERE dniPaciente=? and fecha=?");
			ps.setInt(1, dni);
			ps.setDate(2, java.sql.Date.valueOf(fecha));

			ps.executeUpdate();
			con.close();
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		finally {
			try {
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// BUSCAR TURNO POR DNI DEL PACIENTE
	public LinkedList<Turno> buscarTurnoporDni(Turno tur) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;  

		int dni = tur.getPaciente().getDni();

		LinkedList<Turno> turnos = new LinkedList<Turno>();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT oapellido,onombre,fecha,hora,dni,papellido,pnombre FROM turno tur"
					+ " INNER JOIN paciente pac ON pac.dni=tur.dniPaciente"
					+ " INNER JOIN odontologo odo ON odo.matricula=tur.matricula WHERE pac.dni=?");

			ps.setInt(1, dni);

			rs = ps.executeQuery();

			while (rs.next()) {

				Turno t = new Turno();
				Paciente p = new Paciente();
				Odontologo o = new Odontologo();

				o.setApellido(rs.getString(1));
				o.setNombre(rs.getString(2));
				t.setOdontologo(o);

				t.setFecha(rs.getDate(3).toLocalDate());
				t.setHora(rs.getTime(4).toLocalTime());

				p.setDni(rs.getInt(5));
				p.setApellido(rs.getString(6));
				p.setNombre(rs.getString(7));
				t.setPaciente(p);

				turnos.add(t);
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return turnos;
	}

	// BUSCAR TURNOS POR MATRICULA DEL DOCTOR
	public LinkedList<Turno> buscarTurnosMatricula(Turno tur) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int matricula = tur.getOdontologo().getMatricula();

		LinkedList<Turno> turnos = new LinkedList<Turno>();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT oapellido,onombre,fecha,hora,dni,papellido,pnombre FROM turno tur"
					+ " INNER JOIN paciente pac ON pac.dni=tur.dniPaciente"
					+ " INNER JOIN odontologo odo ON odo.matricula=tur.matricula WHERE odo.matricula=?");

			ps.setInt(1, matricula);

			rs = ps.executeQuery();

			while (rs.next()) {

				Turno t = new Turno();
				Paciente p = new Paciente();
				Odontologo o = new Odontologo();

				o.setApellido(rs.getString(1));
				o.setNombre(rs.getString(2));
				t.setOdontologo(o);

				t.setFecha(rs.getDate(3).toLocalDate());
				t.setHora(rs.getTime(4).toLocalTime());

				p.setDni(rs.getInt(5));
				p.setApellido(rs.getString(6));
				p.setNombre(rs.getString(7));
				t.setPaciente(p);

				turnos.add(t);
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return turnos;
	}

	// BUSCAR TURNOS POR FECHA
	public LinkedList<Turno> buscarTurnoporFecha(Turno tur) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		LocalDate fecha = tur.getFecha();

		LinkedList<Turno> turnos = new LinkedList<Turno>();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT oapellido,onombre,fecha,hora,dni,papellido,pnombre FROM turno tur"
					+ " INNER JOIN paciente pac ON pac.dni=tur.dniPaciente"
					+ " INNER JOIN odontologo odo ON odo.matricula=tur.matricula WHERE tur.fecha=?");

			ps.setDate(1, java.sql.Date.valueOf(fecha));

			rs = ps.executeQuery();

			while (rs.next()) {

				Turno t = new Turno();
				Paciente p = new Paciente();
				Odontologo o = new Odontologo();

				o.setApellido(rs.getString(1));
				o.setNombre(rs.getString(2));
				t.setOdontologo(o);

				t.setFecha(rs.getDate(3).toLocalDate());
				t.setHora(rs.getTime(4).toLocalTime());

				p.setDni(rs.getInt(5));
				p.setApellido(rs.getString(6));
				p.setNombre(rs.getString(7));
				t.setPaciente(p);

				turnos.add(t);
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return turnos;
	}

	// BUSCO TURNO PARA MOSTRAR DATOS DEL TURNO REGISTRADO
	public Turno buscarDatosTurno(Turno t) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int dni = t.getPaciente().getDni();

		Turno tu = new Turno();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT oapellido,onombre,fecha,hora,dni,papellido,pnombre FROM turno tur"
					+ " INNER JOIN paciente pac ON pac.dni=tur.dniPaciente"
					+ " INNER JOIN odontologo odo ON odo.matricula=tur.matricula WHERE pac.dni=?");

			ps.setInt(1, dni);

			rs = ps.executeQuery();

			while (rs.next()) {

				Paciente pa = new Paciente();
				Odontologo od = new Odontologo();

				od.setApellido(rs.getString(1));
				od.setNombre(rs.getString(2));
				tu.setOdontologo(od);

				tu.setFecha(rs.getDate(3).toLocalDate());
				tu.setHora(rs.getTime(4).toLocalTime());

				pa.setDni(rs.getInt(5));
				pa.setApellido(rs.getString(6));
				pa.setNombre(rs.getString(7));
				tu.setPaciente(pa);

			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tu;
	}

	// VERIFICO SI EXISTE EL TURNO, PARA PODER BORRARLO
	public boolean existeTurno(Turno t) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean existe = false;

		int dni = t.getPaciente().getDni();
		LocalDate fecha = t.getFecha();

		try {
			con = DbConnector.getInstancia().getConexion();
			ps = con.prepareStatement("SELECT dniPaciente,fecha FROM turno WHERE dniPaciente=? AND fecha=?");
			ps.setInt(1, dni);
			ps.setDate(2, java.sql.Date.valueOf(fecha));
			rs = ps.executeQuery();

			while (rs.next()) {
				if (dni == (rs.getInt(1)) && fecha.equals(rs.getDate(2).toLocalDate())) {
					existe = true;
				} else {
					existe = false;
				}
			}
		}

		catch (Exception e) {
			System.err.println("Hubo un error");
			throw e;
		}

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DbConnector.getInstancia().desconectar();
				;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return existe;
	}
}
