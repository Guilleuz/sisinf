package es.unizar.sisinf.grpV2_B.servlet.Bizi;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.postgis.PGgeometry;
import org.postgis.Point;

public class servletMenuBizi extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor
	public servletMenuBizi() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<biziVO> listaEstaciones = new LinkedList<biziVO>();
        try {
			listaEstaciones = new biziDAO().listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        request.setAttribute("lista", listaEstaciones);
        request.getRequestDispatcher("mostrarMenuBizi.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}