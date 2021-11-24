package es.unizar.sisinf.grpV2_B.servlet.Bus;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

public class servletMenuBus extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenemos un listado de los nombres de línea
		// Por cada línea, obtenemos los dos sentidos
		lineaBusDAO l = new lineaBusDAO();
		try {
			List<String> lineas = l.obtenerListaNombres();
			HashMap<String, List<String>> sentidosLinea = new HashMap<String, List<String>>();
            
			for (String i : lineas) {
				sentidosLinea.put(i, l.obtenerSentidos(i));
			}
			
			JSONObject json =  new JSONObject(sentidosLinea);
			String sentidosLineaJSON = json.toString();
			//System.out.printf("%s", json);
			System.out.printf("%s", sentidosLineaJSON);
			
			// Mostramos la información de líneas y sus sentidos en mostrarMenuBus.jsp
			request.setAttribute("lineas", lineas);
			request.setAttribute("sentidosLinea", sentidosLineaJSON);
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