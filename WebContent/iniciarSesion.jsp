<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" import = "java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Iniciar Sesión</title>
</head>
<body>
<%
HttpSession sesion = request.getSession();
if (session.getAttribute("usuario") != null) {
	// Sesión ya iniciada, forward a introducir parada
	request.getRequestDispatcher("/introducirParada").forward(request, response);
}
String errorUsuario = ""; String errorPass = "";
Map<String, String> e = (Map<String, String>) request.getAttribute("errores");
if (e != null) {
	if (e.containsKey("usuario")) errorUsuario = e.get("usuario");
	if (e.containsKey("pass")) errorPass = e.get("pass");
}
%>
	<form method="post" action="/sisinf/introducirParada">
		<input type="text" id="usuario" name="usuario" placeholder="Nombre de Usuario"/>
		<input type="password" id="pass" name="pass" placeholder="Contraseña"/>
		<input type="submit" value="Iniciar Sesión"/>		
	</form>
	<%= errorUsuario%>
	<%= errorPass%>
</body>
</html>