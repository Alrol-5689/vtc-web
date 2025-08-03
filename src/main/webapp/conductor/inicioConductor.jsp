<% 
    System.out.println("Usuario logueado en sesión: " + session.getAttribute("usuario_logueado")); 
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.vtc.modelo.Driver" %>
<%
    Driver conductor = (Driver) session.getAttribute("usuario_logueado");
    if (conductor == null) {
        response.sendRedirect("loginConductor.jsp");
        return;
    }
%>
<%-- PARA ESCRIBIR LÓGICA CON CÓDIGO HTML --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio - Conductor VTC</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inicioConductor.css">

</head>
<body>
<div class="layout">

    <!-- SIDEBAR -->
    <nav class="sidebar">
        <h2>Menú</h2>
        <ul>
            <li><a href="registroFacturacion.jsp">Registro Diario</a></li>
            <li><a href="verComisiones.jsp">Pronóstico Comisiones</a></li>
            <li><a href="verNominas.jsp">Nóminas</a></li>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Cerrar Sesión</a></li>
        </ul>
    </nav>

    <!-- MAIN CONTENT -->
    <div class="main-content">
        <!-- TOP HEADER -->
        <header class="top-header">
            <h1>Bienvenido, <%= conductor.getNombre() %> 👋</h1>
        </header>

        <!-- DASHBOARD CARDS -->
        <section class="cards">
            <div class="card">
                <a href="registroFacturacion.jsp">Registrar Facturación Diaria</a>
            </div>
            <div class="card">
                <a href="verComisiones.jsp">Ver Pronóstico de Comisiones</a>
            </div>
            <div class="card">
                <a href="verNominas.jsp">Ver Nóminas</a>
            </div>
            <div class="card">
                <form action="${pageContext.request.contextPath}/SvEliminarConductor" method="post">
                    <button type="submit">Eliminar cuenta</button>
                </form>
            </div>
        </section>
    </div>

</div>
</body>
</html>