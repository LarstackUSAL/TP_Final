package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.dto.MateriasPrimas;
import model.dto.MateriasPrimasCantidad;
import model.dto.OrdenesTrabajos;
import model.dto.Productos;
import utils.DbConnection;

public class MateriasPrimasDao {

	public ArrayList<MateriasPrimas> findAll() {
		
		ArrayList<MateriasPrimas> materiasPrimas = new ArrayList<>();

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = "SELECT "
				+ "id, " +
				"codigo, " +
				"descripcion " +
				"FROM materia_prima";

		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
			
				MateriasPrimas mp = new MateriasPrimas();

				mp.setCodigo(rs.getString("codigo"));
				mp.setDescripcion(rs.getString("descripcion"));
				mp.setId(rs.getInt("id"));
				
				materiasPrimas.add(mp);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}finally {
			
			DbConnection.cerrarConexion(rs, stmt, conn);
		}
		
		return materiasPrimas;
	}
	
	public MateriasPrimasCantidad findById(int id) {
		
		MateriasPrimasCantidad materiasPrimasCantidad = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = "SELECT "
				+ "id, " +
				"codigo, " +
				"descripcion,"
				+ "cantidad " +
				"FROM materia_prima WHERE id = ? ";

		try {
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			rs.next();
		
			MateriasPrimas materiaPrima = new MateriasPrimas();

			materiaPrima.setCodigo(rs.getString("codigo"));
			materiaPrima.setDescripcion(rs.getString("descripcion"));
			materiaPrima.setId(rs.getInt("id"));
			
			materiasPrimasCantidad.setCantidad(rs.getInt("cantidad"));
			materiasPrimasCantidad.setMateriaPrima(materiaPrima);

		} catch (SQLException e) {

			e.printStackTrace();

		}finally {
			
			DbConnection.cerrarConexion(rs, stmt, conn);
		}
		
		return materiasPrimasCantidad;
	}
	
	public MateriasPrimas loadMateriaPrima(String codigoMateriaPrima) {

		MateriasPrimas materiaPrima = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT " +
				"codigo, " +
				"descripcion " +
				"FROM materia_prima "
				+ "WHERE codigo = '" + codigoMateriaPrima + "'";

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs != null && rs.next()) {

				materiaPrima = new MateriasPrimas();

				materiaPrima.setCodigo(rs.getString("codigo"));
				materiaPrima.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}finally {

			DbConnection.cerrarConexion(rs, stmt, conn);
		}

		return materiaPrima;
	}

	public int consultaStock(String codigoMateriaPrima) {

		Integer stock = 0;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		CallableStatement callStmt = null;
				
		try {
			
			callStmt = conn.prepareCall("{call sp_consulta_stock(?,?)}");

			callStmt.setString(1, codigoMateriaPrima);
			callStmt.registerOutParameter(2, Types.INTEGER);  

			callStmt.execute();
			
			stock = callStmt.getInt(2);

		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {

			DbConnection.cerrarConexion(rs, callStmt, conn);
		}

		return stock != null ? stock : 0;
	}

	public void actualizarStock(Integer materiaPrimaId, Integer cantidad) {

		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;

		try {

			int cantidadActual = this.findById(materiaPrimaId).getCantidad();
			int cantidadActualizada = cantidadActual + cantidad;
			
			String insertOC = "update materia_prima set cantidad = ? WHERE id=?";

			stmt = conn.prepareStatement(insertOC);

			stmt.setInt(1, cantidadActualizada);
			stmt.setInt(2, materiaPrimaId);
			
			stmt.executeUpdate();

		} catch (Exception e) {


		}finally {

			DbConnection.cerrarConexion(null, stmt, conn);
		}		
	}

}
