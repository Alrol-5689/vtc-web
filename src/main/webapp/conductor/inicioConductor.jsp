<% 
    System.out.println("Usuario logueado en sesión: " + session.getAttribute("usuario_logueado")); 
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.vtc.modelo.Driver" %>
<%-- PARA ESCRIBIR LÓGICA CON CÓDIGO HTML --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%
    Driver conductor = (Driver) session.getAttribute("usuario_logueado");
    if (conductor == null) {
        response.sendRedirect("loginConductor.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio - Conductor VTC</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inicioConductor.css">

</head>
<body>

<div class="contenedor">
    <h1>Bienvenido, <%= conductor.getNombre() %> 👋</h1>

    <a class="boton" href="registroFacturacion.jsp">Registrar Facturación Diaria</a>
    <a class="boton" href="verComisiones.jsp">Ver Pronóstico de Comisiones</a>
    <a class="boton" href="verNominas.jsp">Ver Nóminas</a>
    <a class="boton" href="${pageContext.request.contextPath}/index.jsp">Cerrar Sesión</a>
    <form action="${pageContext.request.contextPath}/SvEliminarConductor" method="post" style="display:inline;">
        <button class="boton" type="submit">Eliminar cuenta</button>
    </form>
</div>

</body>
</html>