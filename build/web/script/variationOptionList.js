var loadingInfo_variationOptionList = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

// Variation option list button listner
$(document).ready(function () {

    $("#variationOptionList").click(function () {
        setNormalMDL();
        setRightMenu_variationOptionList();
    });
});

// Adding variationOptionList div to the rightn menu
async function setRightMenu_variationOptionList() {
    try {
        $('#div-rightmenu').html(loadingInfo_variationOptionList);
        $('#div-rightmenu').html(" <!-- Right menu div content -->\n" +
                "                    <!-- Change point # # # [div Id]-->\n" +
                "                    <div class=\"container-fluid\" id=\"variationOptionList\">\n" +
                "                        <!-- Head -->\n" +
                "                        <div class=\"row shadow div-leftmenu-head\">\n" +
                "                            <div class=\"col ps-3 d-flex align-items-center justify-content-start\">\n" +
                "                                <!-- Change point # # # [Text]-->\n" +
                "                                <p class=\"m-0 txt-rightmenu-description\">Variation option List</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"col-9 d-flex align-items-center justify-content-end\">\n" +
                "<select class=\"form-select me-2\" aria-label=\"Category Type\" id=\"categorySelect_variationOptionList\">\n"
                + await generateCategoryListSearch__variationList()
                + "</select>" +
                "<select class=\"form-select me-2\" aria-label=\"Variation for category\" id=\"variationSelect_variationOptionList\" disabled>\n"
                + "</select>" +
                "<button class=\"btn btn-primary me-2\" id=\"variationOptionSearchBtn_variationOptionList\">Search</button>" +
                "                                <!-- Change point # # # [Method]-->\n" +
                "                                <button class=\"btn-categoryList-refresh me-3 p-0 py-2 px-3 border border-1 btn btn-light\" id=\"btn-refresh-variationOption\"><i class=\"fa-solid fa-arrows-rotate fa-lg\"></i></button>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- Body -->\n" +
                "                        <div class=\"row shadow mt-3 pt-3 pb-4 div-leftmenu-body\">\n" +
                "                            <div class=\"col px-4\">\n" +
                "                                <table class=\"table table-hover align-middle\">\n" +
                "                                    <thead>\n" +
                "                                        <tr>\n" +
                "                                            <th scope=\"col\">Option ID</th>\n" +
                "                                            <th scope=\"col\">Value</th>\n" +
                "                                            <th scope=\"col\">Variation</th>\n" +
                "                                            <th scope=\"col\"></th>\n" +
                "                                        </tr>\n" +
                "                                    </thead>\n" +
                "                                    <tbody id=\"table-body-variationOptionList\">\n" +
                await generateVariationOptionList__variationList() +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>");
        bindingBtnClickMainDiv__variationOptionList();
        $('#categorySelect_variationOptionList').change(async function () {
            $('#variationSelect_variationOptionList').prop("disabled", false);
            let categoryId = $('#categorySelect_variationOptionList').val();
            $('#variationSelect_variationOptionList').html(await generateVariationListSearch__variationList(categoryId));
        });
        //Variation Options search
        $('#variationOptionSearchBtn_variationOptionList').click(async function () {
            let categoryId = $('#categorySelect_variationOptionList').val();
            let variationId = $('#variationSelect_variationOptionList').val();
            $('#table-body-variationOptionList').html(await searchVariationOption_variationOptionList(categoryId, variationId));
        });
    } catch (error) {
        alert("Error : " + error);
    }
}

// Generate variation option list
async function generateVariationOptionList__variationList() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateVariationOptionList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

async function generateCategoryListSearch__variationList() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateCategoryListVariationOptionSearch",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

async function generateVariationListSearch__variationList(categoryId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateVariationListForCategory",
            data: {
                "categoryId": categoryId
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

async function searchVariationOption_variationOptionList(categoryId, variationId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "SearchVariationOption",
            data: {
                "categoryId": categoryId,
                "variationId": variationId
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

// Add Event listners to new Div buttons in head
function bindingBtnClickMainDiv__variationOptionList() {
    $('#btn-refresh-variationOption').click(function () {
        setRightMenu_variationOptionList();
    });
}

// Set and open modal
function clickOnUpdateVariationOption(id) {
    setModal_updateVariationOption(id);
    $('#defModel').modal('show');
}

// Set default variation update modal
async function setModal_updateVariationOption(variationOptionId) {

    $('#defModel-content').html(loadingInfo_variationOptionList);

    try {
        let data = await generateVariationOptionUpdateMDLData(variationOptionId);
        $('#defModel-content').html("<div class=\"modal-header\">\n" +
                "                    <h5>Update Option</h5>\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n" +
                "                    <form id=\"form-update-variationOptionList\">\n" +
                "                        <div class=\"container-fluid\">\n" +
                "                            <div>\n" +
                "                                <label for=\"txt-variationOptionId-update\" class=\"form-label\">Variation Option ID</label>\n" +
                "                                <input type=\"text\" class=\"form-control\" id=\"txt-variationOptionId-update\" name=\"txt-variationOptionId-update\" value=\"" + data.variationOptionId + "\" readonly>\n" +
                "                            </div>\n" +
                "                            <div class=\"was-validated mt-2\">\n" +
                "                                <label for=\"txt-variationOptionName-update\" class=\"form-label\">Value</label>\n" +
                "                                <input type=\"text\" class=\"form-control\" id=\"txt-variationOptionName-update\" value=\"" + data.variationOptionValue + "\" name=\"txt-variationOptionName-update\" placeholder=\"Enter new value\" required>\n" +
                "                            </div>\n" +
                "                            <div class=\"mt-2\">\n" +
                "                                <label for=\"select-variation-update\" class=\"form-label lbl-leftMenu-body\">Select Variation</label>\n" +
                "                                <select class=\"form-select\" aria-label=\"Default select example\" name=\"select-variation-update\" id=\"select-variation-update\">\n" +
                data.variationListHTML +
                "                </select>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </form>\n" +
                "                </div>\n" +
                "                <div class=\"modal-footer \">\n" +
                "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-updateVariationOption\">Update</button>\n" +
                "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
                "                </div>");
        // Add event listner to the defModal confirm button
        $('#btn-model-updateVariationOption').click(async function () {
            if (validDataCheck_variationOptionUpdate()) {
                showConfirmSubModal_variationOption(variationOptionId);
                $('#defModel').modal('hide');
                $('#subModel').modal('show');
            } else {
                alert("All Fields must completed !");
            }
        });
    } catch (error) {
        alert(error);
    }

}

// Set sub modal content and set event listner for the "Confirm" button
// when confirm button clicked, send data to the server, hide the subModal and set response to the defModal
async function showConfirmSubModal_variationOption(variationOptionId) {
    $('#subModel-content').html("                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\"><i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Confirm</h5>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col\">\n" +
            "                                <p class=\"m-0 fs-5 fw-normal\">Do you want to Update variation option : <span class=\"fw-bold\">" + variationOptionId + "</span> !</p>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer\">\n" +
            "                    <button class=\"btn btn-danger\" style=\"width: 100px;\" id=\"btn-subModal-confirm-variationOption\" data-bs-dismiss=\"modal\">Confirm</button>\n" +
            "                </div>");
    $('#btn-subModal-confirm-variationOption').click(async function () {
        $('#subModel').modal('hide');
        $('#defModel').modal('show');
        $('#defModel-content').html(await updateVariationOption());
        setRightMenu_variationOptionList();
    });
    $('#btn-subModal-cancel').click(async function () {
        $('#subModel').modal('hide');
        $('#defModel').modal('show');
    });
}

async function updateVariationOption() {
    return new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-update-variationOptionList');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "UpdateOption",
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

async function generateVariationOptionUpdateMDLData(variationOptionId) {
    return new Promise(function (resolve, reject) {

        $.ajax({
            type: "POST",
            url: "GenerateVariationOptionUpdateMDLData",
            data: {
                "variationOptionId": variationOptionId
            },
            success: function (response) {
                let data = JSON.parse(response);
                resolve(data);
            },
            error: function (error) {
                reject(error.responseText);
            }
        });

    });
}

// Data validation
function validDataCheck_variationOptionUpdate() {
    let valid = false;

    if ($('#txt-variationOptionName-update').val() !== '') {
        valid = true;
    } else {
        valid = false;
    }
    return valid;
}