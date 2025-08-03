<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/altaConductor.css">
</head>
<body>
  <div class="form-container">
    <a href="../index.jsp" class="back-link">← Volver</a>
    <h2>Iniciar Sesión</h2>
    <form method="post" action="${pageContext.request.contextPath}/SvLoginConductor">
      <div class="form-group">
        <label for="usuario">Nick:</label>
        <input type="text" id="usuario" name="usuario" required>
      </div>
      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>
      </div>
      <div class="form-group">
        <button type="submit">Entrar</button>
      </div>
    </form>
  </div>
</body>
</html>