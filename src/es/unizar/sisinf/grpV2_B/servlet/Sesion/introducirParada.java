package es.unizar.sisinf.grpV2_B.servlet.Sesion;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unizar.sisinf.grpV2_B.model.*;

public class introducirParada extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		Map<String, String> errors = new HashMap<String, String>();
		
		if (sesion.getAttribute("usuario") == null) {
			// no ha iniciado sesión, comprobamos la contraseña
			String usuario = request.getParameter("usuario");
			String pass = request.getParameter("pass");
			System.out.println(usuario + ":" + pass + ":");
			
			// Comprobamos si alguno de los campos del formulario está vacío
			if(usuario == null || (usuario.trim().equals(""))) errors.put("usuario", "Campo Obligatorio");
			if(pass == null || (pass.trim().equals(""))) errors.put("pass", "Campo Obligatorio");
			
			if(!errors.isEmpty()) {
				// Redireccion con errores al jsp
				request.setAttribute("errores", errors);
				request.getRequestDispatcher("iniciarSesion.jsp").forward(request, response);
			}
			else {
				// Comprobamos si el nombre de usuario y la contraseña son correctas
				usuarioVO user = new usuarioVO(usuario, pass);
				int result = new usuarioDAO().validateUser(user);
				switch(result) {
					case 0:
						// Datos correctos
						sesion.setAttribute("usuario", usuario);
						break;
					case 1:
						// Usuario no existe
						errors.put("usuario", "El usuario no existe");
						break;
					case 2:
						// Contraseña incorrecta
						errors.put("pass", "Contraseña incorrecta");
						break;
				}
				if(!errors.isEmpty()) {
					// Redireccion con errores al jsp
					request.setAttribute("errores", errors);
					request.getRequestDispatcher("iniciarSesion.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("introducirParada.jsp").forward(request, response);
				}
			}
			
		} else {
			// Si ha iniciado sesión, redirigimos directamente a introducirParada.jsp
			System.out.println("Sesión iniciada");
			response.sendRedirect("introducirParada.jsp");
		}
	}
}