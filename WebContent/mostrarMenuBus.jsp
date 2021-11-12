<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
    src="jquery-ui-1.10.0/tests/jquery-1.9.0.js"></script>
<script src="jquery-ui-1.10.0/ui/jquery-ui.js"></script>
<script>
$(function() {
    $(".forms").hide();
   $('#seleccionarLinea').on('change',function(){
        $("#seleccionSentidoOculto").hide()
        $("seleccionarSentido").show();
        }
    });
});
</script>


</head>
<body>
	<h1>Menú Bus</h1>

	<% 
	String msjError = "";
	String poste = "";
	if (request.getAttribute("error") != null) {
		msjError = "<span>No existe la parada</span>";
		poste = (String) request.getAttribute("poste");
	}
	%>
	<form action="/sisinf/validarPoste" method"get">
		<input type="text" name="id" id="campoID" value="<%= poste%>" placeholder="Introduzca el número de poste"/>
		<input type="submit" value="Buscar">
	</form>
	<%= msjError%>


	<form action="/sisinf/linea" method="get">

        <select id="seleccionarLinea" name="nombre">
        	<option value="none" selected disabled hidden>
                     Seleccione Linea
                 </option>
                 <c:forEach items="${lineas}" var="linea">
                    <c:set var="nombre" value="${linea}"/>
                        <option value="${nombre}">${nombre}</option>
                    </c:forEach>
           </select>
           <br><br/>

    
       <div id="seleccionarSentido" class="forms">
           <select id="myselect1" name="nombre">
            <option value="none" selected disabled hidden>
                     Seleccione Sentido
                 </option>
                 <c:forEach items="${lineas}" var="parada">
                    <c:set var="nombre" value="${parada}"/>
                        <option value="${nombre}">${nombre}</option>
                    </c:forEach>
           </select> </div>

        <br/><br/>          
        <input type="submit" value="Elegir"/>
    </form>

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
 