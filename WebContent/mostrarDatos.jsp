<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="mostrarDatos.css" media="screen">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estación Bizi</title>
</head>
<body>

<div class="centrar">
    ${estacion.getDireccion()}<br/><br/> 
        
        <p style="text-align: center;">
            Capacidad: ${estacion.getCapacidad()}<br/><br/> 
            Bicis Disponibles: ${estacion.getBicis()}<br/><br/>
         </p>
    
 </div>
</body>
</html>

