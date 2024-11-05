var loadingInfoSystemUser = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

$(document).ready(function () {
    $('#btn-systemUser').click(function () {
        $('#div-rightmenu').html(loadingInfoSystemUser);
        setContent_systemUserManage();
    });
});

// Adding div content and load system user account list

async function setContent_systemUserManage() {
    $('#div-rightmenu').html(`
        <div class="container-fluid p-0" id="div-systemUserManage">
            <!-- Head -->
            <div class="row m-0 mb-3">
                <div class="col p-0">
                    <div class="row shadow div-leftmenu-head m-0">
                        <div class="col d-flex align-items-center p-0 ps-3">
                            <p class="txt-rightmenu-description m-0">System user manage</p>
                        </div>
                        <div class="col d-flex align-items-center justify-content-end p-0 pe-2">
                            <button class="btn-categoryList-refresh border border-1 btn btn-light me-3 p-0 py-2 px-3" id="refresh-systemUserManage">
                                <i class="fa-solid fa-arrows-rotate fa-lg"></i>
                            </button>
                            <button class="btn btn-primary btn-leftmenu-head me-2" type="button" id="btn-addUser">Add User</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Body -->
            <div class="row m-0">
                <div class="col p-0">
                    <div class="row shadow div-leftmenu-body m-0 pt-1 pb-3 ps-3 pe-3">
                        <table class="table table-hover align-middle">
                            <thead>
                                <tr>
                                    <th scope="col">User name</th>
                                    <th scope="col">Employee</th>
                                    <th scope="col">Role</th>
                                    <th scope="col">Last login</th>
                                    <th scope="col">Last update</th>
                                    <th scope="col">Status</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody id="table-body">
                                ${await loadSystemUserList()}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    `);

    async function loadSystemUserList() {
        return new Promise(function (resolve, reject) {
            $.ajax({
                type: "POST",
                url: "loadSystemUserList",
                success: function (response) {
                    resolve(response);
                },
                error: function (error) {
                    reject(error);
                }
            });
        });
    }

    $('#refresh-systemUserManage').click(function () {
        setContent_systemUserManage();
    });
    $('#btn-addUser').click(async function () {
        setLargMDL();
        $('#defModel').modal('show');
        $('#defModel-content').html(loadingInfoSystemUser);
        $('#defModel-content').html(`
            <div class="modal-header">
                <h5>Add System user</h5>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="form-systemUserAdd">
                        <div class="was-validated">
                            <label for="userName" class="form-label">User name</label>
                            <input type="text" class="form-control" id="userName" name="userName" required>
                        </div>
                        <div class="was-validated">
                            <label for="password" class="form-label">password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="was-validated">
                            <label for="employeeId" class="form-label">Employee</label>
                            <select class="form-select" aria-label="Default select example" name="employeeId" id="employeeId">
                                ${await loadEmployeeList_systemUser()}
                            </select>
                        </div>
                        <div class="was-validated">
                            <label for="roleId" class="form-label">Role</label>
                            <select class="form-select" aria-label="Default select example" name="roleId" id="roleId">
                                ${await loadRoleList_systemUser()}
                            </select>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" style="width: 100px;" id="btn-model-saveSystemUser" onclick="onClickSaveModal()">Save</button>
                <button class="btn btn-danger" style="width: 100px;" data-bs-dismiss="modal">Cancel</button>
            </div>
        `);
    });
}
async function loadEmployeeList_systemUser() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "loadEmployeeListSystemUser",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                alert(error);
            }
        });
    });
}

async function loadRoleList_systemUser() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "loadRoleListSystemUser",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                alert(error);
            }
        });
    });
}
// Save

async function onClickSaveModal() {
    console.log("called !");
    $('#subModel-content').html("                <div class=\"modal-header\">\n" +
            "                    <h5>Confirm request</h5>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <p class=\"fw-normal fs-5\">Confirm save new user account</p>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer \">\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-modelConfirm-addUser\">Confirm</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Close</button>\n" +
            "                </div>");
    $('#btn-modelConfirm-addUser').click(async function () {
        $('#defModel-content').html(await save_systemUser());
        $('#subModel').modal('hide');
        setContent_systemUserManage();

        async function save_systemUser() {
            return new Promise(function (resolve, reject) {

                let formElement = document.getElementById('form-systemUserAdd');
                let formData = new FormData(formElement);

                $.ajax({
                    type: "POST",
                    url: "saveSystemUser",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        resolve(response);
                    },
                    error: function (error) {
                        reject(error);
                    }
                });

            });
        }
    });
    $('#subModel').modal('show');

}

// Update

