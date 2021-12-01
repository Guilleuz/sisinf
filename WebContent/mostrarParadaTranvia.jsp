<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
<link href="https://fonts.googleapis.com/css2?family=Jockey+One&display=swap" rel="stylesheet"> 
<link rel="stylesheet" type="text/css" href="estilo.css" media="screen">
<title>Parada de Tranvía</title>
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



<script>

const progressbar =  document.getElementsByClassName("progress");

const changeProgress = (progress) => {
	var tiempo = ${llegada.getPrimero()};
    var tiempoMax = 20 ;
    var porcentaje;
    if (tiempo > tiempoMax) {
    	porcentaje = 0;
    }
    else { porcentaje = ((tiempoMax - tiempo) / tiempoMax) * 100;}
	if (porcentaje > 100) {
		porcentaje = 100;
	}
	console.log(porcentaje);
  progressbar[0].style.width = porcentaje + "%";
};

setTimeout(() => changeProgress(4), 1000);
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


<div class="centrar caja">


  <center> <h2> ${parada.getNombre()} - ${parada.getSentido()}</h2> </center>
   <br><br>
        <div class="progress-container">
  <div class="progress" id="prueba"></div>
      </div>
    <br/><br/> 
     Tiempo restante: ${llegada.getPrimero()} minutos
   <br/><br/> 
        
 </div>



<div class="bottomBar">
 <a>© 2021 Ztreet, Inc.</a>
</div>  
</body>
</html>
