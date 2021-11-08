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
    <p>
        ID: ${parada.getID()}<br/><br/> 
        Nombre: ${parada.getNombre()}<br/><br/> 
        Sentido: ${parada.getSentido()}<br/><br/>
        Direccion: ${parada.getDireccion()}<br/><br/>
        Llegada 1: ${llegada.getPrimero()}<br><br>
        Llegada 2: ${llegada.getSegundo()}<br><br>  
    </p>
    
</body>
</html>
