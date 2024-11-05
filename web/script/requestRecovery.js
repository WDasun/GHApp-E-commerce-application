function requestRecovery() {
    let email = $('#email_requestRecovery').val();
    $('#div_requestRecivery').html(` <h3 class="card-title text-center">Done !</h3>`);
    $.ajax({
        type: "POST",
        url: "GenerateRecoveryCode",
        data: {
            "customerEmail": email
        },
        success: function (response) {

        },
        error: function (error) {

        }
    });
}


