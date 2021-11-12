package es.unizar.sisinf.grpV2_B.servlet.Bus;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class servletMenuBus extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Lista de nombres de línea
		// Por cada línea, los dos sentidos
		lineaBusDAO l = new lineaBusDAO();
		try {
			List<String> lineas = l.obtenerListaNombres();
			HashMap<String, List<String>> sentidosLinea = new HashMap<String, List<String>>();
            
			for (String i : lineas) {
				sentidosLinea.put(i, l.obtenerSentidos(i));
			}
			
			request.setAttribute("lineas", lineas);
			request.setAttribute("sentidosLinea", sentidosLinea);
			request.getRequestDispatcher("mostrarMenuBus.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}