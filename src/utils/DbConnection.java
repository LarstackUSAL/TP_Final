package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	
//	SQLServer

//	public static String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	public static String dbServer = "jdbc:sqlserver://localhost:1433;databaseName=db_tp_final;integratedSecurity=true;";

//	PostgreSQL	
	public static String dbDriver = "org.postgresql.Driver";
	public static String dbServer = "jdbc:postgresql://localhost:5432/db_tp_final?user=postgres&password=1234";


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
	
	public static void cerrarConexion(Connection conn) {
		
		if(conn != null)
			try {
				
				conn.close();
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	public static void cerrarConexion(ResultSet rs) {
		
		if(rs != null) {
			
			try {
			
				cerrarConexion(rs.getStatement());
				rs.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	
	public static void cerrarConexion(Statement stmt) {
		
		if(stmt != null) {
			
			try {
				
				cerrarConexion(stmt.getConnection());
				stmt.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public static void cerrarConexion(ResultSet rs, Statement stmt, Connection conn) {
		
		cerrarConexion(rs);
		cerrarConexion(stmt);
		cerrarConexion(conn);
	}
}
