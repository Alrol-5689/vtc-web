<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Bienvenido</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
  <!--<link rel="stylesheet" href="css/estilos.css">-->
</head>
<body>
  <h1>- Mi VTC controller -</h1>
  <br>
  <div class="login-container">
    <div class="button-group">
      <a href="auth/loginConductor.jsp" class="btn">Iniciar<br>sesión</a>
      <a href="conductor/altaConductor.jsp" class="btn">Crear nueva cuenta</a>
    </div>
  </div>
  <footer>
    <a href="loginAdmin.jsp">Acceso administradores</a>
  </footer>
</body>

</html>
