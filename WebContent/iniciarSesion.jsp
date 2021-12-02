<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8" import = "java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Iniciar Sesión</title>
	<link rel="stylesheet" type="text/css" href="estilo.css" media="screen">
	<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
	<link href="https://fonts.googleapis.com/css2?family=Jockey+One&display=swap" rel="stylesheet">

	<style>
		body {
			background-image:
				linear-gradient(rgba(0, 0, 0, 0.25),
					rgba(0, 0, 0, 0.25)),
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
	if (session.getAttribute("usuario") != null) {
		// Sesión ya iniciada, forward a introducir parada
		request.getRequestDispatcher("/introducirParada").forward(request, response);
	}
	String errorUsuario = ""; String errorPass = ""; String userName ="";
	Map<String, String> e = (Map<String, String>) request.getAttribute("errores");
	if (e != null) {
		if (e.containsKey("usuario")) errorUsuario = e.get("usuario") + "<br>";
		if (e.containsKey("pass")) errorPass = e.get("pass") + "<br>";
		userName = (String) request.getAttribute("userName");
	}

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
		<a href="iniciarSesion.jsp">
			<%= opcion%>
		</a>
	</div>

	<div class="navbar">
		<span style="padding-top: 7px;width:20%;font-size:30px;cursor:pointer;float:left;color: white"
			onclick="openNav()">&nbsp&nbsp&#9776;</span>
		<a href="index.jsp" class="logo">ZTREET</a>
	</div>

	<div class="centrar caja">
		<h3>Iniciar Sesión</h3>
		<form method="post" action="/sisinf/introducirParada">
			<input type="text" id="usuario" name="usuario" placeholder="Nombre de Usuario" value="<%= userName%>" />
			<br>
			<span class="textoError">
				<%= errorUsuario%>
			</span>
			<br>
			<input type="password" id="pass" name="pass" placeholder="Contraseña" />
			<br>
			<span class="textoError">
				<%= errorPass%>
			</span>
			<br>
			<input type="submit" value="Iniciar Sesión" />
		</form>
	</div>
	<div class="bottomBar">
		<a>© 2021 Ztreet, Inc.</a>
	</div>
</body>

</html>