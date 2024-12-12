<!--
Follow me on
------------
Codepen: https://codepen.io/mycnlz/
Dribbble: https://dribbble.com/mycnlz
Pinterest: https://pinterest.com/mycnlz/
-->

<div class="box">
  <!-- Formulario de inicio de sesión -->
  <div class="box-form">
    <div class="box-login-tab"></div>
    <div class="box-login-title">
      <div class="i i-login"></div>
      <h2>USUARIO</h2>

      <!-- Estilos y scripts -->
      <link rel="stylesheet" href="usrebe.css">
    </div>

    <!-- Formulario -->
    <form action="login" method="post">
      <div class="box-login">
        <div class="fieldset-body" id="login_form">

          <!-- Botón más información -->
          <button onclick="openLoginInfo();" class="b b-form i i-more" title="Más Informaciones"></button>

          <!-- Campo de correo -->
          <p class="field">
            <label for="correo">E-MAIL</label>
            <input type="text" id="correo" name="correo" title="Correo" />
            <span id="validaUser" class="i i-warning"></span>
          </p>

          <!-- Campo de contraseña -->
          <p class="field">
            <label for="password">PASSWORD</label>
            <input type="password" id="password" name="password" title="Password" />
            <span id="validaPassword" class="i i-close"></span>
          </p>

          <!-- Botón para iniciar sesión -->
          <input type="submit" id="do_login" value="INICIAR SESION" title="INICIAR SESION" />
        </div>
      </div>
    </form>
  </div>

  <!-- Información adicional -->
  <div class="box-info">
    <p>
      <button onclick="closeLoginInfo();" class="b b-info i i-left" title="Back to Sign In"></button>
    <h3>¿NECESITAS AYUDA?</h3>
    </p>
    <div class="line-wh"></div>

    <!-- Botones de ayuda -->
    <button onclick="" class="b-support" title="Olvidaste tu contraseña?">¿Olvidaste tu contraseña?</button>
    <button onclick="" class="b-support" title="Contact Support">Contactar Soporte</button>
    <div class="line-wh"></div>

    <!-- Botón para crear cuenta -->
    <button onclick="" class="b-cta" title="Sign up now!">CREAR CUENTA</button>
  </div>
</div>