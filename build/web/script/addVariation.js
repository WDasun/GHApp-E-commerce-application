var loadingInfo_addVariation = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

// Add veriation button listner
$(document).ready(function () {

    $("#addVariation").click(function () {
        setNormalMDL();
        setRightMenu_addVariation();
    });
});

// Adding addVariation div to the rightn menu
async function setRightMenu_addVariation() {
    try {
        $('#div-rightmenu').html(loadingInfo_addVariation);
        $('#div-rightmenu').html("                    <div class=\"container-fluid\" id=\"div-addVariation\">\n" +
                "                        <!-- Head -->\n" +
                "                        <div class=\"row shadow div-leftmenu-head\">\n" +
                "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
                "                                <p class=\"m-0 txt-rightmenu-description\">Add Variation</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
                "                                <button class=\"btn btn-secondary me-2 btn-leftmenu-head\" id=\"btn-discard-addVariation\">Discard</button>\n" +
                "                                <button class=\"btn btn-primary me-2 btn-leftmenu-head\" id=\"btn-addVariation\">Add Variation</button>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- Body -->\n" +
                "                        <form id=\"form-addVariation\">\n" +
                "                            <div class=\"row mt-3\">\n" +
                "                                <div class=\"col-7 p-0 pe-3\">\n" +
                "                                    <div class=\"pb-4 pt-3 px-3 shadow div-leftmenu-body\">\n" +
                "                                        <div>\n" +
                "                                            <label for=\"variationId\" class=\"form-label lbl-leftMenu-body\">Variation ID</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" name=\"id\" id=\"variationId\" value=\"" + await generateId__addVariation() + "\" readonly>\n" +
                "                                        </div>\n" +
                "                                        <div class=\"mt-2 was-validated\">\n" +
                "                                            <label for=\"variationName\" class=\"form-label lbl-leftMenu-body\">Variation Name</label>\n" +
                "                                            <input type=\"text\" class=\"form-control\" name=\"name\" id=\"variationName\" required>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div class=\"col-5 pt-3 shadow div-leftmenu-body\">\n" +
                "                                    <label for=\"selectCategory\" class=\"form-label lbl-leftMenu-body\">Select category</label>\n" +
                "                                    <select class=\"form-select\" aria-label=\"Default select example\" name=\"categoryId\" id=\"categoryId-addVariation\">\n" +
                await generateCategoryList__addVariation() +
                "        </select>\n" +
                "\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </form>\n" +
                "                    </div>");
        bindingBtnClickMainDiv__addVariation();
    } catch (error) {
        alert("Error : " + error);
    }
}

// Generating variation ID
async function generateId__addVariation() {
    return new Promise(function (resolve, reject) {
        if (true) {
            resolve("------");

        } else {
            reject("-------");
        }
    });
}

// Generate category list
async function generateCategoryList__addVariation() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateCategoryListForVariation",
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
function bindingBtnClickMainDiv__addVariation() {
    $('#btn-addVariation').click(function () {
        console.log();
        if (validDataCheck()) {
            setModal_addVariation();
            $('#defModel').modal('show');
        } else {
            alert('All fields must completed !');
        }
    });
    $('#btn-discard-addVariation').click(function () {
        setRightMenu_addVariation();
    });
}

// Data validation
function validDataCheck() {
    let valid = false;

    if (($('#variationId').val() !== '') && ($('#variationName').val() !== '') && ($('#categoryId-addVariation').val() !== null)) {
        valid = true;
    } else {
        valid = false;
    }
    return valid;
}

// Set default variation modal
function setModal_addVariation() {
    $('#defModel-content').html("<div class=\"modal-header\">\n" +
            "                    <h5>Confirm request</h5>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <p class=\"fw-normal fs-5\">Are you sure, do you want to add this variation !</p>\n" +
            "                        </div>\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6\">Variation ID</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#variationId').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6\">Name</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#variationName').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row \">\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">Category</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#categoryId-addVariation').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer \">\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-modelConfirm-addVariation\">Confirm</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Close</button>\n" +
            "                </div>");
    // Add event listner to the defModal confirm button
    $('#btn-modelConfirm-addVariation').click(async function () {
        $('#defModel-content').html(loadingInfo_addVariation);
        $('#defModel-content').html(await saveVariation());
        $('#variationName').val("");
    });
}

async function saveVariation() {
    return new Promise(function (resolve, reject) {
        let formElement = document.getElementById('form-addVariation');
        let formData = new FormData(formElement);
        console.log("Called !");
        $.ajax({
            type: "POST",
            url: "SaveVariation",
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