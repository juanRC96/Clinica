package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

	private static String dburl = "jdbc:mysql://localhost:3306/clinica";
	private static String dbuser = "root";
	private static String dbpass = "1234";
	private static String dbdriver = "com.mysql.cj.jdbc.Driver";

	// CARGO EL CONTROLADOR
	public static void cargarControlador() {
		try {
			Class.forName(dbdriver);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}

	// ESTABLEZCO LA CONEXION
	public static Connection getConexion() {
		Connection conexion = null;

		try {
			cargarControlador();
			conexion = DriverManager.getConnection(dburl, dbuser, dbpass);
			System.out.println("Conexión exitosa");

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	}
}