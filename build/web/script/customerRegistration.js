var loadingInfo_customerRegistration = "                    <div class=\"container-fluid my-5\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

function errorMessage_customerRegistration(message) {
    return `    <div class="modal-header">
                    <div class="d-flex align-items-center">
                        <i class="fa-regular fa-face-frown-open fa-2xl" style="color: #74C0FC;"></i>
                        <h5 class="m-0 ms-3">Error message !</h5>
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

$(document).ready(function () {
    $("#btn-signUp-customerRegistration").click(async function () {
        if (formValidation_customerRegistration()) {
            $("#defModel-content-customerRegistration").html(loadingInfo_customerRegistration);
            $("#defModelCustomerRegistration").modal('show');
            try {
                let responseObj = await RegistrationProcess_customerRegistration();
                if (responseObj.registrationStatus) {
                    $("#defModel-content-customerRegistration").html(responseObj.message);
                    window.location.href = responseObj.redirectUrl;
                } else {
                    $("#defModel-content-customerRegistration").html(errorMessage_customerRegistration(responseObj.message));
                }

            } catch (e) {
                $("#defModel-content-customerRegistration").html(errorMessage_customerRegistration("An error occurred"));
            }
        } else {
            alert('Must complete all fields');
        }
    });
});

function formValidation_customerRegistration() {
    let valid = false;

    let fname = $("#txt-fname-customerRegistration").val();
    let lname = $("#txt-lname-customerRegistration").val();
    let email = $("#txt-email-customerRegistration").val();
    let password = $("#txt-password-customerRegistration").val();
    let mobileNumber = $("#txt-cnt-customerRegistration").val();
    let dob = $("#input-dob-customerRegistration").val();

    if (fname !== '' && fname !== null
            && lname !== '' && lname !== null
            && email !== '' && email !== null
            && password !== '' && password !== null
            && mobileNumber !== '' && mobileNumber !== null
            && dob !== '' && dob !== null) {
        valid = true;
    }

    return valid;

}

async function RegistrationProcess_customerRegistration() {
    return new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-customerRegistration');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "CustomerRegistration",
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
