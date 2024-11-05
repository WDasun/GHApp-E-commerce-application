$(document).ready(function () {
    $('#btn-login').click(function () {

        let formElement = document.getElementById('loginForm');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "EmployeeLogin",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                
                let data = JSON.parse(response);
                
                if (data.redirect) {
                    window.location.href = data.redirectUrl;
                } else {
                    alert(data.message);
                }
            },
            error: function (error) {
                console.log(error);
            }
        });

    });
});