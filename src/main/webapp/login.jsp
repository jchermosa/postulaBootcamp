<!DOCTYPE html>
<html lang='es'>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link rel="stylesheet" href="usrebe.css">
  <script src="js/login.js" defer></script>
</head>

<body>
  <div class='box'>
    <div class='box-form'>
      <div class='box-login-tab'></div>
      <div class='box-login-title'>
        <div class='i i-login'></div>
        <h2> USUARIO </h2>
      </div>
      <form action="" method="post">
        <div class='box-login'>
          <div class='fieldset-body' id='login_form'>
            <button onclick="openLoginInfo();" class='b b-form i i-more' title='Mas informacion'></button>
            <p class='field'>
              <label for='correo'>E-MAIL</label>
              <input type='text' id='correo' name='correo' title='Correo' />
              <span id='valida' class='i i-warning'></span>
            </p>
            <p class='field'>
              <label for='password'>PASSWORD</label>
              <input type='password' id='password' name='password' title='Password' />
              <span id='valida' class='i i-close'></span>
            </p>
            <input type='submit' id='do_login' value='INICIAR SESION' title='INICIAR SESION' />
          </div>
        </div>
      </form>
    </div>

    <div class='box-info'>
      <p>
        <button onclick="closeLoginInfo();" class='b b-info i i-left' title='Back to Sign In'></button>
        <h3>Need Help?</h3>
      </p>
      <div class='line-wh'></div>
      <button onclick="" class='b-support' title='Forgot Password?'>Forgot Password?</button>
      <button onclick="" class='b-support' title='Contact Support'>Contact Support</button>
      <div class='line-wh'></div>
      <button onclick="" class='b-cta' title='Sign up now!'>CREATE ACCOUNT</button>
    </div>
  </div>
</body>

</html>
