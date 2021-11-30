<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" import = "java.util.Map"%>
<!--  <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



<title>Menu Ztreet</title>
</head>

<body>
	<h4>Menu Ztreet</h4>

<a href="/sisinf/menuBizi">Bizi</a>
<a href="/sisinf/menuBus">Autobus</a>
<a href="/sisinf/menuTranvia">Tranvia</a>
<a href="iniciarSesion.jsp">Iniciar sesiÃ³n</a> 
<a href="/sisinf/poblarDB">Poblar</a>
    

</body>



</html>

-->

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Menu Ztreet</title>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
<link href="https://fonts.googleapis.com/css2?family=Jockey+One&display=swap" rel="stylesheet"> 
<link rel="stylesheet" type="text/css" href="estilo.css" media="screen">
<style>
body {
    background-image: 
    linear-gradient(
      rgba(0, 0, 0, 0.25),
      rgba(0, 0, 0, 0.25)
    ),
    url(https://images2.alphacoders.com/545/thumb-1920-545232.jpg);
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

<!-- Iconos de acceso a menu -->
 <div class="centrar">
  <p>
   <a href="/sisinf/menuBizi"><img class="imagenBizi"  src="https://i.ibb.co/7ypg8G8/icono-bici-png.gif"> </a>
   <a href="/sisinf/menuBus"><img class="imagenBus" src="https://i.ibb.co/KDTFDDv/icono-bus-png.png"></a>
   <a href="/sisinf/menuTranvia"><img class="imagenTranvia" src="https://i.ibb.co/nrDkHLQ/icono-tranvia-png.png"></a>  
              
  </p>
 </div>

<!-- <div class="bottomBar">
  <a href="#home" class="active">Home</a>
  <a href="#news">News</a>
  <a href="#contact">Contact</a>
</div> -->
</body>



</html>