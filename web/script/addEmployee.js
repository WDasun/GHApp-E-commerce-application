let confirmRequetModalContent = " <div class=\"modal-header\">\n" +
        "                    <h5>Confirm request</h5>\n" +
        "                </div>\n" +
        "                <div class=\"modal-body\">\n" +
        "                    <div class=\"container-fluid\">\n" +
        "                        <div class=\"row\">\n" +
        "                            <p class=\"fw-normal fs-5\">Are you sure, do you want to add this Employee !</p>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"modal-footer \">\n" +
        "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-modelConfirm-addEmployee\">Confirm</button>\n" +
        "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Close</button>\n" +
        "                </div>";
var loadingInfo_addEmployee = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

$(document).ready(function () {
    $('#btn-addEmployee').click(async function () {
        $('#div-rightmenu').html(loadingInfo_addEmployee);
        $('#div-rightmenu').html(await setAddEmployeeDivContentData());
        addNewButtonEvents_addEmployee();
    });
});

function addNewButtonEvents_addEmployee() {
    $('#imageInput').change(function (event) {
        let reader = new FileReader();
        reader.onload = function (event) {
            document.getElementById('icn-imageInput').style.display = 'none';
            $('#img-imageInput').attr('src', event.target.result);
            $('#img-imageInput').show();
        };
        reader.readAsDataURL(this.files[0]);
    });

    $('#btn-removeImage').click(function () {
        document.getElementById('imageInput').value = '';
        let img = document.getElementById('img-imageInput');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInput').style.display = 'block';
    });

    $('#btn-head-discard-addEmployee').click(async function () {
        $('#div-rightmenu').html(await setAddEmployeeDivContentData());
        addNewButtonEvents();
    });

    $('#btn-head-addEmployee').click(function () {
 
        if (validateForm_addEmployee()) {
            $('#defModel-content').html(confirmRequetModalContent);
            $('#defModel').modal('show');
            $('#btn-modelConfirm-addEmployee').click(async function () {
                $('#defModel-content').html(loadingInfo_addEmployee);
                try {
                    $('#defModel-content').html(await saveEmployeeData());
                    $('#div-rightmenu').html(await setAddEmployeeDivContentData());
                    addNewButtonEvents();
                } catch (error) {
                    alert(error);
                }

            });
        } else {
            alert("All fields must completed !");
        }
    });
}

