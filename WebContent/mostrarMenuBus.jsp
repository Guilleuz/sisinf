<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
    url(https://images.unsplash.com/photo-1520105072000-f44fc083e508?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1631&q=80);
} 
</style>

<title>Menú Autobús</title>
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
    $(".forms").hide();
	let data = '${sentidosLinea}'
	let json = JSON.parse(data);
	botonEnvio.disabled = true;
	let sel = document.getElementById("seleccionarSentido");
	let to_hide = sel[2];
	$("#seleccionarLinea").change(function() {
		let linea = $(this).val();
		let s1 = json[linea][0];
		let s2 = json[linea][1];
		
        document.getElementById('sentido1').value = s1;
        document.getElementById('sentido2').value = s2;
        document.getElementById('sentido1').innerHTML = s1;
        document.getElementById('sentido2').innerHTML = s2;
        $("#seleccionarSentidoDivOculto").hide();
        $(".forms").hide().parent().find("#seleccionarSentidoDiv").show();
        if (sel.value != "none") {
        	botonEnvio.disabled = false;
        }
        if (s1 == s2 || s2 == "Sentido único") {
        	to_hide.setAttribute('hidden', 'hidden');
        }
        else to_hide.removeAttribute('hidden');
    });
	$("#seleccionarSentido").change(function() {
		botonEnvio.disabled = false;
	});
});
</script>


</head>
<body>
     <div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="/sisinf/menuBus">Autobús</a>
    <a href="/sisinf/menuTranvia">Tranvía</a>
    <a href="/sisinf/menuBizi">Bizi</a>
    <a href="iniciarSesion.jsp">Iniciar Sesión</a>
    </div>

    <div class="navbar">
    <span style="padding-top: 7px;width:20%;font-size:30px;cursor:pointer;float:left;color: white" onclick="openNav()">&nbsp&nbsp&#9776;</span>
    <a href="index.html" style="font-family: 'Jockey One', sans-serif;text-decoration:none;width: 60%;margin: 0 auto;text-align: center;float: left;font-size: 40px;font-weight: bold;color: white">ZTREET</a>
    </div>
	<% 
	String msjError = "";
	String poste = "";
	if (request.getAttribute("error") != null) {
		msjError = "<span>No existe la parada</span>";
		poste = (String) request.getAttribute("poste");
	}
	%>
	<div class="centrar">
	<form action="/sisinf/validarPoste" method"get">
		<input type="text" name="id" id="campoID" value="<%= poste%>" placeholder="Introduzca el número de poste"/>
		<input type="submit" value="Buscar">
	</form>
	<span style="color: white;font-weight: bold"><%= msjError%></span>
    <br><br/>
     
	<form action="/sisinf/linea" method="get">

        <select id="seleccionarLinea" name="linea">
        	<option value="none" selected disabled hidden>
                     Seleccione Linea
                 </option>
                 <c:forEach items="${lineas}" var="linea">
                    <c:set var="nombre" value="${linea}"/>
                        <option value="${nombre}">${nombre}</option>
                    </c:forEach>
           </select>
        <br><br>
        <div id="seleccionarSentidoDivOculto">
            <select id="seleccionSentidoOculto">
             <option selected="true" disabled="disabled">Seleccione Sentido</option>    
           </select>
           </div>
           
           
       <div id="seleccionarSentidoDiv" class="forms">
           <select id="seleccionarSentido" name="sentido">
            <option value="none" selected disabled hidden>
                     Seleccione Sentido
                 </option>
            <option id="sentido1" value=""></option>
            <option id="sentido2" value=""></option>
           </select> </div>

        <br/><br/>          
        <input id="botonEnvio" type="submit" value="Elegir"/>
    </form>
	</div>
</body>
</html>

<!-- 
Formulario
	Introducir nº de poste
Enviar


Formulario
	Eleccion de linea
	Eleccion de sentido -> valor dependiente de la linea
Enviar
 -->
 