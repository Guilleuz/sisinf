package es.unizar.sisinf.grpV2_B.servlet.Tranvia;


import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class servletParadaTranvia extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public servletParadaTranvia() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sentido = request.getParameter("sentido");
		String nombre = request.getParameter("nombre");
		paradaTranviaDAO tranvia = new paradaTranviaDAO();
		paradaTranviaVO parada = null;
		llegadaTranviaVO llegada = null;
		try {
			int id = tranvia.idParada(nombre, sentido);
			parada = tranvia.infoTranvia(id);
			// TODO acceso API
			//llegada = new llegadaTranviaDAO().getLlegadas(id);
			llegada = new llegadaTranviaVO(1, "5 minutos", "10 minutos");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("parada", parada);
		request.setAttribute("llegada", llegada);
        request.getRequestDispatcher("mostrarParadaTranvia.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}