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
			if (p.existeParada(id)) {
				request.getRequestDispatcher("/paradaBus").forward(request, response);
            }
			else {
				request.setAttribute("error", true);
				request.setAttribute("poste", "" + id);
				request.getRequestDispatcher("/menuBus").forward(request, response);
				// request.getRequestDispatcher("/index.html").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
