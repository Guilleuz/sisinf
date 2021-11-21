<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Paradas tranvia</title>

<script src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
<script>
$(function() {
    $(".forms").hide();
    $("#seleccionarSentido").change(function() {
        switch($(this).val()){ 
            case "${sentidos.get(0)}":
            		$("#seleccionParadaOculto").hide();
                $(".forms").hide().parent().find("#sentido1").show();
                (".forms").parent().find("#sentido2").hide();
                break;
            case "${sentidos.get(1)}":
            		$("#seleccionParadaOculto").hide();
                $(".forms").hide().parent().find("#sentido2").show();
                $(".forms").parent().find("#sentido1").hide();
                break;
        }
    });
});
</script>

</head>
<body>
    <form action="/sisinf/paradaTranvia" method="get">

        <select id="seleccionarSentido" name="sentido">
            <option value="none" selected disabled hidden>
                     Seleccione Sentido
                 </option>
             <option value="${sentidos.get(0)}">${sentidos.get(0)}</option>
             <option value="${sentidos.get(1)}">${sentidos.get(1)}</option>
           </select>
           <br><br/>

           <select id="seleccionParadaOculto">
             <option selected="true" disabled="disabled">Seleccione Parada</option>    
           </select>
           
        
           <div id="sentido1" class="forms">
           <select id="myselect1" name="n1">
            <option value="none" selected disabled hidden>
                     Seleccione Parada
                 </option>
                 <c:forEach items="${sentido1}" var="parada">
                    <c:set var="nombre" value="${parada}"/>
                        <option value="${nombre}">${nombre}</option>
                    </c:forEach>
           </select> </div>
           
        
           <div id="sentido2" class="forms">
            <select id="myselect2" name="n2">
             <option value="none" selected disabled hidden>
                      Seleccione Parada
                  </option>
                  <c:forEach items="${sentido2}" var="parada">
                     <c:set var="nombre" value="${parada}"/>
                         <option value="${nombre}">${nombre}</option>
                     </c:forEach>
            </select> </div>

        <br/><br/>          
        <input type="submit" value="Elegir"/>
    </form>
</body>
</html>

