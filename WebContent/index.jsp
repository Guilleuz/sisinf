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
      background-color: #4d4848;
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
    <a href="iniciarSesion.jsp">
      <%= opcion%>
    </a>
  </div>

  <div class="navbar">
    <span style="padding-top: 7px;width:20%;font-size:30px;cursor:pointer;float:left;color: white"
      onclick="openNav()">&nbsp&nbsp&#9776;</span>
      <a href="index.jsp" class="logo">ZTREET</a>
  </div>

  <!-- Iconos de acceso a menu -->
  <div class="imgMenu" style="text-align: center;">
    <a href="/sisinf/menuBizi"><img
        src="https://images.unsplash.com/photo-1572417227948-70bbb81e814c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NzN8fGJpa2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"></a><a
      href="/sisinf/menuBus"><img
        src="https://images.unsplash.com/photo-1564694202883-46e7448c1b26?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8YnVzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"></a><a
      href="/sisinf/menuTranvia"><img
        src="https://images.unsplash.com/photo-1612085546117-01da93b3f8e9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"></a>
  </div>
  <div class="bottomBar">
    <a>© 2021 Ztreet, Inc.</a>
  </div>
</body>

</html>