package es.unizar.sisinf.grpV2_B.servlet.Bus;


import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

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
		int id = 0;
    	try {
        	id = Integer.valueOf(request.getParameter("id"));
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        paradaBusVO bus = null;
		
		try{
        	bus = new paradaBusDAO().obtenerParada(id);
        }
        catch (SQLException e){
        	e.printStackTrace();
        }


		request.setAttribute("bus", bus);
        request.getRequestDispatcher("mostrarParadaBus.jsp").forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}