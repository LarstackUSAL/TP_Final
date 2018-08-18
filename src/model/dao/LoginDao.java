package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.Operarios;
import utils.DbConnection;

public class LoginDao {

	public String getRolUsuario(String usuario, String password) {
		
		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "select r.codigo "
				+ " from sistema_seguridad_usuario u "
				+ "		inner join sistema_seguridad_usuario_rol ur on u.id = ur.usuario_id "
				+ "			inner join sistema_seguridad_rol r on ur.rol_id = r.id "
				+ "where u.usuario = '" + usuario + "' and password = '" + password + "' ";

		String rol = "";
		
		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs != null) {
				
				while(rs.next()) {
					
					rol = rs.getString("codigo");
					break;
				}

				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		}finally {

			DbConnection.cerrarConexion(rs, stmt, conn);
		}

		return rol;
	}


}
