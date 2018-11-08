package model.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import model.dto.MateriasPrimas;
import model.dto.MateriasPrimasCantidad;
import model.dto.Operarios;
import model.dto.OrdenesTrabajos;
import model.dto.Pasos;
import model.dto.PasosAsignados;
import model.dto.Productos;
import utils.DbConnection;
import utils.Utilities;

public class PasosDao {

	public ArrayList<Pasos> getPasosByNumeroOT(String numeroOT) {

		ArrayList<Pasos> pasosList = new ArrayList<>();
		ProductosDao productosDao = new ProductosDao();
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT p.codigo as codigo_producto "
				+ "FROM producto p "
				+ "	INNER JOIN orden_trabajo ot ON p.id = ot.producto_id "
				+ "WHERE ot.numero = '" + numeroOT + "' ";

		try {
			conn = DbConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs.next()) {

				String codigoProducto = rs.getString("codigo_producto").trim();

				//id(0-3)
				//codigo producto(4-23)
				//descripcion instruccion(24-63)
				//codigo materia prima(10 pos) + cantidad(5 pos) (64-73 + 74-78) //se repite segun la cantidad de materias
				File f = new File("./archivos/INSTRUCCIONES.txt");

				try {

					Scanner s = new Scanner(f);

					while(s.hasNextLine()) {

						String linea = s.nextLine();

						int id = Integer.parseInt(linea.substring(0,4).trim());
						String codigoProductoTmp = linea.substring(4, 24).trim();

						if(codigoProductoTmp.equals(codigoProducto)) {

							Productos producto = productosDao.loadProducto(codigoProducto);
							String descripcion = linea.substring(24, 64).trim();

							ArrayList<MateriasPrimasCantidad> materiasList = new ArrayList<>();
							String materiasPrimasLinea = linea.substring(64).trim();

							int i = 0;
							while(i < materiasPrimasLinea.length()) {

								String codigoMateriaPrima = materiasPrimasLinea.substring(i, i+10).trim();
								int cantidadMateriaPrima = Integer.parseInt(materiasPrimasLinea.substring(i+10, i+15).trim());
								MateriasPrimas materiaPrima = materiasPrimasDao.loadMateriaPrima(codigoMateriaPrima);	

								materiasList.add(new MateriasPrimasCantidad(materiaPrima, cantidadMateriaPrima));

								i = i + 15;
							}

							Pasos paso = new Pasos();
							paso.setId(id);
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

	public boolean asignarTarea(OrdenesTrabajos ot, Pasos paso, Operarios operario) {

		boolean persistenciaOk = false;

		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;

		try {

			String insertOT = "insert into orden_trabajo_instruccion_operario( " +
					"orden_trabajo_id, " +
					"instruccion_id, " +
					"operario_id " +
					") "
					+ " values(?, ?, ?) ";

			stmt = conn.prepareStatement(insertOT);

			stmt.setInt(1, ot.getId());
			stmt.setInt(2, paso.getId());
			stmt.setInt(3, operario.getId());

			persistenciaOk = stmt.executeUpdate() > 0 ? true : false;

		} catch (Exception e) {


		}finally {

			DbConnection.cerrarConexion(null, stmt, conn);
		}

		return persistenciaOk;
	}

	public ArrayList<PasosAsignados> getPasosAsignadosByNumeroOT(String numero) {

		ArrayList<PasosAsignados> pasosList = new ArrayList<>();
		ProductosDao productosDao = new ProductosDao();
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();
		OperariosDao operariosDao = new OperariosDao();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = 
				"select " +
						"	otio.instruccion_id as instruccion_id, " +
						"	otio.operario_id as operario_id, " +
						"   otio.es_finalizado as es_finalizado, " +
						"	otio.fecha_inicio as fecha_inicio, " +
						"	otio.fecha_finalizacion as fecha_finalizacion " +
						"from orden_trabajo_instruccion_operario otio " +
						"	inner join orden_trabajo ot on otio.orden_trabajo_id = ot.id " +
						"where ot.numero = '" + numero + "'";

		try {
			conn = DbConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {

				int idInstruccion = rs.getInt("instruccion_id");

				//id(0-3)
				//codigo producto(4-23)
				//descripcion instruccion(24-63)
				//codigo materia prima(10 pos) + cantidad(5 pos) (64-73 + 74-78) //se repite segun la cantidad de materias
				File f = new File("./archivos/INSTRUCCIONES.txt");

				try {

					Scanner s = new Scanner(f);

					while(s.hasNextLine()) {

						String linea = s.nextLine();

						int id = Integer.parseInt(linea.substring(0,4).trim());
						String codigoProducto = linea.substring(4, 24).trim();

						if(id == idInstruccion) {

							Productos producto = productosDao.loadProducto(codigoProducto);
							String descripcion = linea.substring(24, 64).trim();

							ArrayList<MateriasPrimasCantidad> materiasList = new ArrayList<>();
							String materiasPrimasLinea = linea.substring(64).trim();

							int i = 0;
							while(i < materiasPrimasLinea.length()) {

								String codigoMateriaPrima = materiasPrimasLinea.substring(i, i+10).trim();
								int cantidadMateriaPrima = Integer.parseInt(materiasPrimasLinea.substring(i+10, i+15).trim());
								MateriasPrimas materiaPrima = materiasPrimasDao.loadMateriaPrima(codigoMateriaPrima);	

								materiasList.add(new MateriasPrimasCantidad(materiaPrima, cantidadMateriaPrima));

								i = i + 15;
							}

							PasosAsignados paso = new PasosAsignados();
							paso.setId(id);
							paso.setDescripcion(descripcion);
							paso.setMateriasPrimas(materiasList);
							paso.setProducto(producto);

							Calendar calInicio = null;
							Timestamp fechaInicioTs = rs.getTimestamp("fecha_inicio");
							if(fechaInicioTs != null) {

								calInicio = Calendar.getInstance();
								calInicio.setTimeInMillis(fechaInicioTs.getTime());
							}

							Calendar calFin = null;
							Timestamp fechaFinalizacionTs = rs.getTimestamp("fecha_finalizacion");
							if(fechaFinalizacionTs != null) {

								calFin = Calendar.getInstance();
								calFin.setTimeInMillis(fechaFinalizacionTs.getTime());
							}

							paso.setFechaInicio(calInicio);
							paso.setFechaFinalizacion(calFin);
							paso.setOperario(operariosDao.loadOperarioById(rs.getInt("operario_id")));

							paso.setEsFinalizado(rs.getBoolean("es_finalizado"));
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

	public ArrayList<PasosAsignados> getPasosAsignadosByOperario(Operarios operario) {

		ArrayList<PasosAsignados> pasosList = new ArrayList<>();
		ProductosDao productosDao = new ProductosDao();
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();
		OperariosDao operariosDao = new OperariosDao();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = 
				"select " +
						"	otio.instruccion_id as instruccion_id, " +
						"	otio.operario_id as operario_id, " +
						"   otio.es_finalizado as es_finalizado, " +
						"	otio.fecha_inicio as fecha_inicio, " +
						"	otio.fecha_finalizacion as fecha_finalizacion " +
						"from orden_trabajo_instruccion_operario otio " +
						"	inner join orden_trabajo ot on otio.orden_trabajo_id = ot.id " +
						"where otio.operario_id = " + operario.getId();

		try {
			conn = DbConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {

				int idInstruccion = rs.getInt("instruccion_id");

				//id(0-3)
				//codigo producto(4-23)
				//descripcion instruccion(24-63)
				//codigo materia prima(10 pos) + cantidad(5 pos) (64-73 + 74-78) //se repite segun la cantidad de materias
				File f = new File("./archivos/INSTRUCCIONES.txt");				

				try {

					Scanner s = new Scanner(f);

					while(s.hasNextLine()) {

						String linea = s.nextLine();

						int id = Integer.parseInt(linea.substring(0,4).trim());
						String codigoProducto = linea.substring(4, 24).trim();

						if(id == idInstruccion) {

							Productos producto = productosDao.loadProducto(codigoProducto);
							String descripcion = linea.substring(24, 64).trim();

							ArrayList<MateriasPrimasCantidad> materiasList = new ArrayList<>();
							String materiasPrimasLinea = linea.substring(64).trim();

							int i = 0;
							while(i < materiasPrimasLinea.length()) {

								String codigoMateriaPrima = materiasPrimasLinea.substring(i, i+10).trim();
								int cantidadMateriaPrima = Integer.parseInt(materiasPrimasLinea.substring(i+10, i+15).trim());
								MateriasPrimas materiaPrima = materiasPrimasDao.loadMateriaPrima(codigoMateriaPrima);	

								materiasList.add(new MateriasPrimasCantidad(materiaPrima, cantidadMateriaPrima));

								i = i + 15;
							}

							PasosAsignados paso = new PasosAsignados();
							paso.setId(id);
							paso.setDescripcion(descripcion);
							paso.setMateriasPrimas(materiasList);
							paso.setProducto(producto);

							Calendar calInicio = null;
							Timestamp fechaInicioTs = rs.getTimestamp("fecha_inicio");
							if(fechaInicioTs != null) {

								calInicio = Calendar.getInstance();
								calInicio.setTimeInMillis(fechaInicioTs.getTime());
							}

							Calendar calFin = null;
							Timestamp fechaFinalizacionTs = rs.getTimestamp("fecha_finalizacion");
							if(fechaFinalizacionTs != null) {

								calFin = Calendar.getInstance();
								calFin.setTimeInMillis(fechaFinalizacionTs.getTime());
							}

							paso.setFechaInicio(calInicio);
							paso.setFechaFinalizacion(calFin);
							paso.setOperario(operariosDao.loadOperarioById(rs.getInt("operario_id")));

							paso.setEsFinalizado(rs.getBoolean("es_finalizado"));
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

	public boolean iniciarPaso(int idPaso) {

		boolean persistenciaOk = false;

		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;

		try {

			//PostgrSQL
			String updatePaso = "update orden_trabajo_instruccion_operario set fecha_inicio=now() where id=" + idPaso;

			//SQL Server
			//			String insertOT = "update orden_trabajo_instruccion_operario set fecha_inicio=getDate() where id =" + idPaso;

			stmt = conn.prepareStatement(updatePaso);

			persistenciaOk = stmt.executeUpdate() > 0 ? true : false;

		} catch (Exception e) {


		}finally {

			DbConnection.cerrarConexion(null, stmt, conn);
		}

		return persistenciaOk;
	}

	public boolean finalizarPaso(int idPaso) {

		boolean persistenciaOk = false;

		Connection conn = DbConnection.getConnection();
		PreparedStatement stmtPaso = null;

		try {

			conn.setAutoCommit(false);

			//PostgrSQL
			String updatePaso = "update orden_trabajo_instruccion_operario set fecha_finalizacion=now(), es_finalizado=true where id=" + idPaso;

			//SQL Server
			//			String insertOT = "update orden_trabajo_instruccion_operario set fecha_finalizacion=getDate(), es_finalizado=true where id =" + idPaso;

			stmtPaso = conn.prepareStatement(updatePaso);

			persistenciaOk = stmtPaso.executeUpdate() > 0 ? true : false;

			if(persistenciaOk) {

				OrdenesTrabajosDao ordenesTrabajosDao = new OrdenesTrabajosDao();
				OrdenesTrabajos ordenTrabajo = ordenesTrabajosDao.getOrdenTrabajoByPaso(this.getPasoById(idPaso));

				String sql = "select count(*) as total " +
						"from orden_trabajo_instruccion_operario otio " +
						"where otio.orden_trabajo_id = " + ordenTrabajo.getId() +
						"	and fecha_finalizacion is null";

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);


				if(rs != null && rs.next()) {

					Integer total = rs.getInt("total");

					if(total == 0) {

						String updateOt = "update orden_trabajo set fecha_finalizacion=now() where id=" + ordenTrabajo.getId();

						PreparedStatement stmtOt = conn.prepareStatement(updateOt);

						persistenciaOk = stmtOt.executeUpdate() > 0 ? true : false;
					}
				}				

				conn.commit();
			}

		} catch (Exception e) {

			persistenciaOk = false;

		}finally {

			DbConnection.cerrarConexion(null, stmtPaso, conn);
		}

		return persistenciaOk;
	}

	private Pasos getPasoById(int idInstruccion) {

		Pasos paso = null;
		ProductosDao productosDao = new ProductosDao();
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();

		//id(0-3)
		//codigo producto(4-23)
		//descripcion instruccion(24-63)
		//codigo materia prima(10 pos) + cantidad(5 pos) (64-73 + 74-78) //se repite segun la cantidad de materias
		File f = new File("./archivos/INSTRUCCIONES.txt");

		try {

			Scanner s = new Scanner(f);

			while(s.hasNextLine()) {

				String linea = s.nextLine();

				int id = Integer.parseInt(linea.substring(0,4).trim());
				String codigoProducto = linea.substring(4, 24).trim();

				if(id == idInstruccion) {

					Productos producto = productosDao.loadProducto(codigoProducto);
					String descripcion = linea.substring(24, 64).trim();

					ArrayList<MateriasPrimasCantidad> materiasList = new ArrayList<>();
					String materiasPrimasLinea = linea.substring(64).trim();

					int i = 0;
					while(i < materiasPrimasLinea.length()) {

						String codigoMateriaPrima = materiasPrimasLinea.substring(i, i+10).trim();
						int cantidadMateriaPrima = Integer.parseInt(materiasPrimasLinea.substring(i+10, i+15).trim());
						MateriasPrimas materiaPrima = materiasPrimasDao.loadMateriaPrima(codigoMateriaPrima);	

						materiasList.add(new MateriasPrimasCantidad(materiaPrima, cantidadMateriaPrima));

						i = i + 15;
					}

					paso = new Pasos();
					paso.setId(id);
					paso.setDescripcion(descripcion);
					paso.setMateriasPrimas(materiasList);
					paso.setProducto(producto);

				}
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return paso;
	}

	public ArrayList<Pasos> getPasosByCodigoProducto(String codigo) {

		ArrayList<Pasos> pasos = new ArrayList<>();
		ProductosDao productosDao = new ProductosDao();
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();

		//id(0-3)
		//codigo producto(4-23)
		//descripcion instruccion(24-63)
		//codigo materia prima(10 pos) + cantidad(5 pos) (64-73 + 74-78) //se repite segun la cantidad de materias

		String currentDirectory = new File("").getAbsolutePath();

		//		File f = new File("./archivos/INSTRUCCIONES.txt");
		File f = new File(currentDirectory + "/archivos/INSTRUCCIONES.txt");
		try {

			Scanner s = new Scanner(f);

			while(s.hasNextLine()) {

				String linea = s.nextLine();

				int id = Integer.parseInt(linea.substring(0,4).trim());
				String codigoProducto = linea.substring(4, 24).trim();

				if(codigoProducto.equals(codigo)) {

					Productos producto = productosDao.loadProducto(codigoProducto);
					String descripcion = linea.substring(24, 64).trim();

					ArrayList<MateriasPrimasCantidad> materiasList = new ArrayList<>();
					String materiasPrimasLinea = linea.substring(64).trim();

					int i = 0;
					while(i < materiasPrimasLinea.length()) {

						String codigoMateriaPrima = materiasPrimasLinea.substring(i, i+10).trim();
						int cantidadMateriaPrima = Integer.parseInt(materiasPrimasLinea.substring(i+10, i+15).trim());
						MateriasPrimas materiaPrima = materiasPrimasDao.loadMateriaPrima(codigoMateriaPrima);	

						materiasList.add(new MateriasPrimasCantidad(materiaPrima, cantidadMateriaPrima));

						i = i + 15;
					}

					Pasos paso = new Pasos();
					paso.setId(id);
					paso.setDescripcion(descripcion);
					paso.setMateriasPrimas(materiasList);
					paso.setProducto(producto);

					pasos.add(paso);
				}
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return pasos;
	}

	public void actualizarInstrucciones(List<Pasos> pasos) {

		ProductosDao productosDao = new ProductosDao();
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();

		//id(0-3)
		//codigo producto(4-23)
		//descripcion instruccion(24-63)
		//codigo materia prima(10 pos) + cantidad(5 pos) (64-73 + 74-78) //se repite segun la cantidad de materias

		String currentDirectory = new File("").getAbsolutePath();

		try {

//			File f = new File("./archivos/INSTRUCCIONES.txt");
			File f = new File(currentDirectory + "/archivos/INSTRUCCIONES.txt");

			FileWriter fw = new FileWriter(currentDirectory + "/archivos/INSTRUCCIONES_tmp.txt");
			PrintWriter fwOut = new PrintWriter(fw);

			Scanner s = new Scanner(f);

//			CORREGIR PORQUE ESTÁ GRABANDO MAL EL ARCHIVO (DUPLICA LAS FILAS)
			
			while(s.hasNextLine()) {

				String linea = s.nextLine();

				int id = Integer.parseInt(linea.substring(0,4).trim());

				for (int i = 0; i < pasos.size(); i++) {

					if(pasos.get(i).getId() == id) {

						String lineaWrite = "";

						String idString = Utilities.fillString(String.valueOf(id), 4, " ", true);
						String codigo = Utilities.fillString(String.valueOf(pasos.get(i).getProducto().getCodigo()), 20, " ", false);
						String descripcion = Utilities.fillString(String.valueOf(pasos.get(i).getDescripcion()), 40, " ", false);

						lineaWrite = idString + codigo + descripcion;

						for (int j = 0; j < pasos.get(i).getMateriasPrimas().size(); j++) {

							MateriasPrimasCantidad mat = pasos.get(i).getMateriasPrimas().get(j);

							String codigoMat = Utilities.fillString(mat.getMateriaPrima().getCodigo(), 10, " ", false);
							String cantidadMat = Utilities.fillString(String.valueOf(mat.getCantidad()), 5, " ", false);

							lineaWrite = codigoMat + cantidadMat;
						}
						
						fwOut.println(lineaWrite);
						
					} else {
						
						fwOut.println(linea);
					}
				}
			}
			
			fwOut.close();
			fw.close();
			s.close();
			
			f.delete();
					
			File newInstrucciones = new File(currentDirectory + "/archivos/INSTRUCCIONES.txt");

			File tmp = new File(currentDirectory + "/archivos/INSTRUCCIONES_tmp.txt");
			tmp.renameTo(newInstrucciones);
			
			tmp.delete();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
