package web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PasosDao;
import model.dto.MateriasPrimasCantidad;
import model.dto.Pasos;

/**
 * Servlet implementation class ActualizacionInstrucciones
 */
@WebServlet("/ActualizacionInstrucciones")
public class ActualizacionInstrucciones extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizacionInstrucciones() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("buscar").equals("true")) {
			
			String codigoProducto = request.getParameter("consultaProducto").trim();
			PasosDao pasosDao = new PasosDao();
			ArrayList<Pasos> pasos = pasosDao.getPasosByCodigoProducto(codigoProducto);

			request.getSession().setAttribute("pasos", pasos);
			request.getSession().setAttribute("mensajeActualizacion", null);
			request.getRequestDispatcher("actualizacionInstrucciones.jsp").forward(request, response);
		}else {
			
			List<Pasos> pasos = (List<Pasos>) request.getSession().getAttribute("pasos");
			
			for (int i = 0; i < pasos.size(); i++) {
				
				Pasos paso = pasos.get(i);
				paso.setDescripcion(request.getParameter("descripcion_" + paso.getId()));
				
					for (int j = 0; j < paso.getMateriasPrimas().size(); j++) {
					
						MateriasPrimasCantidad mp = paso.getMateriasPrimas().get(j);
						mp.setCantidad(Integer.parseInt(request.getParameter(paso.getId() + "_" + mp.getMateriaPrima().getCodigo())));
					}
			}
			
			PasosDao pasosDao = new PasosDao();
			pasosDao.actualizarInstrucciones(pasos);
			
			request.getSession().setAttribute("pasos", null);
			request.getSession().setAttribute("mensajeActualizacion", "Instrucciones actualizadas con exito!");
			request.getRequestDispatcher("actualizacionInstrucciones.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
