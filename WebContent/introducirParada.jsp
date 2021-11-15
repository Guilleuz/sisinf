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
<form method="post" action="/validarPoste">
    <input type="text" name="ID" placeholder="Número de poste"/>
    <input type="text" name="direccion" placeholder="Direccion"/>
    <input type="number" name="latitud" placeholder="latitud"/>
    <input type="number" name="longitud" placeholder="longitud"/>
    <input type="submit" value="Añadir"/>
 </form>
</body>
</html>