package es.unizar.sisinf.grpV2_B.prueba;

import es.unizar.sisinf.grpV2_B.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


@WebServlet("/list")
public class servlet {
    public servlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String calle = request.getParameter("category");
        request.setAttribute("prueba", calle);
        request.getRequestDispatcher("prueba.jsp").forward(request, response);
    }
}
