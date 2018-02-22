package model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import model.dto.MateriasPrimas;
import model.dto.MateriasPrimasCantidad;
import model.dto.Operarios;
import model.dto.Pasos;
import model.dto.Productos;
import utils.DbConnection;

public class PasosDao {

	public ArrayList<Pasos> getPasosByNumeroOT(String numeroOT) {

		ArrayList<Pasos> pasosList = new ArrayList<>();
		ProductosDao productosDao = new ProductosDao();
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT p.codigo "
				+ "FROM producto p "
				+ "	INNER JOIN orden_trabajo ot ON p.id = ot.producto_id "
				+ "WHERE ot.numero = '" + numeroOT + "'";
		
		try {
		conn = DbConnection.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		if(rs.next()) {

			String codigoProducto = rs.getString(0).trim();

			//codigo producto(0-19)
			//descripcion instruccion(20-59)
			//codigo materia prima(10 pos) + cantidad(5 pos) (60-69 + 70-74) //se repite segun la cantidad de materias
			File f = new File("./archivos/INSTRUCCIONES.txt");

			try {

				Scanner s = new Scanner(f);

				while(s.hasNextLine()) {

					String linea = s.nextLine();

					String codigoProductoTmp = linea.substring(0, 20).trim();

					if(codigoProductoTmp.equals(codigoProducto)) {
						
						Productos producto = productosDao.getProductoByCodigo();
						String descripcion = linea.substring(20, 60).trim();
						
						ArrayList<MateriasPrimasCantidad> materiasList = new ArrayList<>();
						String materiasPrimasLinea = linea.substring(60).trim();
						
						int i = 0;
						while(i < materiasPrimasLinea.length()) {
							
							String codigoMateriaPrima = materiasPrimasLinea.substring(i, i+10).trim();
							int cantidadMateriaPrima = Integer.parseInt(materiasPrimasLinea.substring(i+10, i+15));
							MateriasPrimas materiaPrima = materiasPrimasDao.getMateriaPrimaByCodigo(codigoMateriaPrima);	
							
							materiasList.add(new MateriasPrimasCantidad(materiaPrima, cantidadMateriaPrima));
							
							i = i + 15;
						}
						
						Pasos paso = new Pasos();
						paso.setDescripcion(descripcion);
						paso.setMateriasPrimas(materiasList);
						paso.setProducto(producto);
						
						pasosList.add(paso);
					}
				}
				
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}
	}catch(SQLException e) {
		
		e.printStackTrace();
	}finally {
		
		DbConnection.cerrarConexion(rs);
		DbConnection.cerrarConexion(stmt);
		DbConnection.cerrarConexion(conn);
	}
		return pasosList;
	}

	public ArrayList<Operarios> getOperarios() {
		
		20180221 - Levantar los operarios para mostrar en los combos 
		return null;
	}

}
