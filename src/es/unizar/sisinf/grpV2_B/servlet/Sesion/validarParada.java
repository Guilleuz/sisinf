package es.unizar.sisinf.grpV2_B.servlet.Sesion;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.postgis.PGgeometry;
import org.postgis.Point;

import es.unizar.sisinf.grpV2_B.model.paradaBusDAO;
import es.unizar.sisinf.grpV2_B.model.paradaBusVO;

public class validarParada extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String poste = request.getParameter("ID");
            String dir = request.getParameter("direccion");
            String lat = request.getParameter("latitud");
            String lon =request.getParameter("longitud");
            
            request.setAttribute("poste", poste);
            request.setAttribute("direccion", dir);
            request.setAttribute("latitud", lat);
            request.setAttribute("longitud", lon);
            
            if (poste == null || dir == null || lat == null || lon == null) {
            	request.setAttribute("error", "Campos vacíos");
            	request.getRequestDispatcher("introducirParada.jsp").forward(request, response);
            }
            
            paradaBusDAO bus = new paradaBusDAO();
			if(bus.existeParada(Integer.valueOf("poste"))) {
				request.setAttribute("error", "Numero de poste ya existente");
				request.getRequestDispatcher("introducirParada.jsp").forward(request, response);
			}
			
			paradaBusVO parada = new paradaBusVO(Integer.valueOf(poste), dir, new Point(Double.parseDouble(lat), Double.parseDouble(lon)));
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
    

	
}