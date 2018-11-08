package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.DbConnection;

public class OrdenesCompraMateriasPrimasDao {

	public boolean insertarOrdenCompra(Integer materiaPrimaId, Integer cantidad, boolean exterior, String deposito1, String deposito2, String deposito3) {
		
		boolean persistenciaOk = false;

		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;

		try {

			String insertOC = "insert into orden_compra_materia_prima( " +
					"materia_prima_id, " +
					"cantidad, " +
					"es_exterior, " +
					"deposito_1, " +
					"deposito_2, " +
					"deposito_3, " +
					") " +
					"values(?,?,?,?,?,?)";

			stmt = conn.prepareStatement(insertOC);

			stmt.setInt(1, materiaPrimaId);
			stmt.setInt(2, cantidad);
			stmt.setBoolean(3, exterior);
			stmt.setString(4, deposito1);
			stmt.setString(5, deposito2);
			stmt.setString(6, deposito3);
			
			persistenciaOk = stmt.executeUpdate() > 0 ? true : false;

		} catch (Exception e) {


		}finally {

			DbConnection.cerrarConexion(null, stmt, conn);
		}

		return persistenciaOk;
	}
}
