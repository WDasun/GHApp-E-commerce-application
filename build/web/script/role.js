var loadingInfoRole = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

$(document).ready(function () {
    $("#btn-role").click(function () {
        $("#div-rightmenu").html(loadingInfoRole);
        addRightMenuRole();
    });
});

async function addRightMenuRole() {
    $('#div-rightmenu').html(" <div class=\"container-fluid p-0\" id=\"div-addProduct\">\n" +
            "                        <!-- Head -->\n" +
            "                        <div class=\"row m-0 mb-3\">\n" +
            "                            <div class=\"col p-0\">\n" +
            "                                <div class=\"row shadow div-leftmenu-head m-0\">\n" +
            "                                    <div class=\"col d-flex align-items-center p-0 ps-3\">\n" +
            "                                        <p class=\"txt-rightmenu-description m-0\">Role</p>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"col d-flex align-items-center justify-content-end p-0 pe-2\">\n" +
            "                                        <button class=\"btn-categoryList-refresh border border-1 btn btn-light  me-3 p-0 py-2 px-3\" id=\"refresh-employeeList\"><i class=\"fa-solid fa-arrows-rotate fa-lg\"></i></button>\n" +
            "                                        <button class=\"btn btn-primary btn-leftmenu-head me-2\" type=\"button\" id=\"btn-addRole\">Add Role</button>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!-- Body -->\n" +
            "                        <div class=\"row m-0\">\n" +
            "                            <div class=\"col p-0\">\n" +
            "                                <div class=\"row shadow div-leftmenu-body m-0 pt-1 pb-3 ps-3 pe-3\">\n" +
            "                                    <table class=\"table table-hover align-middle\">\n" +
            "                                        <thead>\n" +
            "                                            <tr>\n" +
            "                                                <th scope=\"col\">Role ID</th>\n" +
            "                                                <th scope=\"col\">Role name</th>\n" +
            "                                                <th scope=\"col\">Description</th>\n" +
            "                                                <th scope=\"col\"></th>\n" +
            "                                            </tr>\n" +
            "                                        </thead>\n" +
            "                                        <tbody id=\"table-body\">\n" +
            await loadRoleList() +
            "                                        </tbody>\n" +
            "                                    </table>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>");
    $('#btn-addRole').click(function () {
        setNormalMDL();
        setAddRoleMDL();
        $('#defModel').modal('show');
    });
}

async function loadRoleList() {
    return  new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadRoleList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });
}

// Role update

function clickOnUpdateRole(id) {
    setNormalMDL();
    setUpdateRoleMDL(id);
    $('#defModel').modal('show');
}

async function setUpdateRoleMDL(id) {
    let data = JSON.parse(await getRoleData(id));
    $('#defModel-content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add Role</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <div>
                        <div class="mb-1">
                            <Label class="form-label text-modal-label">Role ID</Label>
                            <input type="text" class="form-control" id="roleId-updateRole" value="${data.roleId}" disabled>
                        </div>
                        <div class="mb-1">
                            <Label class="form-label text-modal-label">Role Name</Label>
                            <input type="text" class="form-control" id="roleName-updateRole" value="${data.roleName}">
                        </div>
                        <div class="mb-1">
                            <Label class="form-label text-modal-label">Description</Label>
                            <textarea class="form-control" rows="3" id="roleDescription-updateRole">${data.roleDescription}</textarea>
                        </div>
                    </div>
                    <div class="mb-1">
                        <Label class="form-label text-modal-label fw-bold">Permissions</Label>
                        <div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f1-updateRole" ${data.f1 ? 'checked' : ''}>
                                <label class="form-check-label permission-label" for="f1-updateRole">
                                    [f1] SystemUser Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f2-updateRole" ${data.f2 ? 'checked' : ''}>
                                <label class="form-check-label permission-label" for="f2-updateRole">
                                    [f2] Employee Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f3-updateRole" ${data.f3 ? 'checked' : ''}>
                                <label class="form-check-label permission-label" for="f3-updateRole">
                                    [f3] Customer Order Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f4-updateRole" ${data.f4 ? 'checked' : ''}>
                                <label class="form-check-label permission-label" for="f4-updateRole">
                                    [f4] System Variable Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f5-updateRole" ${data.f5 ? 'checked' : ''}>
                                <label class="form-check-label permission-label" for="f5-updateRole">
                                    [f5] Shop Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f6-updateRole" ${data.f6 ? 'checked' : ''}>
                                <label class="form-check-label permission-label" for="f6-addRole">
                                    [f6] Customer manage access
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="btn-model-updateRole">Update</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    $('#btn-model-updateRole').click(async function () {
        if (
                $('#roleId-updateRole').val().trim() !== "" &&
                $('#roleName-updateRole').val().trim() !== "" &&
                $('#roleDescription-updateRole').val().trim() !== ""
                ) {
            let updateData = {
                roleId: $('#roleId-updateRole').val().trim(),
                roleName: $('#roleName-updateRole').val().trim(),
                roleDescription: $('#roleDescription-updateRole').val().trim(),
                f1: $('#f1-updateRole').prop("checked"),
                f2: $('#f2-updateRole').prop("checked"),
                f3: $('#f3-updateRole').prop("checked"),
                f4: $('#f4-updateRole').prop("checked"),
                f5: $('#f5-updateRole').prop("checked"),
                f6: $('#f6-updateRole').prop("checked")
            }
            try {
                $('#defModel-content').html(`
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <p class="text-center m-0">${await updateRole(JSON.stringify(updateData))}</p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
            `);
                addRightMenuRole();
            } catch (error) {
                alert(error);
            }
        } else {
            alert("Please ensure all required fields are completed before proceeding !");
        }
    });
}

async function updateRole(roleInfo) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "updateRole",
            data: {
                "roleInfo": roleInfo
            },
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });

    return myPromise;
}

