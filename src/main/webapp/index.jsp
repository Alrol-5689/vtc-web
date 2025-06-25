<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - VTC</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <script src="js/login.js" defer></script>
</head>
<body>
    <div class="login-container">
        <h2>Login VTC</h2>
        <form action="login" method="post">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" required>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>

            <label for="tipo">Tipo de usuario:</label>
            <select id="tipo" name="tipo">
                <option value="jefe">Jefe</option>
                <option value="gestor">Gestor</option>
                <option value="conductor">Conductor</option>
            </select>

            <button type="submit">Iniciar sesión</button>
        </form>
    </div>
</body>
</html>
