<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Estaciones Bizis</title>
</head>
<body>
    <form action="/estacionBizi" method="get">
        <select name="id">
            <c:forEach items="${lista}" var="Estacion">
            <c:set var="ID" value="${Estacion.getID()}"/>
            <c:set var="dir" value="${Estacion.getDireccion()}"/>
                <option value="${ID}">${ID} - ${dir}</option>
            </c:forEach>
        </select>
        <br/><br/>          
        <input type="submit" value="Elegir"/>
    </form>
</body>
</html>

