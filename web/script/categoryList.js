// ------------- Starting category list -------------

var loadingInfo_categoryList = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
    "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
    "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
    "                                <span class=\"visually-hidden\">Loading...</span>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "                    </div>";

$(document).ready(function () {
    $("#categoryList").click(function () {
        setNormalMDL();
        addRightMenuCategoryList();
    });
})

async function addRightMenuCategoryList() {
    $("#div-rightmenu").html(loadingInfo_categoryList);
    $("#div-rightmenu").html("<div class=\"container-fluid\" id=\"categoryList\">\n" +
            "                        <!-- Head -->\n" +
            "                        <div class=\"row shadow div-leftmenu-head\">\n" +
            "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
            "                                <p class=\"m-0 txt-rightmenu-description\">Categories</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
            "<button class=\"btn-categoryList-refresh me-3 p-0 py-2 px-3 border border-1 btn btn-light\" onclick=\"addRightMenuCategoryList()\"><i class=\"fa-solid fa-arrows-rotate fa-lg\"></i></button>" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!-- Body -->\n" +
            "                        <div class=\"row shadow mt-3 pt-3 pb-4 div-leftmenu-body\">\n" +
            "                            <div class=\"col px-4\">\n" +
            "                                <table class=\"table table-hover align-middle\">\n" +
            "                                    <thead>\n" +
            "                                        <tr>\n" +
            "                                            <th scope=\"col\">ID</th>\n" +
            "                                            <th scope=\"col\">Name</th>\n" +
            "                                            <th scope=\"col\">Parent category ID</th>" +
            "                                            <th scope=\"col\">Status</th>\n" +
            "                                            <th scope=\"col\"></th>\n" +
            "                                        </tr>\n" +
            "                                    </thead>\n" +
            "                                    <tbody id=\"table-body\">\n" + await loadCategoryList() +
            "                                    </tbody>\n" +
            "                                </table>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>");
}

async function loadCategoryList() {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadCategoryList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });

    return await myPromise;

}


// ------------- Update Proccess -------------

function clickOnUpdateCategory(categoryId) {
    setUpdateCategoryMDL(categoryId);
    $('#defModel').modal('show');
}

async  function setUpdateCategoryMDL(categoryId) {
    try {
        let modalData = await generateUpdateModalData_addCategory(categoryId);
        $('#defModel-content').html("<div class=\"modal-header\">\n" +
                "                    <h5>Update category</h5>\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n"
                + "                    <form id=\"form-update-categoryList\">\n"
                + "                        <div class=\"container-fluid\">\n"
                + "                            <div>\n"
                + "                                <label for=\"txt-categoryId-update\" class=\"form-label\">Category ID</label>\n"
                + "                                <input type=\"text\" class=\"form-control\" id=\"txt-categoryId-update\" name=\"txt-categoryId-update\" value=\"" + modalData.categoryId + "\" readonly>\n"
                + "                            </div>\n"
                + "                            <div class=\"was-validated mt-2\">\n"
                + "                                <label for=\"txt-categoryName-update\" class=\"form-label\">Category name</label>\n"
                + "                                <input type=\"text\" class=\"form-control\" id=\"txt-categoryName-update\" value=\"" + modalData.categoryName + "\" name=\"txt-categoryName-update\" placeholder=\"Enter new value\" required>\n"
                + "                            </div>\n"
                + "                            <div class=\"mt-2\">\n"
                + "                                <label for=\"select-variation-update\" class=\"form-label lbl-leftMenu-body\">Select Parent Category</label>\n"
                + "                                <select class=\"form-select\" aria-label=\"Default select example\" name=\"select-parentCategory-update\" id=\"select-variation-update\">\n"
                + modalData.parentCategoryListHTML
                + "                </select>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </form>\n"
                + "                </div>" +
                "                <div class=\"modal-footer \">\n" +
                "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"updateCategory('" + modalData.categoryId + "')\">Update</button>\n" +
                "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
                "                </div>");
    } catch (e) {
        alert(e);
    }

}

async function generateUpdateModalData_addCategory(categoryId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "generateCategoryUpdateMDLData",
            data: {
                "categoryId": categoryId
            },
            success: function (response) {
                let data = JSON.parse(response);
                resolve(data);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

async function updateCategory(id) {
    if (updateCategoryValidation()) {
        try {
            let newName = $('#txt-newCategoryName').val();
            let serverResponse = await sendUpdateReqToSRVR(id, newName);
            setUpdateResponseMDL(serverResponse);
            addRightMenuCategoryList();
        } catch (error) {
            setUpdateResponseMDL('Error : [' + error + ']');
        }
    } else {
        setUpdateResponseMDL('Field "name" must be filled !');
    }
}

async function sendUpdateReqToSRVR(id, newName) {
    let myPromise = new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-update-categoryList');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "UpdateCategory",
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

    return myPromise;
}

function setUpdateResponseMDL(serverResponse) {
    $("#defModel-content").html(serverResponse);
}

function updateCategoryValidation() {
    let valid = true;
    let name = $('#txt-newCategoryName').val();
    if (name === '') {
        valid = false;
    }

    return valid;
}


// ------------- Category Inactivate Proccess -------------

function clickOnInactivateCategory(id) {
    setInactivateCategoryMDL(id);
    $('#defModel').modal('show');
}

function setInactivateCategoryMDL(id) {
    $('#defModel-content').html("<!-- Model content -->\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\">\n" +
            "                        <i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Inactivate Category</h5>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col\">\n" +
            "                                <p class=\"m-0 fs-5 fw-normal\">Are sure, you want to inactivate : <span class=\"fw-bold\">" + id + "</span> ?</p>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer \">\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"inactivateCategory('" + id + "')\">Inactivate</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "                </div>");
}

async function inactivateCategory(id) {
    try {
        let serverResponse = await sendInactivateReqToSRVR(id);
        setInactivateResponseMDL(serverResponse);
        addRightMenuCategoryList();
    } catch (error) {
        setInactivateResponseMDL('Error : [' + error + ']');
    }
}

async function sendInactivateReqToSRVR(id) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "changeStatusCategory",
            data: {
                "id": id,
                "newValue": "false"
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

function setInactivateResponseMDL(serverResponse) {
    $("#defModel-content").html(serverResponse);

}

// ------------- Category Activate Proccess -------------

function clickOnActivateCategory(id) {
    setActivateCategoryMDL(id);
    $('#defModel').modal('show');
}

function setActivateCategoryMDL(id) {
    $('#defModel-content').html("<!-- Model content -->\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\">\n" +
            "                        <i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Activate Category</h5>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col\">\n" +
            "                                <p class=\"m-0 fs-5 fw-normal\">Are sure, you want to activate : <span class=\"fw-bold\">" + id + "</span> ?</p>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer \">\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"ActivateCategory('" + id + "')\">Activate</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "                </div>");
}

async function ActivateCategory(id) {
    try {
        let serverResponse = await sendActivateReqToSRVR(id);
        setActivateResponseMDL(serverResponse);
        addRightMenuCategoryList();
    } catch (error) {
        setActivateResponseMDL('Error : [' + error + ']');
    }
}

async function sendActivateReqToSRVR(id) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "changeStatusCategory",
            data: {
                "id": id,
                "newValue": "true"
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

function setActivateResponseMDL(serverResponse) {
    $("#defModel-content").html(serverResponse);

}