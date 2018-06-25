package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
