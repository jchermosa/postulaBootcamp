<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css">
    <link rel="icon" href="./imagenes/roshkaicon.ico">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/login_effects.js"></script>
    <title>Login</title>
</head>

<body>

<div class="container">
    <div class="formBox level-login">
        <div class="box boxShaddow"></div>

            <form action="resetPassword" method="post" class="form">
                <p>Reestablezca su contraseña</p><br><br>
                <div class="f_row last">
                    <label>Contraseña nueva</label>
                    <input type="password" id="nueva_contrasena" name="nueva_contrasena" title="Nueva Contraseña" class="input-field" required>

                    <c:if test="${not empty errorMessage && errorType == 'confirmarContrasena'}">
                        <span class="error-message">${errorMessage}</span>
                    </c:if>
                    <u></u>
                </div>

                <br><br><br>
                <div class="f_row last">
                    <label>Confirmar contraseña</label>
                    <input type="password" id="confirmar_contrasena" name="confirmar_contrasena" title="Confirmar Contraseña" class="input-field" required>
                    <u></u>
                </div>
                <br><br><br>
                <button type="submit" id="do_recovery" value="RECUPERAR CONTRASEÑA" title="RECUPERAR CONTRASEÑA" class="btn" ><span>Resetear contraseña</span><u></u></button>
            </form>
        </div>

    </div>
</div>

</body>

</html>