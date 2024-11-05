var loadingInfo_addVariationOption = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
    "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
    "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
    "                                <span class=\"visually-hidden\">Loading...</span>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "                    </div>";

// Add veriationOption button listner
$(document).ready(function() {

    $("#addVariationOption").click(function() {
        setRightMenu_addVariationOption();
    });
});

// Adding addVariationOption div to the rightn menu
async function setRightMenu_addVariationOption() {
    try {
        $('#div-rightmenu').html(loadingInfo_addVariation);
        $('#div-rightmenu').html("<!-- Div body -->\n" +
            "                    <div class=\"container-fluid\" id=\"div-addVariationOption\">\n" +
            "                        <!-- Head -->\n" +
            "                        <div class=\"row shadow div-leftmenu-head\">\n" +
            "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
            "                                <p class=\"m-0 txt-rightmenu-description\">Add Variation option</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
            "                                <button class=\"btn btn-secondary me-2 btn-leftmenu-head\" id=\"btn-discard-addVariationOption\">Discard</button>\n" +
            "                                <button class=\"btn btn-primary me-2 btn-leftmenu-head\" id=\"btn-addVariationOption\">Add Option</button>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!-- Body -->\n" +
            "                        <form id=\"form-addVariationOption\">\n" +
            "                            <div class=\"row mt-3\">\n" +
            "                                <div class=\"col-7 p-0 pe-3\">\n" +
            "                                    <div class=\"pb-4 pt-3 px-3 shadow div-leftmenu-body\">\n" +
            "                                        <div>\n" +
            "                                            <label for=\"option-id\" class=\"form-label lbl-leftMenu-body\">Variation ID</label>\n" +
            "                                            <input type=\"text\" class=\"form-control\" name=\"option-id\" id=\"option-id\" value=\"---\" readonly>\n" +
            "                                        </div>\n" +
            "                                        <div class=\"mt-2 was-validated\">\n" +
            "                                            <label for=\"option-name\" class=\"form-label lbl-leftMenu-body\">Option Name</label>\n" +
            "                                            <input type=\"text\" class=\"form-control\" name=\"option-name\" id=\"option-name\" required>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                                <div class=\"col-5 pt-3 shadow div-leftmenu-body\">\n" +
            "                                    <label for=\"select-variation\" class=\"form-label lbl-leftMenu-body\">Select Variation</label>\n" +
            "                                    <select class=\"form-select\" aria-label=\"Default select example\" name=\"select-variation\" id=\"select-variation\">\n" +
            await generateVariationList__addVariationOption() +
            "</select>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </form>\n" +
            "                    </div>");
        bindingBtnClickMainDiv__addVariationOption();
    } catch (error) {
        alert("Error : " + error);
    }
}

// Generate Variation list
async function generateVariationList__addVariationOption() {
    return new Promise(function(resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateVariationListForOption",
            success: function (response) {
                resolve(response)
            },
            error: function (error) {
                reject(error)
            }
        });
    });
}

// Add Event listners to new Div buttons in head
function bindingBtnClickMainDiv__addVariationOption() {
    $('#btn-addVariationOption').click(function() {
        if (validDataCheck_variationOption()) {
            setModal_addVariationOption();
            $('#defModel').modal('show');
        } else {
            alert('All fields must completed !');
        }
    });
    $('#btn-discard-addVariationOption').click(function() {
        setRightMenu_addVariationOption();
    });
}

// Data validation
function validDataCheck_variationOption() {
    let valid = false;

    if (($('#option-name').val() !== '') && ($('#select-variation').val() !== null)) {
        valid = true;
    } else {
        valid = false;
    }
    return valid;
}

// Set default variation option modal
function setModal_addVariationOption() {
    $('#defModel-content').html("<div class=\"modal-header\">\n" +
        "                    <h5>Confirm request</h5>\n" +
        "                </div>\n" +
        "                <div class=\"modal-body\">\n" +
        "                    <div class=\"container-fluid\">\n" +
        "                        <div class=\"row\">\n" +
        "                            <p class=\"fw-normal fs-5\">Are you sure, do you want to add this Option !</p>\n" +
        "                        </div>\n" +
        "                        <div class=\"row\">\n" +
        "                            <div class=\"col-4\">\n" +
        "                                <p class=\"fw-normal fs-6\">Option ID</p>\n" +
        "                            </div>\n" +
        "                            <div class=\"col-4\">\n" +
        "                                <p class=\"fw-normal fs-6 \">: ---</p>\n" +
        "                            </div>\n" +
        "                            <div class=\"col-4 \"></div>\n" +
        "                        </div>\n" +
        "                        <div class=\"row\">\n" +
        "                            <div class=\"col-4\">\n" +
        "                                <p class=\"fw-normal fs-6\">Name</p>\n" +
        "                            </div>\n" +
        "                            <div class=\"col-4\">\n" +
        "                                <p class=\"fw-normal fs-6 \">: " + $('#option-name').val() + "</p>\n" +
        "                            </div>\n" +
        "                            <div class=\"col-4 \"></div>\n" +
        "                        </div>\n" +
        "                        <div class=\"row \">\n" +
        "                            <div class=\"col-4 \">\n" +
        "                                <p class=\"fw-normal fs-6 \">Variation</p>\n" +
        "                            </div>\n" +
        "                            <div class=\"col-4 \">\n" +
        "                                <p class=\"fw-normal fs-6 \">: " + $('#select-variation').val() + "</p>\n" +
        "                            </div>\n" +
        "                            <div class=\"col-4 \"></div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"modal-footer \">\n" +
        "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-modelConfirm-addVariationOption\">Confirm</button>\n" +
        "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Close</button>\n" +
        "                </div>");
    // Add event listner to the defModal confirm button
    $('#btn-modelConfirm-addVariationOption').click(async function() {
        $('#defModel-content').html(loadingInfo_addVariationOption);
        $('#defModel-content').html(await save_variationOption());
    });
}

async function save_variationOption() {
    return new Promise(function(resolve, reject) {
        
        let formElement = document.getElementById('form-addVariationOption');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "SaveOption",
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