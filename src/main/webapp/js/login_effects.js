$(document).ready(function () {
    var inP = $('.input-field');

    // Focus/Blur logic
    inP.on('blur', function () {
        if (!this.value) {
            $(this).parent('.f_row').removeClass('focus');
        } else {
            $(this).parent('.f_row').addClass('focus');
        }
    }).on('focus', function () {
        $(this).parent('.f_row').addClass('focus');
        $('.btn').removeClass('active');
        $('.f_row').removeClass('shake');
    });

    // Forgot Password button
    $('.resetTag').click(function (e) {
        e.preventDefault();
        $('.formBox').addClass('level-forget').removeClass('level-reg');
    });

    // Back button
    $('.back').click(function (e) {
        e.preventDefault();
        $('.formBox').removeClass('level-forget').addClass('level-login');
    });

    // Register button
    $('.regTag').click(function (e) {
        e.preventDefault();
        $('.formBox').toggleClass('level-login level-reg');
    });

    // Botón de envío
    $('.btn').on('click', function (e) {
        var finp = $(this).closest('form').find('.input-field');
        var isValid = true;

        // Validar campos requeridos
        finp.each(function () {
            if ($(this).val().trim() === '') {
                isValid = false;
                $(this).parent('.f_row').addClass('shake');
            } else {
                $(this).parent('.f_row').removeClass('shake');
            }
        });

        // Si la validación es exitosa, permitir el envío al servlet
        if (isValid) {
            $(this).addClass('active');
            setTimeout(() => {
                return true; // Permite el envío al servlet después del retardo
            }, 1000); // Retardo de 1 segundo
        } else {
            e.preventDefault(); // Evita el envío si hay campos inválidos
        }

        // Limpiar los estilos después de un tiempo (opcional)
        setTimeout(function () {
            $('.f_row').removeClass('shake focus');
            $('.btn').removeClass('active');
        }, 2000);
    });


});
