var loadingInfo_employeeList = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

$(document).ready(function () {
    $('#btn-employeeList').click(function () {
        setDivContent_employeeList();
    });
});

async function setDivContent_employeeList() {
    $('#div-rightmenu').html(loadingInfo_employeeList);
    $('#div-rightmenu').html(await setEmployeeListDivContentData());
    addNewButtonEvents();
}

function addNewButtonEvents() {
    $('#refresh-employeeList').click(async function () {
        setDivContent_employeeList();
    });
}

async function setEmployeeListDivContentData() {
    try {
        let EmplyeeListDivContent = "<div class=\"container-fluid p-0\" id=\"div-addProduct\">\n" +
                "                        <!-- Head -->\n" +
                "                        <div class=\"row m-0 mb-3\">\n" +
                "                            <div class=\"col p-0\">\n" +
                "                                <div class=\"row shadow div-leftmenu-head m-0\">\n" +
                "                                    <div class=\"col d-flex align-items-center p-0 ps-3\">\n" +
                "                                        <p class=\"txt-rightmenu-description m-0\">Employee List</p>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"col d-flex align-items-center justify-content-end p-0 pe-2\">\n" +
                "                                        <button class=\"btn-categoryList-refresh border border-1 btn btn-light  me-3 p-0 py-2 px-3\" id=\"refresh-employeeList\"><i class=\"fa-solid fa-arrows-rotate fa-lg\"></i></button>\n" +
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
                "                                                <th scope=\"col\">Employee ID</th>\n" +
                "                                                <th scope=\"col\">Full name</th>\n" +
                "                                                <th scope=\"col\">NIC</th>\n" +
                "                                                <th scope=\"col\">Email</th>\n" +
                "                                                <th scope=\"col\">Last update</th>\n" +
                "                                                <th scope=\"col\">Status</th>\n" +
                "                                                <th scope=\"col\"></th>\n" +
                "                                            </tr>\n" +
                "                                        </thead>\n" +
                "                                        <tbody id=\"table-body\">\n" +
                await getEmployeeListTableData() +
                "                                        </tbody>\n" +
                "                                    </table>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>";
        return EmplyeeListDivContent;
    } catch (error) {
        alert(error);
    }
}

async function getEmployeeListTableData() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateEmployeeList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

async function clickOnInactivateEmployee(empId) {
    $('#defModel-content').html(loadingInfo_employeeList);
    $('#defModel').modal('show');
    try {
        $('#defModel-content').html(await inactivateEmployee_employeeList(empId));
        setDivContent_employeeList();
    } catch (error) {
        alert(error);
    }
}