async function getRoleData(id) {
    let myPromise = new Promise(function (resolve, reject) {

        $.ajax({
            type: "POST",
            url: "generateRoleData",
            data: {
                "id": id
            },
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });

    return myPromise;
}

// Role Add


function setAddRoleMDL() {
    $('#defModel-content').html(`
            <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add Role</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <div>
                        <div class="mb-1">
                            <Label class="form-label text-modal-label">Role ID</Label>
                            <input type="text" class="form-control" id="roleId-addRole">
                        </div>
                        <div class="mb-1">
                            <Label class="form-label text-modal-label">Role Name</Label>
                            <input type="text" class="form-control" id="roleName-addRole">
                        </div>
                        <div class="mb-1">
                            <Label class="form-label text-modal-label">Description</Label>
                            <textarea class="form-control" rows="3" id="roleDescription-addRole"></textarea>
                        </div>
                    </div>
                    <div class="mb-1">
                        <Label class="form-label text-modal-label fw-bold">Permissions</Label>
                        <div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f1-addRole">
                                <label class="form-check-label permission-label" for="f1-addRole">
                                    [f1] SystemUser Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f2-addRole">
                                <label class="form-check-label permission-label" for="f2-addRole">
                                    [f2] Employee Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f3-addRole">
                                <label class="form-check-label permission-label" for="f3-addRole">
                                    [f3] Customer Order Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f4-addRole">
                                <label class="form-check-label permission-label" for="f4-addRole">
                                    [f4] System Variable Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f5-addRole">
                                <label class="form-check-label permission-label" for="f5-addRole">
                                    [f5] Shop Manage Access
                                </label>
                            </div>
                            <div class="form-check d-flex align-items-center ps-5">
                                <input class="form-check-input me-3" type="checkbox" id="f6-addRole">
                                <label class="form-check-label permission-label" for="f6-addRole">
                                    [f6] Customer manage access
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="btn-model-saveRole">Save</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    $('#btn-model-saveRole').click(async function () {
        if (
             $('#roleId-addRole').val().trim() !== "" &&
             $('#roleName-addRole').val().trim() !== "" &&
             $('#roleDescription-addRole').val().trim() !== ""
                ) {
            roleIdAddRole = $('#roleId-addRole').val().trim();
            roleNameAddRole = $('#roleName-addRole').val().trim();
            roleDescriptionAddRole = $('#roleDescription-addRole').val().trim();
            f1AddRole = $('#f1-addRole').prop("checked");
            f2AddRole = $('#f2-addRole').prop("checked");
            f3AddRole = $('#f3-addRole').prop("checked");
            f4AddRole = $('#f4-addRole').prop("checked");
            f5AddRole = $('#f5-addRole').prop("checked");
            f6AddRole = $('#f6-addRole').prop("checked");
            try {
                $('#defModel-content').html(loadingInfoRole);
                $('#defModel-content').html(`
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <p class="text-center m-0">${await saveRole()}</p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
            `);
                addRightMenuRole();
            } catch (error) {
                alert(error);
            }
        }else{
            alert("Please ensure all required fields are completed before proceeding !");
        }
    });
}
let roleIdAddRole;
let roleNameAddRole;
let roleDescriptionAddRole;
let f1AddRole;
let f2AddRole;
let f3AddRole;
let f4AddRole;
let f5AddRole;
let f6AddRole;
async function saveRole() {
    let myPromise = new Promise(function (resolve, reject) {

        $.ajax({
            type: "POST",
            url: "saveRole",
            data: {
                "roleIdAddRole": roleIdAddRole,
                "roleNameAddRole": roleNameAddRole,
                "roleDescriptionAddRole": roleDescriptionAddRole,
                "f1AddRole": f1AddRole,
                "f2AddRole": f2AddRole,
                "f3AddRole": f3AddRole,
                "f4AddRole": f4AddRole,
                "f5AddRole": f5AddRole,
                "f6AddRole": f6AddRole
            },
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });

    return myPromise;
}


