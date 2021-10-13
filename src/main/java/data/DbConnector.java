package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

	private static String dburl = "jdbc:mysql://localhost:3306/clinica";
	private static String dbuser = "root";
	private static String dbpass = "1234";
	private static String dbdriver = "com.mysql.cj.jdbc.Driver";
	private static DbConnector instancia;
	private Connection con = null;

	// CARGAR DRIVER
	private DbConnector() {
		try {
			Class.forName(dbdriver);
			System.out.println("Controlador cargado");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}

	// CREAR NUEVA INSTANCIA DE LA CLASE DBCONNECTOR
	public static DbConnector getInstancia() {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		return instancia;
	}

	// CONECTAR
	public Connection getConexion() {
		try {
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection(dburl, dbuser, dbpass);
				System.out.println("Conexión abierta");
			}
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		return con;
	}

	// DESCONECTAR
	public void desconectar() {
		try {
			con.close();
			System.out.println("Conexión cerrada");
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión");
			e.printStackTrace();
		}
	}

}