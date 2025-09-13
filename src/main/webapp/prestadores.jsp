<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Prestadores de Salud</title>
</head>
<body>
<h1>Prestadores de Salud</h1>
<form method="post" action="prestadores">
    <input type="text" name="nombre" placeholder="Nombre" required />
    <input type="text" name="direccion" placeholder="Dirección" required />
    <select name="tipo" required>
        <option value="LABORATORIO">Laboratorio</option>
        <option value="HOSPITAL">Hospital</option>
    </select>
    <button type="submit">Agregar</button>
</form>

<form method="get" action="prestadores">
    <input type="text" name="nombre" placeholder="Buscar por nombre" />
    <button type="submit">Buscar</button>
</form>

<ul>
<c:forEach var="p" items="${prestadores}">
    <li>${p.id} - ${p.nombre} - ${p.direccion} - ${p.tipo}</li>
</c:forEach>
</ul>
</body>
</html>
