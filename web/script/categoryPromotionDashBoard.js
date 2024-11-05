var loadingInfo_categoryPromotion = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";
// Load Promotion List
async function categoryPromotionDiv_dashBoard() {
    console.log('called !')
    $("#div-rightmenu").html(loadingInfo_categoryPromotion);
    $("#div-rightmenu").html("<div class=\"container-fluid\" id=\"categoryList\">\n" +
            "                        <!-- Head -->\n" +
            "                        <div class=\"row shadow div-leftmenu-head\">\n" +
            "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
            "                                <p class=\"m-0 txt-rightmenu-description\">Category Promotion</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
            "<button type=\"button\" class=\"btn btn-primary w-25 h-50 me-4\" onclick=\"onclickAddNewPromotion_dashboard()\">New Promotion</button>" +
            "<button class=\"btn-categoryList-refresh me-3 p-0 py-2 px-3 border border-1 btn btn-light\" onclick=\"categoryPromotionDiv_dashBoard()\"><i class=\"fa-solid fa-arrows-rotate fa-lg\"></i></button>" +
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
            "                                            <th scope=\"col\">Category</th>" +
            "                                            <th scope=\"col\">Start</th>\n" +
            "                                            <th scope=\"col\">End</th>\n" +
            "                                            <th scope=\"col\"></th>\n" +
            "                                        </tr>\n" +
            "                                    </thead>\n" +
            "                                    <tbody id=\"table-body\">\n" +
            await loadPromotionList_dashboard() +
            "                                    </tbody>\n" +
            "                                </table>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>");
}

async function loadPromotionList_dashboard() {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadPromotionList",
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

// Add new promotion
async function onclickAddNewPromotion_dashboard() {
    $('#defModel-content').html(`
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New Category Promotion</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-6">
                    <label for="newPromotionName" class="form-label">Name</label>
                    <input type="text" class="form-control mb-1" id="newPromotionName">
                    <label for="newPromotionDescription" class="form-label">Description</label>
                    <input type="text" class="form-control mb-1" id="newPromotionDescription">
                    <label for="newPromotionDiscountRate" class="form-label">Discount Rate</label>
                    <input type="text" class="form-control mb-1" id="newPromotionDiscountRate">
                    <label for="newPromotionDiscountRate" class="form-label">Related Category</label>
                    <select class="form-select mb-1" aria-label="CategoryList" id="newPromotionCategory">
                        ${await loadCategoryList_newPromotion()}
                      </select>
                    <label class="form-label">Start Date and Time</label>
                    <input type="date" class="form-control mb-1" id="newPromotionStartDate">
                    <input type="time" class="form-control mb-1" id="newPromotionStartTime">
                    <label class="form-label">End Date and Time</label>
                    <input type="date" class="form-control mb-1" id="newPromotionEndDate">
                    <input type="time" class="form-control mb-1" id="newPromotionEndTime">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="saveBtn-newPromotion">Save</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    $('#defModel').modal('show');
    $('#saveBtn-newPromotion').click(async function () {
        let newPromotionName = $('#newPromotionName').val();
        let newPromotionDescription = $('#newPromotionDescription').val();
        let newPromotionDiscountRate = $('#newPromotionDiscountRate').val();
        let newPromotionCategory = $('#newPromotionCategory').val();
        let newPromotionStartDate = $('#newPromotionStartDate').val();
        let newPromotionStartTime = $('#newPromotionStartTime').val();
        let newPromotionEndDate = $('#newPromotionEndDate').val();
        let newPromotionEndTime = $('#newPromotionEndTime').val();
        $('#defModel-content').html(loadingInfo_categoryPromotion);
        $('#defModel-content').html(`
            <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <p class="text-center m-0">
                    ${await savePromotionSRVR(
                newPromotionName,
                newPromotionDescription,
                newPromotionDiscountRate,
                newPromotionCategory,
                newPromotionStartDate,
                newPromotionStartTime,
                newPromotionEndDate,
                newPromotionEndTime
                )}
                    </p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
         `);
        await categoryPromotionDiv_dashBoard();
    });
}

async function loadCategoryList_newPromotion() {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadCategoryListNewPromotion",
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

async function savePromotionSRVR(
        newPromotionName,
        newPromotionDescription,
        newPromotionDiscountRate,
        newPromotionCategory,
        newPromotionStartDate,
        newPromotionStartTime,
        newPromotionEndDate,
        newPromotionEndTime) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "SaveCategoryPromotion",
            data: {
                "newPromotionName": newPromotionName,
                "newPromotionDescription": newPromotionDescription,
                "newPromotionDiscountRate": newPromotionDiscountRate,
                "newPromotionCategory": newPromotionCategory,
                "newPromotionStartDate": newPromotionStartDate,
                "newPromotionStartTime": newPromotionStartTime + ":00",
                "newPromotionEndDate": newPromotionEndDate,
                "newPromotionEndTime": newPromotionEndTime + ":00"
            },
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

// Delete promotion
async function clickOnDeletePromotion(promotionId, categoryId) {
    $('#defModel-content').html(`
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <p class="text-center m-0">Do you want to delete this Promotion ? </p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-primary w-25" id="deleteConfirmBtn-promotion">Delete</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Cancel</button>
                </div>
    `);
    $('#defModel').modal('show');
    $('#deleteConfirmBtn-promotion').click(async function () {
        $('#defModel-content').html(loadingInfo_categoryPromotion);
        $('#defModel-content').html(`
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <p class="text-center m-0">${await deletePromotionSRVR_dashboard(promotionId, categoryId)}</p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
        await categoryPromotionDiv_dashBoard();
    });

}

