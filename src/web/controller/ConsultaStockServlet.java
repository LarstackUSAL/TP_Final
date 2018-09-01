package web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MateriasPrimasDao;

/**
 * Servlet implementation class ConsultaStockServlet
 */
@WebServlet("/ConsultaStock")
public class ConsultaStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaStockServlet() {
		super();

	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String codigoMateriaPrima = request.getParameter("codigoMateriaPrima");
		MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();
		
		int stock = materiasPrimasDao.consultaStock(codigoMateriaPrima);
//		int stock = 42;
		
		request.setAttribute("stock", stock);
		request.getRequestDispatcher("consultaStock.jsp").forward(request, response);
	}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}
