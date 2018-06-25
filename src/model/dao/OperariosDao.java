package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.Operarios;
import utils.DbConnection;

public class OperariosDao {

	public ArrayList<Operarios> loadOperarios() {
		
		ArrayList<Operarios> operariosList = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		
		String sql = "SELECT "
				+ "id, " +
				"nombre, " +
				"apellido, " +
				"legajo " +
				"FROM operario ";

		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs != null) {
				
				operariosList = new ArrayList<>();
				
				while(rs.next()) {

					Operarios operario = new Operarios();
					
					operario.setId(rs.getInt("id"));
					operario.setNombre(rs.getString("nombre"));
					operario.setApellido(rs.getString("apellido"));
					operario.setLegajo(rs.getString("legajo"));
					
					operariosList.add(operario);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}finally {
			
			DbConnection.cerrarConexion(rs, stmt, conn);
		}
		
		return operariosList;
	}

	public Operarios loadOperarioById(int id) {

		Operarios operario = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		
		String sql = "SELECT "
				+ "id, " +
				"nombre, " +
				"apellido, " +
				"legajo " +
				"FROM operario "
				+ "WHERE id = " + id;

		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs != null && rs.next()) {
				
				operario = new Operarios();
					
				operario.setId(rs.getInt("id"));
				operario.setNombre(rs.getString("nombre"));
				operario.setApellido(rs.getString("apellido"));
				operario.setLegajo(rs.getString("legajo"));

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}finally {
			
			DbConnection.cerrarConexion(rs, stmt, conn);
		}
		
		return operario;
	}

}
