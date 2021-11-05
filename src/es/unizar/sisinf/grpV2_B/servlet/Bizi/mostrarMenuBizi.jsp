<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Estaciones Bizis</title>
</head>
<body>
    <form action="list" method="get">
        <select name="id">
            <c:forEach items="${lista}" var="Estacion">
                <option value="${Estacion.id}">${Estacion.id - Estacion.direccion}</option>
            </c:forEach>
        </select>
        <br/><br/>          
        <input type="submit" value="Elegir"/>
    </form>
</body>
</html>