async function deletePromotionSRVR_dashboard(promotionId, categoryId) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "DeleteCategoryPromotion",
            data: {
                "promotionId": promotionId,
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
    return await myPromise;
}

// Update promotion
async function clickOnUpdatePromotion(promotionId) {
    $('#defModel-content').html(await loadPromotionUpdateModalSRVR_dashboard(promotionId));
    $('#defModel').modal('show');
    $('#updateBtn-newPromotion').click(async function () {
        let updatePromotionId = $('#updatePromotionId').val();
        let updatePromotionName = $('#updatePromotionName').val();
        let updatePromotionDescription = $('#updatePromotionDescription').val();
        let updatePromotionDiscountRate = $('#updatePromotionDiscountRate').val();
        let updatePromotionStartDate = $('#updatePromotionStartDate').val();
        let updatePromotionStartTime = $('#updatePromotionStartTime').val();
        let updatePromotionEndDate = $('#updatePromotionEndDate').val();
        let updatePromotionEndTime = $('#updatePromotionEndTime').val();
        $('#defModel-content').html(loadingInfo_categoryPromotion);
        $('#defModel-content').html(`
            <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <p class="text-center m-0">
                    ${await updatePromotionSRVR(
                updatePromotionId,
                updatePromotionName,
                updatePromotionDescription,
                updatePromotionDiscountRate,
                updatePromotionStartDate,
                updatePromotionStartTime,
                updatePromotionEndDate,
                updatePromotionEndTime
                )}
                    </p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
         `);
        await categoryPromotionDiv_dashBoard();
    });
}

async function updatePromotionSRVR(
        updatePromotionId,
        updatePromotionName,
        updatePromotionDescription,
        updatePromotionDiscountRate,
        updatePromotionStartDate,
        updatePromotionStartTime,
        updatePromotionEndDate,
        updatePromotionEndTime
        ) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "UpdateCategoryPromotion",
            data: {
                "updatePromotionId": updatePromotionId,
                "updatePromotionName": updatePromotionName,
                "updatePromotionDescription": updatePromotionDescription,
                "updatePromotionDiscountRate": updatePromotionDiscountRate,
                "updatePromotionStartDate": updatePromotionStartDate,
                "updatePromotionStartTime": updatePromotionStartTime + ":00",
                "updatePromotionEndDate": updatePromotionEndDate,
                "updatePromotionEndTime": updatePromotionEndTime + ":00"
            },
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

async function loadPromotionUpdateModalSRVR_dashboard(promotionId) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadPromotionUpdateModal",
            data: {
                "promotionId": promotionId
            },
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