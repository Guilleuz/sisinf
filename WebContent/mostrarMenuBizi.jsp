<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <link rel="stylesheet" type="text/css" href="estilo.css" media="screen">
  <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
  <link href="https://fonts.googleapis.com/css2?family=Jockey+One&display=swap" rel="stylesheet">
  <style>
    body {
      background-image:
        linear-gradient(rgba(0, 0, 0, 0.25),
          rgba(0, 0, 0, 0.25)),
        url(https://images.unsplash.com/photo-1559348349-86f1f65817fe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80);
    }
  </style>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Menú Bizi</title>
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
    $(function () {
      botonEnvio.disabled = true;
      $("#seleccionarEstacion").change(function () {
        console.log($(this).val);
        if ($(this).val = "none") {
          botonEnvio.disabled = false;
        }
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
    <a href="/sisinf/menuTranvia">Tranvía</a>
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
    <h3>Estaciones Bizi</h3>
    <form action="/sisinf/estacionBizi" method="get">
      <select id="seleccionarEstacion" name="id">
        <option value="none" selected disabled hidden>
          Seleccione Estacion
        </option>
        <c:forEach items="${lista}" var="Estacion">
          <c:set var="ID" value="${Estacion.getID()}" />
          <c:set var="dir" value="${Estacion.getDireccion()}" />
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