package es.unizar.sisinf.grpV2_B.servlet.Bizi;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class datosEstacion extends HttpServlet {
    private static final long serialVersionUID = 2L;

    public datosEstacion() {
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
        biziDAO bizi = new biziDAO();
        biziVO estacion = null;
        
        try{
        	estacion = bizi.infoBizi(id);
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        
        request.setAttribute("estacion", estacion);
        request.getRequestDispatcher("mostrarDatos.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
