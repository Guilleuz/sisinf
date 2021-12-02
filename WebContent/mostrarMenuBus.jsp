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
                linear-gradient(rgba(0, 0, 0, 0.25),
                    rgba(0, 0, 0, 0.25)),
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
        $(function () {
            $(".forms").hide();
            let data = '${sentidosLinea}'
            let json = JSON.parse(data);
            botonEnvio.disabled = true;
            let sel = document.getElementById("seleccionarSentido");
            let to_hide = sel[2];
            $("#seleccionarLinea").change(function () {
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
            $("#seleccionarSentido").change(function () {
                botonEnvio.disabled = false;
            });
        });
    </script>
</head>

<body>
    <% HttpSession sesion=request.getSession(); String opcion="Iniciar Sesión" ; if (session.getAttribute("usuario")
        !=null) { // Sesión ya iniciada, la opción será introducir parada opcion="Introducir Parada" ; }%>
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

        <% String msjError="" ; String poste="" ; if (request.getAttribute("error") !=null) {
            msjError="<span>No existe la parada</span>" ; poste=(String) request.getAttribute("poste"); } %>
            <div class="centrar caja">
                <h3>Búsqueda por Poste</h3>
                <form action="/sisinf/validarPoste" method"get">
                    <input style="width:70%;padding-left:" type="text" name="id" id="campoID" value="<%= poste%>"
                        placeholder="Número de poste" />
                    <input type="submit" style="" value="Buscar">
                </form>
                <span class="textoError" style="padding-left:10px">
                    <%= msjError%>
                </span>
                <br>

                <h3>Búsqueda por Línea</h3>
                <form action="/sisinf/linea" method="get">

                    <select id="seleccionarLinea" name="linea">
                        <option value="none" selected disabled hidden>
                            Seleccione Linea
                        </option>
                        <c:forEach items="${lineas}" var="linea">
                            <c:set var="nombre" value="${linea}" />
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
                        </select>
                    </div>

                    <br />
                    <input id="botonEnvio" type="submit" value="Elegir" />
                </form>
            </div>
            <br>
            <div class="bottomBar">
                <a>© 2021 Ztreet, Inc.</a>
            </div>
</body>

</html>