var loadingInfo_customerLogin = "                    <div class=\"container-fluid my-5\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

function errorMessage_customerLogin(message) {
    return `    <div class="modal-header">
                    <div class="d-flex align-items-center">
                        <h5 class="m-0 ms-3">Response !</h5>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <p class="fw-normal fs-5">${message}</p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer ">
                    <button class="btn btn-danger " style="width: 100px; " data-bs-dismiss="modal">Close</button>
                </div>`;
}

async function customerLoginProcess() {
    if (formValidation_customerLogin()) {
        $("#defModel-content-customerLogin").html(loadingInfo_customerLogin);
        $("#defModelCustomerLogin").modal('show');
        
        try {
            let responseObj = await LoginProcess_customerLogin();
            $("#defModel-content-customerLogin").html(errorMessage_customerLogin(responseObj.message));
            if(responseObj.loginStatus){
                window.location.href = responseObj.redirectUrl;
            }
        } catch (e) {
            $("#defModel-content-customerLogin").html(errorMessage_customerLogin("An error occurred"));
            console.log(e);
        }

    }
}

function formValidation_customerLogin() {
    let valid = false;

    let email = $("#email-customerLogin").val();
    let password = $("#password-customerLogin").val();

    if (email !== '' && email !== null
            && password !== '' && password) {
        valid = true;
    }

    return true;

}

async function LoginProcess_customerLogin() {
    return new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-customerLogin');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "CustomerLogin",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                resolve(JSON.parse(response));
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}