const openLoginInfo = () => {
    event.preventDefault();
    const boxForm = document.querySelector('.box-form');
    boxForm.style.display = 'none';
}

const closeLoginInfo = () => {
    event.preventDefault();
    const boxForm = document.querySelector('.box-form');
    boxForm.style.display = 'block';
}