
const openLoginInfo = () => {
    console.log("hola");
    event.preventDefault();
    const boxForm = document.querySelector('.box-form');
    boxForm.style.display = 'none';
};

const closeLoginInfo = () => {
    event.preventDefault();
    const boxForm = document.querySelector('.box-form');
    boxForm.style.display = 'block';
}

//<div class = 'box-crear-cuenta'>
//    <div class = 'box-form'>
//      <div class = 'box-login-tab'></div>
//      <div class = 'box-login-title'>
//        <div class = 'i i-login'></div>
//        <h2> CREAR CUENTA </h2>
//      </div>
//      <form action = "crearCuenta" method = "post">
//        <div class = 'box-login'>
//          <div class = 'fieldset> body' id = 'login_form'>
//            <button onclick = "openLoginInfo();" class = 'b b-form i i-more' title = 'Mas informacion'> </button>
//            <p class = 'field'>
//              <label for = 'correo'> E-MAIL </label>
//              <input type = 'text' id = 'correo' name = 'correo' title = 'Correo' />
//              <span id = 'valida' class = 'i i-warning'> </span>
//            </p>
//            <p class = 'field'>
//              <label for = 'password'> PASSWORD </label>
//              <input type = 'password' id = 'password' name = 'password' title = 'Password' />
//              <span id = 'valida' class = 'i i-close'> </span>
//            </p>
//            <input type = 'submit' id = 'do_login' value = 'CREAR CUENTA' title = 'CREAR CUENTA' />
//          </div>
//        </div>
//      </form>
//    </div>
//   </div>

const createAccount = () => {
    event.preventDefault();
    const boxCrearCuenta = document.querySelector('.box-crear-cuenta');
    boxCrearCuenta.style.display = 'block';
    const boxInfo = document.querySelector('.box-info');
    boxInfo.style.display = 'none';

}