<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Bienvenido - VTC</title>
</head>
<body>
    <form method="post" action="login">
        <label>Nick:</label>
        <input type="text" name="usuario" required />
        <label>Contraseña:</label>
        <input type="password" name="password" required />
        <button type="submit">Iniciar sesión</button>
    </form>
</body>
</html>