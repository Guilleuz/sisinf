<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Prueba</h1>
	<form action="/sisinf/validarPoste" method"get">
		<input type="text" name="id" id="campoID" placeholder="Introduzca el número de poste"/>
		<input type="submit" value="Buscar">
	</form>
	<form action="/sisinf/linea" method="get">

      <select id="myselect1" name="nombre">
        	<option value="none" selected disabled hidden>
                     Seleccione Linea
                 </option>
                 <c:forEach items="${lineas}" var="linea">
                    <c:set var="nombre" value="${linea}"/>
                        <option value="${nombre}">${nombre}</option>
                    </c:forEach>
           </select> </div>
           <br><br/>

           <select id="seleccionSentidoOculto">
             <option selected="true" disabled="disabled">Seleccione Sentido</option>    
           </select>
           
        <% 

		%>

       <div id="sentido1" class="forms">
           <select id="myselect1" name="nombre">
            <option value="none" selected disabled hidden>
                     Seleccione Sentido
                 </option>
                 <c:forEach items="${sentido1}" var="parada">
                    <c:set var="nombre" value="${parada}"/>
                        <option value="${nombre}">${nombre}</option>
                    </c:forEach>
           </select> </div>

        <br/><br/>          
        <input type="submit" value="Elegir"/>
    </form>
</body>


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
 