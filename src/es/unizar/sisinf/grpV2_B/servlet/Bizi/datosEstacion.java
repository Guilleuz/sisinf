package es.unizar.sisinf.grpV2_B.servlet.Bizi;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/list")
public class datosEstacion extends HttpServlet {
    private static final long serialVersionUID = 2L;

    public datosEstacion() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("id");
        biziDAO bizi = new biziDAO();
        
        try{
        biziVO estacion = bizi.infoBizi(id);
        }
        catch (SQLException e){
		se.printStackTrace();
        }
        
        request.setAttribute("estacion", estacion);
        request.getRequestDispatcher("mostrarDatos.jsp").forward(request, response);   
}
