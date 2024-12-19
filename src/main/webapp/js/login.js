
const openLoginInfo = (event) => {
    console.log("hola");
    event.preventDefault();
    const boxForm = document.querySelector('.box-form');
    boxForm.style.display = 'none';
};

const closeLoginInfo = (event) => {
    event.preventDefault();
    const boxForm = document.querySelector('.box-form');
    boxForm.style.display = 'block';
}

const createAccount = (event) => {
    event.preventDefault();
    const boxCrearCuenta = document.querySelector('.box-crear-cuenta');
    boxCrearCuenta.style.display = 'block';
    const boxInfo = document.querySelector('.box-info');
    boxInfo.style.display = 'none';
}


const forgotPassword = (event) => {
    event.preventDefault();
    const boxInfo = document.querySelector('.box-info');
    boxInfo.style.display = 'none';
    const boxRecovery = document.querySelector('.box-recovery'); // Asegúrate de que el formulario tenga la clase 'box-recovery'
    boxRecovery.style.display = 'block';
}
