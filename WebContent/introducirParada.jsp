<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" import = "java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Introducir Parada</title>
</head>
<body>
<% 
String mensaje = "";
String dir = "";
String poste = "";
double longitud = 0.0;
double latitud = 0.0;
if (request.getAttribute("error") != null) {
	mensaje = (String) request.getAttribute("error");
	poste = (String) request.getAttribute("poste");
	dir = (String) request.getAttribute("direccion");
	String lat = (String) request.getAttribute("latitud");
	String lon = (String) request.getAttribute("longitud");
	if (lat != "") latitud = Double.valueOf(lat);
	if (lon != "") longitud = Double.valueOf(lon);
}
else if (request.getAttribute("mensaje") != null) mensaje = (String) request.getAttribute("mensaje");
%>
<form method="post" action="/sisinf/validarParada">
    <input type="text" name="ID" placeholder="Número de poste" value="<%= poste%>"/>
    <input type="text" name="direccion" placeholder="Direccion" value="<%= dir%>"/>
    <input type="number" name="latitud" placeholder="latitud" value="<%= latitud%>"/>
    <input type="number" name="longitud" placeholder="longitud" value="<%= longitud%>"/>
    <input type="submit" value="Añadir"/>
 </form>
<br>
<%= mensaje%>
</body>
</html>