async function clickOnUpdateSystemUser(id) {
    setLargMDL();
    $('#defModel-content').html(loadingInfoSystemUser);
    let data = await generateUserAccountDetails(id);
    $('#defModel-content').html(" <div class=\"modal-header\">\n" +
            "                    <h5>Update System user</h5>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <form id=\"form-systemUserUpdate\">\n" +
            "                            <div>\n" +
            "                                <label for=\"userName\" class=\"form-label\">User name</label>\n" +
            "                                <input type=\"text\" class=\"form-control\" id=\"userName\" name=\"userName\" value=\"" + data.userName + "\" readonly>\n" +
            "                            </div>\n" +
            "                            <div class=\"was-validated\">\n" +
            "                                <label for=\"employeeId\" class=\"form-label\">Employee</label>\n" +
            "                                <input type=\"text\" class=\"form-control\" id=\"employeeId\" name=\"employeeId\" value=\"" + data.employeeId + "\" readonly>\n" +
            "                            </div>\n" +
            "                            <div class=\"was-validated\">\n" +
            "                                <label for=\"roleId\" class=\"form-label\">Role</label>\n" +
            "                                <select class=\"form-select\" aria-label=\"Default select example\" name=\"roleId\" id=\"roleId\">\n" +
            data.roleList +
            "                                </select>\n" +
            "                            </div>\n" +
            "                        </form>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer \">\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-saveSystemUser\" onclick=\"onClickUpdateModal()\">Update</button>\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-passwordSystemUser\" onclick=\"onClickPasswordModal('" + data.userName + "')\">Password</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "                </div>");
    $('#defModel').modal('show');
}

function onClickPasswordModal(userName) {
    $('#defModel-content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Reset password</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-6">
                    <label for="" class="form-label">New Password</label>
                    <input type="text" class="form-control" id="newPassword-systemUserManage">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="passwordResetConfirm-btn">Reset</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    $('#passwordResetConfirm-btn').click(async function(){
        let newPassword = $('#newPassword-systemUserManage').val();
        $('#defModel-content').html(`
                        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <div>
                        <p class="text-center m-0">${await resetPasswordSRVR_systemUserManage(userName, newPassword)}</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    });
    
}

async function resetPasswordSRVR_systemUserManage(userName, newPassword) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "ResetSystemUserPassword",
            data: {
                "userName": userName,
                "newPassword": newPassword
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

async function generateUserAccountDetails(id) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateUserAccountDetails",
            data: {
                "systemUserName": id
            },
            success: function (response) {
                let data = JSON.parse(response);
                resolve(data);
            },
            error: function (error) {
                alert(error);
            }
        });
    });
}

async function onClickUpdateModal() {
    $('#defModel-content').html(`
                        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <div>
                        <p class="text-center m-0">${await updateSystemUser()}</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    setContent_systemUserManage();

    async function updateSystemUser() {
        return new Promise(function (resolve, reject) {

            let formElement = document.getElementById('form-systemUserUpdate');
            let formData = new FormData(formElement);

            $.ajax({
                type: "POST",
                url: "updateSystemUser",
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    resolve(response);
                },
                error: function (error) {
                    alert(error);
                }
            });

        });
    }

}


// Inactivate / Activate

function clickOnchangeStatus_systemUser(id, status) {
    let textInConfirmMsg = "";
    if (status) {
        textInConfirmMsg = "Activate";
    } else {
        textInConfirmMsg = "Inactivate";
    }
    setNormalMDL();
    $('#defModel-content').html("                <div class=\"modal-header\">\n" +
            "                    <h5>Confirm request</h5>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <p class=\"fw-normal fs-5\">Confirm " + textInConfirmMsg + " User account : <span class=\"fw-bold\">" + id + "</span></p>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer \">\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-modelConfirm-changeStatusUserAccount\">Confirm</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Close</button>\n" +
            "                </div>");
    $('#defModel').modal('show');

    $('#btn-modelConfirm-changeStatusUserAccount').click(async function () {
        $('#defModel-content').html(loadingInfoSystemUser);
        $('#defModel-content').html("                <div class=\"modal-header\">\n" +
                "                    <h5>Response</h5>\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n" +
                "                    <div class=\"container-fluid\">\n" +
                "                        <div class=\"row\">\n" +
                "                            <p class=\"fw-normal fs-5\">" + await changeStatus_systemUser(id, status) + "</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"modal-footer \">\n" +
                "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Close</button>\n" +
                "                </div>");

        async function changeStatus_systemUser(id, status) {
            return new Promise(function (resolve, reject) {

                $.ajax({
                    type: "POST",
                    url: "changeSystemUserStatus",
                    data: {
                        "id": id,
                        "status": status
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

        setContent_systemUserManage();

    });

}