package web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getParameter("menu").equals("ConsultaStock") ) {
			
			request.getRequestDispatcher("consultaStock.jsp").forward(request, response);
		
		}else if(request.getParameter("menu").equals("ConsultaOT") ) {
			
			request.getRequestDispatcher("consultaOT.jsp").forward(request, response);
		}
		else if(request.getParameter("menu").equals("ActualizacionInstrucciones") ) {
			
			request.getRequestDispatcher("actualizacionInstrucciones.jsp").forward(request, response);
		}
		else if(request.getParameter("menu").equals("RegistroCompras") ) {
			
			request.getRequestDispatcher("registroCompras.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		doGet(request, response);
		

	}

}
