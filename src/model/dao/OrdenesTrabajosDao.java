package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import model.dto.OrdenesTrabajos;
import model.dto.Pasos;
import utils.DbConnection;
import utils.Utilities;

public class OrdenesTrabajosDao {

	public String generarNumeroOrden() {

		String numeroOrden = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT numero FROM orden_trabajo ORDER BY id DESC";

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String ultimoNumero = "0000/00";

			if(rs != null && rs.next()) {

				ultimoNumero = rs.getString("numero");

			}

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
		PreparedStatement stmt = null;

		try {

			String insertOT = "insert into orden_trabajo( " +
					"numero, " +
					"producto_id, " +
					"cantidad_requerida, " +
					"fecha_estimada_finalizacion, " +
					"descripcion, " +
					"es_urgente " +
					") " +
					"select ?, p.id, ?, ?, ?, ? " +
					"from producto p "
					+ "where p.codigo = ?";

			stmt = conn.prepareStatement(insertOT);

			stmt.setString(1, ot.getNumero());

			stmt.setInt(2, ot.getCantidadRequerida());
			stmt.setDate(3, new Date(ot.getFechaEstimadaFinalizacion().getTimeInMillis()));
			stmt.setString(4, ot.getDescripcion());
			stmt.setBoolean(5, ot.isEsUrgente());
			stmt.setString(6, ot.getProducto().getCodigo());


			persistenciaOk = stmt.executeUpdate() > 0 ? true : false;

		} catch (Exception e) {


		}finally {

			DbConnection.cerrarConexion(null, stmt, conn);
		}

		return persistenciaOk;
	}

	public ArrayList<OrdenesTrabajos> getOrdenesTrabajos(boolean pendientes) {

		ProductosDao productosDao = new ProductosDao();
		ArrayList<OrdenesTrabajos> ordenesTrabajosList = null;

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT distinct "
				+ "ot.id, " +
				"ot.numero, " +
				"ot.fecha_alta, " +
				"p.codigo as codigo_producto, " +
				"ot.cantidad_requerida, " +
				"ot.fecha_estimada_finalizacion, "	+
				"ot.fecha_finalizacion, " +
				"ot.descripcion, " +
				"ot.es_urgente " +
				"FROM orden_trabajo ot "
				+ "INNER JOIN producto p ON ot.producto_id = p.id "
				+ "		left join orden_trabajo_instruccion_operario otio on ot.id = otio.orden_trabajo_id "
				+ "where 1=1 ";

		if(pendientes) {

			sql += " and otio.id is null ";
		}
		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs != null) {

				ordenesTrabajosList = new ArrayList<>();

				while(rs.next()) {

					OrdenesTrabajos ot = new OrdenesTrabajos();

					ot.setId(rs.getInt("id"));

					Calendar calFechaAlta = Calendar.getInstance();
					calFechaAlta.setTimeInMillis(rs.getTimestamp("fecha_alta").getTime());

					Calendar calFechaEstimada = Calendar.getInstance();
					calFechaEstimada.setTimeInMillis(rs.getTimestamp("fecha_estimada_finalizacion").getTime());

					Calendar calFechaFinalizacion = Calendar.getInstance();
					Timestamp fechaFinalizacion = rs.getTimestamp("fecha_finalizacion");
					if(fechaFinalizacion != null)
						calFechaFinalizacion.setTimeInMillis(fechaFinalizacion.getTime());
					else calFechaFinalizacion = null;

					ot.setNumero(rs.getString("numero"));
					ot.setFechaAlta(calFechaAlta);
					ot.setProducto(productosDao.loadProducto(rs.getString("codigo_producto")));
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

	public OrdenesTrabajos getOrdenTrabajoByPaso(Pasos paso) {

		OrdenesTrabajos ot = null;

		ProductosDao productosDao = new ProductosDao();

		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT distinct "
				+ "ot.id, " +
				"ot.numero, " +
				"ot.fecha_alta, " +
				"p.codigo as codigo_producto, " +
				"ot.cantidad_requerida, " +
				"ot.fecha_estimada_finalizacion, "	+
				"ot.fecha_finalizacion, " +
				"ot.descripcion, " +
				"ot.es_urgente " +
				"FROM orden_trabajo ot "
				+ "	inner join orden_trabajo_instruccion_operario otio on ot.id = otio.orden_trabajo_id "
				+ "	inner join producto p on ot.producto_id = p.id "
				+ "where otio.instruccion_id =  " + paso.getId();

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs != null && rs.next()) {


				ot = new OrdenesTrabajos();

				ot.setId(rs.getInt("id"));

				Calendar calFechaAlta = Calendar.getInstance();
				calFechaAlta.setTimeInMillis(rs.getTimestamp("fecha_alta").getTime());

				Calendar calFechaEstimada = Calendar.getInstance();
				calFechaEstimada.setTimeInMillis(rs.getTimestamp("fecha_estimada_finalizacion").getTime());

				Calendar calFechaFinalizacion = Calendar.getInstance();
				Timestamp fechaFinalizacion = rs.getTimestamp("fecha_finalizacion");
				if(fechaFinalizacion != null)
					calFechaFinalizacion.setTimeInMillis(fechaFinalizacion.getTime());
				else calFechaFinalizacion = null;

				ot.setNumero(rs.getString("numero"));
				ot.setFechaAlta(calFechaAlta);
				ot.setProducto(productosDao.loadProducto(rs.getString("codigo_producto")));
				ot.setCantidadRequerida(rs.getInt("cantidad_requerida"));
				ot.setFechaEstimadaFinalizacion(calFechaEstimada);
				ot.setFechaFinalizacion(calFechaFinalizacion);
				ot.setDescripcion(rs.getString("descripcion"));
				ot.setEsUrgente(rs.getBoolean("es_urgente"));

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}finally {

			DbConnection.cerrarConexion(rs, stmt, conn);
		}

		return ot;
	}	
}
