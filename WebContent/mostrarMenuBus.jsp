<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
<script>
$(function() {
    $(".forms").hide();
	let data = '${sentidosLinea}'
	let json = JSON.parse(data);
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
 