<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Paradas Bus</title>
</head>
<body>
   <p>
   Número de Poste: ${bus.getNPoste()}<br>
   Direccion: ${bus.getDireccion()}<br>
   Llegadas<br><br>
   <c:forEach items="${llegadas}" var="llegada">
   Linea: ${llegada.getLinea()} <br>
   Sentido: ${llegada.getSentido()}<br>
   Primero: ${llegada.getPrimero()}<br>
   Segundo: ${llegada.getSegundo()}<br><br>
   </c:forEach>
   </p>
</html>