async function saveEmployeeData() {
    return new Promise(function (resolve, reject) {
        let formElement = document.getElementById('form-addEmployee');
        let formData = new FormData(formElement);
        $.ajax({
            type: "POST",
            url: "SaveEmployee",
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

async function loadEmployeeId() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadEmployeeId",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

function validateForm_addEmployee() {

    var form = document.getElementById('form-addEmployee');

    var inputs = form.getElementsByTagName('input');
    var selects = form.getElementsByTagName('select');


    for (var i = 0; i < inputs.length; i++) {

        if (inputs[i].value === null || inputs[i].value.trim() === '') {
            return false;
        }
    }

    for (var i = 0; i < selects.length; i++) {

        if (selects[i].value === null || selects[i].value.trim() === '') {
            return false;
        }
    }

    return true;
}

async function setAddEmployeeDivContentData() {
    let content = "";

    try {
        content = "                 <div class=\"container-fluid p-0\" id=\"div-addProduct\">\n" +
                "                        <!-- Head -->\n" +
                "                        <div class=\"row m-0 mb-3\">\n" +
                "                            <div class=\"col p-0\">\n" +
                "                                <div class=\"row shadow div-leftmenu-head m-0\">\n" +
                "                                    <div class=\"col d-flex align-items-center p-0 ps-3\">\n" +
                "                                        <p class=\"txt-rightmenu-description m-0\">Add Employee</p>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"col d-flex align-items-center justify-content-end p-0 pe-2\">\n" +
                "                                        <button class=\"btn btn-secondary btn-leftmenu-head me-2\" type=\"button\" id=\"btn-head-discard-addEmployee\">Discard</button>\n" +
                "                                        <button class=\"btn btn-primary btn-leftmenu-head me-2\" type=\"button\" id=\"btn-head-addEmployee\">Add Employee</button>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- Body -->\n" +
                "                        <div class=\"row m-0\">\n" +
                "                            <form class=\"p-0\" id=\"form-addEmployee\">\n" +
                "                                <div class=\"row m-0\">\n" +
                "                                    <div class=\"col p-0 pe-2\">\n" +
                "                                        <div class=\"shadow was-validated div-leftmenu-body pt-2 pb-3 px-3\">\n" +
                "\n" +
                "                                            <label for=\"empId\" class=\"form-label lbl-leftMenu-body\">Employee ID</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"empId\" name=\"empId\" value=\"" + await loadEmployeeId() + "\" readonly>\n" +
                "\n" +
                "                                            <label for=\"fname\" class=\"form-label lbl-leftMenu-body mt-3\">First name</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"fname\" name=\"fname\" required>\n" +
                "\n" +
                "                                            <label for=\"lname\" class=\"form-label lbl-leftMenu-body mt-3\">Last name</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"lname\" name=\"lname\" required>\n" +
                "\n" +
                "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"col p-0 ps-2\">\n" +
                "                                        <div class=\"shadow was-validated div-leftmenu-body pt-2 pb-3 px-3\">\n" +
                "\n" +
                "                                            <label for=\"gender\" class=\"form-label lbl-leftMenu-body\">Gender</label>\n" +
                "                                            <select class=\"form-select\" aria-label=\"Default select example\" id=\"gender\" name=\"gender\">\n" +
                "                                              <option value=\"male\">Male</option>\n" +
                "                                              <option value=\"female\">Female</option>\n" +
                "                                              <option value=\"other\">Other</option>\n" +
                "                                            </select>\n" +
                "\n" +
                "                                            <label for=\"dob\" class=\"form-label lbl-leftMenu-body mt-3\">Date of birth</label>\n" +
                "                                            <input type=\"date\" class=\"form-control\" id=\"dob\" name=\"dob\" required>\n" +
                "\n" +
                "                                            <label for=\"nic\" class=\"form-label lbl-leftMenu-body mt-3\">NIC</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" id=\"nic\" name=\"nic\" required>\n" +
                "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"row m-0 mt-3\">\n" +
                "                                    <div class=\"col p-0 pe-2\">\n" +
                "                                        <div class=\"shadow was-validated div-leftmenu-body pt-2 pb-3 px-3\">\n" +
                "\n" +
                "                                            <label for=\"email\" class=\"form-label lbl-leftMenu-body\">Email</label>\n" +
                "                                            <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" required>\n" +
                "\n" +
                "                                            <label for=\"address\" class=\"form-label lbl-leftMenu-body mt-3\">Address</label>\n" +
                "                                            <input type=\"email\" class=\"form-control\" id=\"address\" name=\"address\" required>\n" +
                "\n" +
                "                                            <label for=\"cnt1\" class=\"form-label lbl-leftMenu-body mt-3\">Contact number 1</label>\n" +
                "                                            <input type=\"email\" class=\"form-control\" id=\"cnt1\" name=\"cnt1\" required>\n" +
                "\n" +
                "                                            <label for=\"cnt2\" class=\"form-label lbl-leftMenu-body mt-3\">Contact number 2</label>\n" +
                "                                            <input type=\"email\" class=\"form-control\" id=\"cnt2\" name=\"cnt2\" required>\n" +
                "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"col p-0 ps-2\">\n" +
                "                                        <div class=\"shadow d-flex align-items-start was-validated div-leftmenu-body pt-3 pb-3 px-3\">\n" +
                "\n" +
                "                                            <label for=\"cnt1\" class=\"form-label lbl-leftMenu-body m-0 me-5\">Select profile picture : </label>\n" +
                "                                            <div class=\"d-flex align-items-center justify-content-center mt-1\">\n" +
                "                                                <div class=\"form-group\">\n" +
                "                                                    <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInput\" style=\"height: 300px; width: 200px;\">\n" +
                "                                                      <i class=\"fa-solid fa-image\" id=\"icn-imageInput\"></i>\n" +
                "                                                      <img id=\"img-imageInput\" src=\"#\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
                "                                                  </label>\n" +
                "                                                    <input type=\"file\" class=\"form-control d-none\" id=\"imageInput\" name=\"image\" accept=\"image/*\">\n" +
                "                                                </div>\n" +
                "                                                <div class=\"\" style=\"height: 300px;\">\n" +
                "                                                    <div class=\"container px-3\">\n" +
                "                                                        <div class=\"row mt-1\">\n" +
                "                                                            <button class=\"btn btn-danger ms-1\" type=\"button\" id=\"btn-removeImage\">\n" +
                "                                                              <i class=\"fa-solid fa-trash\"></i>\n" +
                "                                                          </button>\n" +
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
                "                    </div>";
    } catch (error) {
        alert(error);
    }

    return content;
}