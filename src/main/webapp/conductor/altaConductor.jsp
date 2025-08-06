<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Alta Conductor</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/altaConductor.css">
</head>
<body>
    <!-- dñjgkle´krgjéprogéprojéporje´prog-->
    <div class="form-container">
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <a href="../index.jsp" class="back-link">← Volver</a>
            <a href="${pageContext.request.contextPath}/auth/loginConductor.jsp" class="btn-link">Iniciar Sesión →</a>
        </div>
        <h2>Alta Nuevo Conductor</h2>
        <form action="${pageContext.request.contextPath}/SvAltaConductor" method="post">
            <div class="form-group">
                <label for="nick">Nick:</label>
                <input type="text" id="nick" name="nick" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirm_password">Repite Contraseña:</label>
                <input type="password" id="confirm_password" name="confirm_password" required>
            </div>
            <div class="form-group">
                <label for="dni">DNI:</label>
                <input type="text" id="dni" name="dni" >
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre">
            </div>
            <div class="form-group">
                <label for="apellido1">Primer Apellido:</label>
                <input type="text" id="apellido1" name="apellido1">
            </div>
            <div class="form-group">
                <label for="apellido2">Segundo Apellido:</label>
                <input type="text" id="apellido2" name="apellido2">
            </div>
            <div class="form-group">
                <label for="telefono">Teléfono:</label>
                <input type="text" id="telefono" name="telefono">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email">
            </div>
            <div class="form-group">
                <button type="submit">Registrar Conductor</button>
            </div>
        </form>
    </div>
    <script>
        document.querySelector("form").addEventListener("submit", function(e) {
            const password = document.getElementById("password").value;
            const confirm = document.getElementById("confirm_password").value;
            if (password !== confirm) {
                alert("Las contraseñas no coinciden.");
                e.preventDefault(); // bloquea el envío del formulario
            }
        });
    </script>
</body>
</html>