async function inactivateEmployee_employeeList(id) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "changeStatusEmployee",
            data: {
                "id": id,
                "status": "false"
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

async function clickOnActivateEmployee(empId) {
    $('#defModel-content').html(loadingInfo_employeeList);
    $('#defModel').modal('show');
    try {
        $('#defModel-content').html(await activateEmployee_employeeList(empId));
        setDivContent_employeeList();
    } catch (error) {
        alert(error);
    }
}

async function activateEmployee_employeeList(id) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "changeStatusEmployee",
            data: {
                "id": id,
                "status": "true"
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

let profilePictureStatus_employeeList = true;

async function clickOnUpdateEmployee(empId) {
    $('#defModel-content').html(loadingInfo_employeeList);
    setLargMDL();
    $('#defModel').modal('show');
    $('#defModel-content').html(await getModalEmployeeData(empId));

    let img = document.getElementById('img-imageInput');
    img.style.display = 'block';
    document.getElementById('icn-imageInput').style.display = 'none';

    $('#removeImage').click(function () {
        document.getElementById('imageInput').value = '';
        let img = document.getElementById('img-imageInput');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInput').style.display = 'block';
        profilePictureStatus_employeeList = false;

        console.log(profilePictureStatus_employeeList);

    });
    $('#imageInput').change(function (event) {
        let reader = new FileReader();
        reader.onload = function (event) {
            document.getElementById('icn-imageInput').style.display = 'none';
            $('#img-imageInput').attr('src', event.target.result);
            $('#img-imageInput').show();
            profilePictureStatus_employeeList = true;
        };
        reader.readAsDataURL(this.files[0]);
    });
    $('#btn-model-updateEmployee').click(function () {
        setSubModalContent_employeeList();
        $('#subModel').modal('show');
    });
}

async function getModalEmployeeData(empId) {
    try {
        let data = await getEmployeeData_employeeList(empId);

        let content = "                <!-- Update Modal content -->\n" +
                "                <div class=\"modal-header\">\n" +
                "                    <h5>Update Emplyee</h5>\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n" +
                "                    <div class=\"container-fluid p-0\" id=\"div-addProduct\">\n" +
                "                        <!-- Body -->\n" +
                "                        <div class=\"row m-0\">\n" +
                "                            <form class=\"p-0\" id=\"form-employeeList\">\n" +
                "                                <div class=\"row m-0\">\n" +
                "                                    <div class=\"col p-0 pe-2\">\n" +
                "                                        <div class=\"was-validated div-leftmenu-body pt-2 pb-3 px-3\">\n" +
                "\n" +
                "                                            <label for=\"empId\" class=\"form-label lbl-leftMenu-body\">Employee ID</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"empId\" name=\"empId\" value=\"" + data.empId + "\" readonly>\n" +
                "\n" +
                "                                            <label for=\"fname\" class=\"form-label lbl-leftMenu-body mt-3\">First name</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"fname\" name=\"fname\" value=\"" + data.fname + "\" required>\n" +
                "\n" +
                "                                            <label for=\"lname\" class=\"form-label lbl-leftMenu-body mt-3\">Last name</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"lname\" name=\"lname\" value=\"" + data.lname + "\" required>\n" +
                "\n" +
                "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"col p-0 ps-2\">\n" +
                "                                        <div class=\"was-validated div-leftmenu-body pt-2 pb-3 px-3\">\n" +
                "\n" +
                "                                            <label for=\"gender\" class=\"form-label lbl-leftMenu-body\">Gender</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"gender\" name=\"gender\" value=\"" + data.gender + "\" required>\n" +
                "\n" +
                "                                            <label for=\"dob\" class=\"form-label lbl-leftMenu-body mt-3\">Date of birth</label>\n" +
                "                                            <input type=\"date\" class=\"form-control\" id=\"dob\" name=\"dob\" value=\"" + data.dob + "\" required>\n" +
                "\n" +
                "                                            <label for=\"nic\" class=\"form-label lbl-leftMenu-body mt-3\">NIC</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"nic\" name=\"nic\" value=\"" + data.nic + "\" required>\n" +
                "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"row m-0 mt-3\">\n" +
                "                                    <div class=\"col p-0 pe-2\">\n" +
                "                                        <div class=\"was-validated div-leftmenu-body pt-2 pb-3 px-3\">\n" +
                "\n" +
                "                                            <label for=\"email\" class=\"form-label lbl-leftMenu-body\">Email</label>\n" +
                "                                            <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" value=\"" + data.email + "\" required>\n" +
                "\n" +
                "                                            <label for=\"address\" class=\"form-label lbl-leftMenu-body mt-3\">Address</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"address\" name=\"address\" value=\"" + data.address + "\" required>\n" +
                "\n" +
                "                                            <label for=\"cnt1\" class=\"form-label lbl-leftMenu-body mt-3\">Contact number 1</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"cnt1\" name=\"cnt1\" value=\"" + data.cnt1 + "\" required>\n" +
                "\n" +
                "                                            <label for=\"cnt2\" class=\"form-label lbl-leftMenu-body mt-3\">Contact number 2</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"cnt2\" name=\"cnt2\" value=\"" + data.cnt2 + "\" required>\n" +
                "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"col p-0 ps-2\">\n" +
                "                                        <div class=\"d-flex align-items-start was-validated div-leftmenu-body pt-3 pb-3 px-3\">\n" +
                "\n" +
                "                                            <label for=\"cnt1\" class=\"form-label lbl-leftMenu-body m-0 me-5\">Select profile picture : </label>\n" +
                "                                            <div class=\"d-flex align-items-center justify-content-center mt-1\">\n" +
                "                                                <div class=\"form-group\">\n" +
                "                                                    <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInput\" style=\"height: 300px; width: 200px;\">\n" +
                "                                    <i class=\"fa-solid fa-image\" id=\"icn-imageInput\"></i>\n" +
                "                                    <img id=\"img-imageInput\" src=\"" + data.image + "\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
                "                                </label>\n" +
                "                                                    <input type=\"file\" class=\"form-control d-none\" id=\"imageInput\" name=\"image\" accept=\"image/*\">\n" +
                "                                                </div>\n" +
                "                                                <div class=\"\" style=\"height: 300px;\">\n" +
                "                                                    <div class=\"container px-3\">\n" +
                "                                                        <div class=\"row mt-1\">\n" +
                "                                                            <button class=\"btn btn-danger ms-1\" id=\"removeImage\" type=\"button\">\n" +
                "                                            <i class=\"fa-solid fa-trash\"></i>\n" +
                "                                        </button>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </form>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    </form>\n" +
                "                </div>\n" +
                "                <div class=\"modal-footer \">\n" +
                "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-updateEmployee\">Update</button>\n" +
                "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
                "                </div>";
        return content;
    } catch (error) {
        alert(error);
    }
}

async function getEmployeeData_employeeList(empId) {

    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "generateEmployeeData",
            data: {
                "empId": empId
            },
            success: function (response) {
                let data = JSON.parse(response);
                resolve(data);
            },
            error: function (error) {
                error(error);
            }
        });
    });

}

function setSubModalContent_employeeList() {

    $('#subModel-content').html("                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\"><i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Confirm</h5>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col\">\n" +
            "                                <p class=\"m-0 fs-5 fw-normal\">Do you want to update empoyee account !</p>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer\">\n" +
            "                    <button class=\"btn btn-danger\" style=\"width: 100px;\" id=\"btn-subModal-confirm-employeeList\" data-bs-dismiss=\"modal\">Confirm</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "                </div>");

    $('#btn-subModal-confirm-employeeList').click(async function () {
        $('#defModel-content').html(await updateEmployee_employeeList());
        $('#subModel').modal('hide');
        setDivContent_employeeList();
    });

}

async function updateEmployee_employeeList() {

    return new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-employeeList');
        let formData = new FormData(formElement);
        $.ajax({
            type: "POST",
            url: "UpdateEmployee",
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