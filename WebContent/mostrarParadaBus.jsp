<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Parada de Autobús</title>
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
    const progressbar = document.getElementsByClassName("progress");
    let data = '${llegadasJSON}'
    let json = JSON.parse(data);

    const changeProgress = (progress) => {
      console.log(progressbar.length);
      for (let i = 0; i < progressbar.length; i++) {
        console.log(json["result"][i]);
        var tiempo = json["result"][i];
        var tiempoMax = 20;
        var porcentaje;

        if (tiempo > tiempoMax) { porcentaje = 0; }
        else { porcentaje = ((tiempoMax - tiempo) / tiempoMax) * 100; }

        if (porcentaje > 100) { porcentaje = 100; }

        console.log(porcentaje);
        progressbar[i].style.width = porcentaje + "%";
      }
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
    <a href="iniciarSesion.jsp">
      <%= opcion%>
    </a>
  </div>

  <div class="navbar">
    <span style="padding-top: 7px;width:20%;font-size:30px;cursor:pointer;float:left;color: white"
      onclick="openNav()">&nbsp&nbsp&#9776;</span>
    <a href="index.jsp" class="logo">ZTREET</a>
  </div>
  <div class="centrar caja" style="color: white;">
    <p>
      ${bus.getDireccion()}<br><br>
      <span style="font-weight: bold;"> Llegadas</span><br>
      <c:forEach items="${llegadas}" var="llegada">
        <br>
        <div class="progress-container">
          <div class="progress" id="prueba"></div>
        </div>
        <br>
        ${llegada.getLinea()} - ${llegada.getSentido()}<br>
        Tiempo restante: ${llegada.getPrimero()} minutos <br>
        <span style="font-size: 20px;"> Siguiente en : ${llegada.getSegundo()} minutos</span><br><br>
      </c:forEach>
    </p>
  </div>
  <div class="bottomBar">
    <a>© 2021 Ztreet, Inc.</a>
  </div>
</body>

</html>