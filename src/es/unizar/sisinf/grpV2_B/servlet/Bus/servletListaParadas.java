package es.unizar.sisinf.grpV2_B.servlet.Bus;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class servletListaParadas extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor
	public servletListaParadas() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenemos el listado de las paradas de bus de una línea en un determinado sentido
		String nombre = request.getParameter("linea");
		String sentido= request.getParameter("sentido");
		lineaBusVO linea = null;
		List<paradaTrayectoVO> paradas = new LinkedList<paradaTrayectoVO>();
        List<paradaBusVO> paradasBus = new LinkedList<paradaBusVO>(); 
		try {
			linea = new lineaBusDAO().obtenerLinea(nombre, sentido);
			paradas = new paradaTrayectoDAO().listarParadas(linea.getId());
			for (paradaTrayectoVO i : paradas) {
                paradasBus.add(new paradaBusDAO().obtenerParada(i.getParada()));
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Mostramos dicho listado en mostrarParadasLinea.jsp
		request.setAttribute("paradas", paradasBus);
		request.setAttribute("linea", nombre);
		request.setAttribute("sentido", sentido);
        request.getRequestDispatcher("mostrarParadasLinea.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}