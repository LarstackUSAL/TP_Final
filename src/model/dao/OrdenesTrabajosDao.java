package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import model.dto.OrdenesTrabajos;
import utils.DbConnection;
import utils.Utilities;

public class OrdenesTrabajosDao {

	public String generarNumeroOrden() {
		
		String numeroOrden = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		
		String sql = "SELECT TOP 1 numero FROM orden_trabajo ORDER BY id DESC";
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs != null) {
				
				String ultimoNumero = rs.getString(0);
				
				Calendar cal = Calendar.getInstance();
				int anioActual = cal.get(Calendar.YEAR);
				int anio = Integer.parseInt(String.valueOf(anioActual).substring(2));
				
				int anioDb = Integer.parseInt(ultimoNumero.substring(5));
				int numeroDb = Integer.parseInt(ultimoNumero.substring(0, 4));
				
				if(anioDb == anio) {
					
					numeroDb++;
				}else {
					
					numeroDb = 1;
				}
				
				String numeroString = Utilities.fillString(String.valueOf(numeroDb), 4, "0", true);				
				
				numeroOrden = numeroString + "/" + anio;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			
			DbConnection.cerrarConexion(rs, stmt, conn);
		}
		
		return numeroOrden;
	}

	public boolean persistirNuevaOT(OrdenesTrabajos ot) {

		boolean persistenciaOk = false;
		
		Connection conn = DbConnection.getConnection();
		Statement stmt = null;

		try {

			String insertOT = "insert into orden_trabajo( " +
						"numero, " +
						"fecha_alta, " +
						"codigo_producto, " +
						"cantidad_requerida, " +
						"fecha_estimada_finalizacion, " +
						"descripcion, " +
						"es_urgente " +
					") " +
					"values( " +
						ot.getNumero() + "," +
						"getDate(), " +
						ot.getCodigoProducto() + "," +
						ot.getCantidadRequerida() + "," +
						Utilities.calendarToString(ot.getFechaEstimadaFinalizacion(), "yyyyMMdd") + "," +
						ot.getDescripcion() + "," +
						ot.isEsUrgente() +
					")";

			persistenciaOk = stmt.execute(insertOT);

		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			
			DbConnection.cerrarConexion(null, stmt, conn);
		}
		
		return persistenciaOk;
	}

	public ArrayList<OrdenesTrabajos> getOrdenesTrabajos(boolean pendientes) {

		ArrayList<OrdenesTrabajos> ordenesTrabajosList = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;
		
		String sql = "SELECT " +
				"numero, " +
				"fecha_alta, " +
				"codigo_producto, " +
				"cantidad_requerida, " +
				"fecha_estimada_finalizacion, "	+
				"fecha_finalizacion, " +
				"descripcion, " +
				"es_urgente " +
				"FROM orden_trabajo ";

				if(pendientes) {

					sql += "WHERE fecha_finalizacion IS NULL ";
				}
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs != null) {
				
				ordenesTrabajosList = new ArrayList<>();
				
				while(rs.next()) {
					
					OrdenesTrabajos ot = new OrdenesTrabajos();
					
					Calendar calFechaAlta = Calendar.getInstance();
					calFechaAlta.setTimeInMillis(rs.getTimestamp("fecha_alta").getTime());
					
					Calendar calFechaEstimada = Calendar.getInstance();
					calFechaEstimada.setTimeInMillis(rs.getTimestamp("fecha_estimada_finalizacion").getTime());
					
					Calendar calFechaFinalizacion = Calendar.getInstance();
					calFechaFinalizacion.setTimeInMillis(rs.getTimestamp("fecha_finalizacion").getTime());

					ot.setNumero(rs.getString("numero"));
					ot.setFechaAlta(calFechaAlta);
					ot.setCodigoProducto(rs.getString("codigo_producto"));
					ot.setCantidadRequerida(rs.getInt("cantidad_requerida"));
					ot.setFechaEstimadaFinalizacion(calFechaEstimada);
					ot.setFechaFinalizacion(calFechaFinalizacion);
					ot.setDescripcion(rs.getString("descripcion"));
					ot.setEsUrgente(rs.getBoolean("es_urgente"));
					
					ordenesTrabajosList.add(ot);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}finally {
			
			DbConnection.cerrarConexion(rs, stmt, conn);
		}
		
		return ordenesTrabajosList;
	}	
}
