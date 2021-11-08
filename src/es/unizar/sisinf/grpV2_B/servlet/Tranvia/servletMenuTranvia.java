package es.unizar.sisinf.grpV2_B.servlet.Tranvia;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class servletMenuTranvia extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor
	public servletMenuTranvia() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        paradaTranviaDAO tranvia = new paradaTranviaDAO();
        
        List<String> sentidos = new LinkedList<String>();
        List<paradaTranviaVO> sentido1 = new LinkedList<paradaTranviaVO>();
        List<paradaTranviaVO> sentido2 = new LinkedList<paradaTranviaVO>();
        
        try {
        	 sentidos = tranvia.listarSentidos();
             sentido1 = tranvia.listarOrdenada(sentidos.get(0));
             sentido2 = tranvia.listarOrdenada(sentidos.get(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<String> paradasS1 = new LinkedList<String>();
        List<String> paradasS2 = new LinkedList<String>();
		for (paradaTranviaVO i : sentido1) {
        	paradasS1.add(i.getNombre());
        }
        
		for (paradaTranviaVO i : sentido2) {
			paradasS2.add(i.getNombre());
		}
		
        request.setAttribute("sentidos", sentidos);
        request.setAttribute("sentido1", paradasS1);
        request.setAttribute("sentido2", paradasS2);
        
        request.getRequestDispatcher("mostrarMenuTranvia.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}