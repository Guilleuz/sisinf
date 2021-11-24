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
		// Obtenemos el nombre y el sentido de la parada a partir del formulario
		
		String sentido = request.getParameter("sentido");
		String nombre = "";
		if (sentido.equals("Avenida Academia")) {
			nombre = request.getParameter("n1");
		} else {
			nombre = request.getParameter("n2");
		}
		paradaTranviaDAO tranvia = new paradaTranviaDAO();
		paradaTranviaVO parada = null;
		llegadaTranviaVO llegada = null;
		try {
			// Consultamos la DB para obtener los datos de la parada
			int id = tranvia.idParada(nombre, sentido);
			parada = tranvia.infoTranvia(id);
			// Consultamos la API para obtener los tiempos de llegada
			llegada = new llegadaTranviaDAO().getLlegadas(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Mostramos la información de la parada y los tiempos de llegada en mostrarParadaTranvia.jsp
		request.setAttribute("parada", parada);
		request.setAttribute("llegada", llegada);
        request.getRequestDispatcher("mostrarParadaTranvia.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}