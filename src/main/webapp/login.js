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

    // Submit button
    $('.btn').on('click', function (e) {
        e.preventDefault();
        var finp = $(this).closest('form').find('.input-field');

        if (finp.val().trim() !== '') {
            $(this).addClass('active');
        } else {
            finp.parent('.f_row').addClass('shake');
        }

        setTimeout(function () {
            finp.val('');
            $('.f_row').removeClass('shake focus');
            $('.btn').removeClass('active');
        }, 2000);
    });
});
