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
    <form action="/sisinf/paradaBus" method="get">
        <select name="id">
            <c:forEach items="${paradas}" var="parada">
            <c:set var="ID" value="${parada.getNPoste()}"/>
            <c:set var="dir" value="${parada.getDireccion()}"/>
                <option value="${ID}">${ID} - ${dir}</option>
            </c:forEach>
        </select>
        <br/><br/>          
        <input type="submit" value="Elegir"/>
    </form>
</body>
</html>

