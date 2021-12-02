<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Lista Paradas Bus</title>
  <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
  <link href="https://fonts.googleapis.com/css2?family=Jockey+One&display=swap" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="estilo.css" media="screen">
  <style>
    body {
      background-image:
        linear-gradient(rgba(0, 0, 0, 0.25),
          rgba(0, 0, 0, 0.25)),
        url(https://images.unsplash.com/photo-1520105072000-f44fc083e508?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1631&q=80);
    }
  </style>
  <script src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
  <script>
    $(function () {
      botonEnvio.disabled = true;
      $("#seleccionarParada").change(function () {
        if ($(this).val != "none") {
          botonEnvio.disabled = false;
        }
      });
    });

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
    <h3>Paradas del trayecto</h3>
    <h4>Línea: ${linea} - ${sentido}</h4>
    <form action="/sisinf/paradaBus" method="get">
      <select id="seleccionarParada" name="id">
        <option value="none" selected disabled hidden>Seleccione una parada</option>
        <c:forEach items="${paradas}" var="parada">
          <c:set var="ID" value="${parada.getNPoste()}" />
          <c:set var="dir" value="${parada.getDireccion()}" />
          <option value="${ID}">${ID} - ${dir}</option>
        </c:forEach>
      </select>
      <br /><br />
      <input id="botonEnvio" type="submit" value="Elegir" />
    </form>
  </div>
  <div class="bottomBar">
    <a>© 2021 Ztreet, Inc.</a>
  </div>
</body>

</html>