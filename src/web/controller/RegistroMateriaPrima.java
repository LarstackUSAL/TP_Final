package web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MateriasPrimasDao;
import model.dao.OrdenesCompraMateriasPrimasDao;

/**
 * Servlet implementation class RegistroMateriaPrima
 */
@WebServlet("/RegistroMateriaPrima")
public class RegistroMateriaPrima extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroMateriaPrima() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer materiaPrimaId = Integer.valueOf(request.getParameter("selectMateriaPrima"));
		Integer cantidad = Integer.valueOf(request.getParameter("cantidad"));
		boolean exterior = "on".equals(request.getParameter("exterior")) ? true : false;
		String deposito1 = request.getParameter("deposito1");
		String deposito2 = request.getParameter("deposito2");
		String deposito3 = request.getParameter("deposito3");
		
		OrdenesCompraMateriasPrimasDao ordenesCompraMateriasPrimasDao = new OrdenesCompraMateriasPrimasDao();
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();
		
		boolean ok = ordenesCompraMateriasPrimasDao.insertarOrdenCompra(materiaPrimaId, cantidad, exterior, deposito1,deposito2,deposito3);
		
		String mensajeActualizacion;
		
		if(ok) {
			
			mensajeActualizacion = "La compra se ha realizado con exito!";
			materiasPrimasDao.actualizarStock(materiaPrimaId, cantidad);
			
		}else mensajeActualizacion = "Se ha verificado un error al realizar la compra.";
		
		request.getSession().setAttribute("mensajeActualizacion", mensajeActualizacion);
		request.getRequestDispatcher("statusResponse.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
