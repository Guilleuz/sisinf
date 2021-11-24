package es.unizar.sisinf.grpV2_B.servlet.Bus;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.unizar.sisinf.grpV2_B.model.paradaBusDAO;

public class servletValidarPoste extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
    	try {
        	id = Integer.valueOf(request.getParameter("id"));
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    	
    	paradaBusDAO p = new paradaBusDAO();
    	try {
    		// Consultamos la DB para comprobar si una parada determinada existe
			if (p.existeParada(id)) {
				// Si existe, redirigimos al servlet encargado de mostrar sus datos
				request.getRequestDispatcher("/paradaBus").forward(request, response);
            }
			else {
				// Si no, redirigimos al menú de autobús, con el mensaje de error correspondiente
				request.setAttribute("error", true);
				request.setAttribute("poste", "" + id);
				request.getRequestDispatcher("/menuBus").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
