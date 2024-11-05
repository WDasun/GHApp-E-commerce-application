var loadingInfo_variationList = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

// Variation list button listner
$(document).ready(function () {
    $("#variationList").click(function () {
        setNormalMDL();
        setRightMenu_variationList();
    });
});

// Adding variationList div to the rightn menu
async function setRightMenu_variationList() {
    console.log('setRightMenu_variationList() called !');
    try {
        $('#div-rightmenu').html(loadingInfo_variationList);
        $('#div-rightmenu').html("                    <!-- Right menu div content -->\n" +
                "                    <!-- Change point # # # [div Id]-->\n" +
                "                    <div class=\"container-fluid\" id=\"VariationList\">\n" +
                "                        <!-- Head -->\n" +
                "                        <div class=\"row shadow div-leftmenu-head\">\n" +
                "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
                "                                <!-- Change point # # # [Text]-->\n" +
                "                                <p class=\"m-0 txt-rightmenu-description\">Variation List</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
                "                                <!-- Change point # # # [Method]-->\n" +
                "                                <button class=\"btn-refreash-variationList me-3 p-0 py-2 px-3 border border-1 btn btn-light\" id=\"btn-refreash-variationList\"><i class=\"fa-solid fa-arrows-rotate fa-lg\"></i></button>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- Body -->\n" +
                "                        <div class=\"row shadow mt-3 pt-3 pb-4 div-leftmenu-body\">\n" +
                "                            <div class=\"col px-4\">\n" +
                "                                <table class=\"table table-hover align-middle\">\n" +
                "                                    <thead>\n" +
                "                                        <tr>\n" +
                "                                            <th scope=\"col\">Variation ID</th>\n" +
                "                                            <th scope=\"col\">Variation name</th>\n" +
                "                                            <th scope=\"col\">Category</th>\n" +
                "                                            <th scope=\"col\"></th>\n" +
                "                                        </tr>\n" +
                "                                    </thead>\n" +
                "                                    <tbody id=\"table-body\">\n" +
                await generateVariationList__variationList() +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>");
        bindingBtnClickMainDiv__variationList();
    } catch (error) {
        alert("Error : " + error);
    }
}

// Generate variation list
async function generateVariationList__variationList() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateVariationList",
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
function bindingBtnClickMainDiv__variationList() {
    $('#btn-refreash-variationList').click(function () {
        setRightMenu_variationList();
    });
}

// Set and open modal
function clickOnUpdateVariation(id) {
    setModal_updateVariation(id);
}

// Set default variation update modal
async function setModal_updateVariation(variationId) {

    try {
        $('#defModel-content').html(loadingInfo_variationList);
        $('#defModel').modal('show');
        let data = await generateVariationUpdateMDLData(variationId);
        $('#defModel-content').html("                <div class=\"modal-header\">\n" +
                "                    <h5>Update Variation</h5>\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n" +
                "                    <form id=\"form-update-variationList\">\n" +
                "                        <div class=\"container-fluid\">\n" +
                "                            <div>\n" +
                "                                <label for=\"txt-variationId-update\" class=\"form-label\">Variation ID</label>\n" +
                "                                <input type=\"text\" class=\"form-control\" id=\"txt-variationId-update\" name=\"txt-variationId-update\" value=\"" + data.variationId + "\" readonly>\n" +
                "                            </div>\n" +
                "                            <div class=\"was-validated mt-2\">\n" +
                "                                <label for=\"txt-variationName-update\" class=\"form-label\">Variation name</label>\n" +
                "                                <input type=\"text\" class=\"form-control\" id=\"txt-variationName-update\" value=\"" + data.variationName + "\" name=\"txt-variationName-update\" placeholder=\"Enter new name\" required>\n" +
                "                            </div>\n" +
                "                            <div class=\"mt-2\">\n" +
                "                                <label for=\"select-categoryVariation-update\" class=\"form-label lbl-leftMenu-body\">Select category</label>\n" +
                "                                <select class=\"form-select\" aria-label=\"Default select example\" name=\"select-categoryVariation-update\" id=\"select-categoryVariation-update\">\n" +
                data.categoryListHTML +
                "                </select>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </form>\n" +
                "                </div>\n" +
                "                <div class=\"modal-footer \">\n" +
                "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-updateVariation\">Update</button>\n" +
                "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
                "                </div>");
        // Add event listner to the defModal confirm button
        $('#btn-model-updateVariation').click(async function () {
            if (validDataCheck_variationUpdate()) {
                showConfirmSubModal(variationId);
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
async function showConfirmSubModal(variationId) {
    $('#subModel-content').html("                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\"><i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Confirm</h5>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col\">\n" +
            "                                <p class=\"m-0 fs-5 fw-normal\">Do you want to Update variation : <span class=\"fw-bold\">" + variationId + "</span> !</p>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer\">\n" +
            "                    <button class=\"btn btn-danger\" style=\"width: 100px;\" id=\"btn-subModal-confirm\" data-bs-dismiss=\"modal\">Confirm</button>\n" +
            "<button class=\"btn btn-danger \" style=\"width: 100px; \" id=\"btn-subModal-cancel\">Cancel</button>\n" +
            "                </div>");
    $('#btn-subModal-confirm').click(async function () {
        $('#subModel').modal('hide');
        $('#defModel').modal('show');
        $('#defModel-content').html(await updateVariation()); 
        await setRightMenu_variationList();
    });
    $('#btn-subModal-cancel').click(async function () {
        $('#subModel').modal('hide');
        $('#defModel').modal('show');
    });
    
}

async function updateVariation() {
    return new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-update-variationList');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "UpdateVariation",
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

async function generateVariationUpdateMDLData(variationId) {
    return new Promise(function (resolve, reject) {

        $.ajax({
            type: "POST",
            url: "GenerateVariationUpdateMDLData",
            data: {
                "variationId": variationId
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
function validDataCheck_variationUpdate() {
    let valid = false;

    if ($('#txt-variationName-update').val() !== '') {
        valid = true;
    } else {
        valid = false;
    }
    return valid;
}