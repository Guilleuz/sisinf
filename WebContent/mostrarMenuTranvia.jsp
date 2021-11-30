<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="estilo.css" media="screen">
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
<link href="https://fonts.googleapis.com/css2?family=Jockey+One&display=swap" rel="stylesheet">
<style>
body {
    background-image: 
    linear-gradient(
      rgba(0, 0, 0, 0.25),
      rgba(0, 0, 0, 0.25)
    ),
    url(https://images.unsplash.com/photo-1612085546117-01da93b3f8e9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80);
} 
</style>
<title>Menú Tranvía</title>
<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("mySidenav").style.borderWidth = "1px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("mySidenav").style.borderWidth = "0px";
}
</script>

<script src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
<script>
$(function() {
	botonEnvio.disabled = true;
    $(".forms").hide();
    let sent1 = document.getElementById("myselect1");
    let sent2 = document.getElementById("myselect2");
    $("#seleccionarSentido").change(function() {
        switch($(this).val()){ 
            case "${sentidos.get(0)}":
            		$("#seleccionParadaOculto").hide();
                $(".forms").hide().parent().find("#sentido1").show();
                $(".forms").parent().find("#sentido2").hide();
                if (sent1.value != "none") {
                	botonEnvio.disabled = false;
                } else {
                	botonEnvio.disabled = true;
                }
                break;
            case "${sentidos.get(1)}":
            		$("#seleccionParadaOculto").hide();
                $(".forms").hide().parent().find("#sentido2").show();
                $(".forms").parent().find("#sentido1").hide();
                if (sent2.value != "none") {
                	botonEnvio.disabled = false;
                } else {
                	botonEnvio.disabled = true;
                }
                break;
        }
    });
    $("#myselect1").change(function() {
    	botonEnvio.disabled = false;
    });
    $("#myselect2").change(function() {
    	botonEnvio.disabled = false;
    });
});
</script>

</head>
<body>
<%
HttpSession sesion = request.getSession();
String opcion = "Iniciar Sesión";
if (session.getAttribute("usuario") != null) {
	// Sesión ya iniciada, la opción será introducir parada
	opcion = "Introducir Parada";
}%>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="/sisinf/menuBus">Autobús</a>
  <a href="/sisinf/menuTranvia">Tranví­a</a>
  <a href="/sisinf/menuBizi">Bizi</a>
  <a href="iniciarSesion.jsp"><%= opcion%></a>
</div>

<div class="navbar">
 <span style="padding-top: 7px;width:20%;font-size:30px;cursor:pointer;float:left;color: white" onclick="openNav()">&nbsp&nbsp&#9776;</span>
 <a href="index.jsp" style="font-family: 'Jockey One', sans-serif;text-decoration:none;width: 60%;margin: 0 auto;text-align: center;float: left;font-size: 40px;font-weight: bold;color: white">ZTREET</a>
</div>
	<div class="centrar">
	<h3>Paradas de Tranvía</h3>
    <form action="/sisinf/paradaTranvia" method="get">

        <select id="seleccionarSentido" name="sentido">
            <option value="none" selected disabled hidden>
                     Seleccione Sentido
                 </option>
             <option value="${sentidos.get(0)}">${sentidos.get(0)}</option>
             <option value="${sentidos.get(1)}">${sentidos.get(1)}</option>
           </select>
           <br><br/>

           <select id="seleccionParadaOculto">
             <option selected="true" disabled="disabled">Seleccione Parada</option>    
           </select>
           
        
           <div id="sentido1" class="forms">
           <select id="myselect1" name="n1">
            <option value="none" selected disabled hidden>
                     Seleccione Parada
                 </option>
                 <c:forEach items="${sentido1}" var="parada">
                    <c:set var="nombre" value="${parada}"/>
                        <option value="${nombre}">${nombre}</option>
                    </c:forEach>
           </select> </div>
           
        
           <div id="sentido2" class="forms">
            <select id="myselect2" name="n2">
             <option value="none" selected disabled hidden>
                      Seleccione Parada
                  </option>
                  <c:forEach items="${sentido2}" var="parada">
                     <c:set var="nombre" value="${parada}"/>
                         <option value="${nombre}">${nombre}</option>
                     </c:forEach>
            </select> </div>

        <br/><br/>          
        <input id="botonEnvio" type="submit" value="Elegir"/>
    </form>
    </div>
</body>
</html>

