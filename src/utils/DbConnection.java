package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	
//	SQLServer

	public static String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String dbServer = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=db_tp_final;integratedSecurity=true";

	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbServer);
		
		} catch (SQLException e) {

			System.out.println("SQL ERROR: " + e.getMessage());

		} catch (ClassNotFoundException e) {

			System.out.println("ERROR DE CONEXION A LA BASE DE DATOS.");
		}
		
		return conn;		
	}

	public static void cerrarConexion(ResultSet rs, Statement stmt, Connection conn) {
		
		if(rs != null)
			try {
			
				rs.close();
			
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		if(stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
	}
}
