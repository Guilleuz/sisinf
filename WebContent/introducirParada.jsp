<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
  <link href="https://fonts.googleapis.com/css2?family=Jockey+One&display=swap" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="estilo.css" media="screen">
  <title>Introducir Parada</title>
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
  String mensaje = "";
  String dir = "";
  String poste = "";
  String errorPoste = "";
  String errorDir = "";
  double longitud = 0.0;
  double latitud = 0.0;
  if (request.getAttribute("errorPoste") != null || request.getAttribute("errorDir") != null) {
    if (request.getAttribute("errorPoste") != null)
      errorPoste = (String) request.getAttribute("errorPoste") + "<br>";
    if (request.getAttribute("errorDir") != null)
      errorDir = (String) request.getAttribute("errorDir") + "<br>";
    poste = (String) request.getAttribute("poste");
    dir = (String) request.getAttribute("direccion");
    String lat = (String) request.getAttribute("latitud");
    String lon = (String) request.getAttribute("longitud");
    if (lat != "") latitud = Double.valueOf(lat);
    if (lon != "") longitud = Double.valueOf(lon);
  }
  else if (request.getAttribute("mensaje") != null) mensaje = (String) request.getAttribute("mensaje");
  
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
    <h3>Introducir Parada de Autobús</h3>
    <form method="post" action="/sisinf/validarParada">
      <input type="text" name="ID" placeholder="Número de poste" value="<%= poste%>" /><br>
      <span class="textoError">
        <%= errorPoste%>
      </span>
      <input type="text" name="direccion" placeholder="Direccion" value="<%= dir%>" /><br>
      <span class="textoError">
        <%= errorDir%>
      </span>
      <input type="number" step="any" name="latitud" placeholder="latitud" value="<%= latitud%>" /><br>
      <input type="number" step="any" name="longitud" placeholder="longitud" value="<%= longitud%>" /><br>
      <input type="submit" value="Añadir" />
    </form>
    <span class="textoError">
      <%= mensaje%>
    </span>
  </div>
  <br>
  <div class="bottomBar">
    <a>© 2021 Ztreet, Inc.</a>
  </div>
</body>

</html>