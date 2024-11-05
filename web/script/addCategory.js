var loadingInfo_addCategory = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

// Add category button listner
$(document).ready(function () {

    $("#addCategory").click(function () {
        setRightMenu_addCategory();
    });
});

// Adding addCategory div to the rightn menu
async function setRightMenu_addCategory() {
    try {
        $('#div-rightmenu').html(loadingInfo_addCategory);
        $('#div-rightmenu').html("<!-- Div body -->\n" +
                "                    <div class=\"container-fluid\" id=\"div-addCategory\">\n" +
                "                        <!-- Head -->\n" +
                "                        <div class=\"row shadow div-leftmenu-head\">\n" +
                "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
                "                                <p class=\"m-0 txt-rightmenu-description\">Add Category</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
                "                                <button class=\"btn btn-secondary me-2 btn-leftmenu-head\" id=\"btn-discard-addCategory\">Discard</button>\n" +
                "                                <button class=\"btn btn-primary me-2 btn-leftmenu-head\" id=\"btn-addCategory\">Add Category</button>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- Body -->\n" +
                "                        <form id=\"form-addCategory\">\n" +
                "                            <div class=\"row mt-3\">\n" +
                "                                <div class=\"col-7 p-0 pe-3\">\n" +
                "                                    <div class=\"pb-4 pt-3 px-3 shadow div-leftmenu-body\">\n" +
                "                                        <div>\n" +
                "                                            <label for=\"categoryId\" class=\"form-label lbl-leftMenu-body\">Category ID</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" name=\"categoryId\" id=\"categoryId\" value=\"" + await generateId__addCategory() + "\" readonly>\n" +
                "                                        </div>\n" +
                "                                        <div class=\"mt-2 was-validated\">\n" +
                "                                            <label for=\"categoryName\" class=\"form-label lbl-leftMenu-body\">Category Name</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" name=\"categoryName\" id=\"categoryName\" required>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"col-5 pt-3 shadow div-leftmenu-body\">\n" +
                "                                    <label for=\"select-parentCategory\" class=\"form-label lbl-leftMenu-body\">Select category</label>\n" +
                "                                    <select class=\"form-select\" aria-label=\"Default select example\" name=\"select-parentCategory\" id=\"select-parentCategory\">\n" +
                await generateParentCategoryList__addCategory() +
                "                                    </select>\n" +
                "\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </form>\n" +
                "                    </div>");
        bindingBtnClickMainDiv__addCategory();
    } catch (error) {
        alert("Error : " + error);
    }
}

// Generating category ID
async function generateId__addCategory() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateCategoryId",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

// Generate parent category list
async function generateParentCategoryList__addCategory() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "generateParentCategoryList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

// Add Event listners to new Div buttons in head
function bindingBtnClickMainDiv__addCategory() {
    $('#btn-addCategory').click(function () {
        console.log();
        if (validDataCheck_addCategory()) {
            setModal_addCategory();
            $('#defModel').modal('show');
        } else {
            alert('All fields must completed !');
        }
    });
    $('#btn-discard-addCategory').click(function () {
        setRightMenu_addCategory();
    });
}

// Data validation
function validDataCheck_addCategory() {
    let valid = false;

    if (($('#categoryId').val() !== '') && ($('#categoryName').val() !== '')) {
        valid = true;
    } else {
        valid = false;
    }
    return valid;
}

// Set default variation modal
function setModal_addCategory() {
    $('#defModel-content').html("                <div class=\"modal-header\">\n" +
            "                    <h5>Confirm request</h5>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <p class=\"fw-normal fs-5\">Are you sure, do you want to add this Category !</p>\n" +
            "                        </div>\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6\">Category ID</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#categoryId').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6\">Parent ID</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#select-parentCategory').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row \">\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">name</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#categoryName').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer \">\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-modelConfirm-addCategory\">Confirm</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Close</button>\n" +
            "                </div>");
    // Add event listner to the defModal confirm button
    $('#btn-modelConfirm-addCategory').click(async function () {
        $('#defModel-content').html(loadingInfo_addCategory);
        $('#defModel-content').html(await saveCategory());
        setRightMenu_addCategory();
    });
}

async function saveCategory() {
    return new Promise(function (resolve, reject) {
        
        let formElement = document.getElementById('form-addCategory');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "SaveCategory",
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