package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Productos;
import utils.DbConnection;

public class ProductosDao {

	public Productos loadProducto(String codigo) {

		Productos producto = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = "SELECT " +
				"codigo, " +
				"descripcion " +
				"FROM producto " +
				"WHERE codigo = ? ";

		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, codigo);
			
			rs = stmt.executeQuery();

			if(rs != null && rs.next()) {

					producto = new Productos();

					producto.setCodigo(rs.getString("codigo"));
					producto.setDescripcion(rs.getString("descripcion"));

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}finally {
			
			DbConnection.cerrarConexion(rs, stmt, conn);
		}
		
		return producto;
	}

	public ArrayList<Productos> loadProductos() {

		ArrayList<Productos> productos = new ArrayList<>();

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = "SELECT " +
				"codigo, " +
				"descripcion " +
				"FROM producto ";

		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
			
				Productos producto = new Productos();

				producto.setCodigo(rs.getString("codigo"));
				producto.setDescripcion(rs.getString("descripcion"));
				
				productos.add(producto);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}finally {
			
			DbConnection.cerrarConexion(rs, stmt, conn);
		}
		
		return productos;
	}
}
