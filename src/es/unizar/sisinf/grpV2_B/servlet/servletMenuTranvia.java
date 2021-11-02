package es.unizar.sisinf.grpV2_B.servlet;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class servletMenuTranvia extends HttpServlet {
	// Constructor
	public servletMenuTranvia() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<paradaTranviaVO> listaEstaciones = new LinkedList<paradaTranviaVO>();
        try {
			listaEstaciones = new paradaTranviaDAO().listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("lista", listaEstaciones);
        request.getRequestDispatcher("mostrarMenuTranvia.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}