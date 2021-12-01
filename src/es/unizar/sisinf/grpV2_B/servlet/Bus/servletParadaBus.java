package es.unizar.sisinf.grpV2_B.servlet.Bus;


import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

public class servletParadaBus extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public servletParadaBus() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dado un id de parada, obtenemos su información
		int id = 0;
    	try {
        	id = Integer.valueOf(request.getParameter("id"));
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        paradaBusVO bus = null;
		List<llegadaAutobusVO> llegada = new LinkedList<llegadaAutobusVO>();
		try{
			// Consultamos la API para obtener las llegadas de autobuses a la parada
        	bus = new paradaBusDAO().obtenerParada(id);
        	llegada = new llegadaAutobusDAO().getLlegadas(id);
        }
        catch (SQLException e){
        	e.printStackTrace();
        }

		List<String> primerasLlegadas = new LinkedList<String>();
		for (llegadaAutobusVO i : llegada) {
			primerasLlegadas.add(i.getPrimero());
		}
		
		HashMap<String, List<String>> llegadas = new HashMap<String, List<String>>();
		llegadas.put("result", primerasLlegadas);
		String llegadasJSON = new JSONObject(llegadas).toString();

		// Mostramos la información de la parada y las llegadas en mostrarParadaBus.jsp
		request.setAttribute("bus", bus);
		request.setAttribute("llegadas", llegada);
		request.setAttribute("llegadasJSON", llegadasJSON);
        request.getRequestDispatcher("mostrarParadaBus.jsp").forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}