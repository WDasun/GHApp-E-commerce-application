let email;
let recoveryCode;
let newPassword;
async function request_recovery() {
    email = $('#email_recovery').val();
    recoveryCode = $('#recoveryCode_recovery').val();
    newPassword = $('#newPassword_recovery').val();
    $('#passwordReset_modal_content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center fs-5 m-0">Wait ...</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    $('#passwordReset_modal').modal('show');
    $('#passwordReset_modal_content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center fs-5 m-0">${await resetPasswordSRVR_recovery()}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    
}

async function resetPasswordSRVR_recovery() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "ResetPassword",
            data: {
                "customerEmail" :email,
                "recoveryCode" :recoveryCode,
                "newPassword" :newPassword
            },
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}
