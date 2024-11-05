// Other 
var imageInputOneChangedItemList = false;
var imageInputTwoChangedItemList = false;
var imageInputThreeChangedItemList = false;

var loadingInfoItemList = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

function setLargMDLItemList() {
    document.getElementById("defModel").innerHTML = "<div class=\"modal-dialog modal-dialog-centered modal-xl\">\n" +
            "                <div class=\"modal-content\" id=\"defModel-content\">\n" +
            "\n" +
            "                </div>\n" +
            "            </div>";
}

function setNormalMDLItemList() {
    document.getElementById("defModel").innerHTML = "<div class=\"modal-dialog modal-dialog-centered\">\n" +
            "                <div class=\"modal-content\" id=\"defModel-content\">\n" +
            "\n" +
            "                </div>\n" +
            "            </div>";
}

// UpdateMDL image proccessing

function ItemList_BindingChangeEventForIMG() {
    //Image process
    //Hide icon "icn-imageInputOne"
    //Set image for "img-imageInputOne"
    $('#imageInputOne').change(function (event) {
        imageInputOneChangedItemList = true;
        let reader = new FileReader();
        reader.onload = function (event) {
            document.getElementById('icn-imageInputOne').style.display = 'none';
            $('#img-imageInputOne').attr('src', event.target.result);
            $('#img-imageInputOne').show();
        };
        reader.readAsDataURL(this.files[0]);
    });
    //Hide icon "icn-imageInputTwo"
    //Set image for "img-imageInputTwo"
    $('#imageInputTwo').change(function (event) {
        imageInputTwoChangedItemList = true;
        let reader = new FileReader();
        reader.onload = function (event) {
            document.getElementById('icn-imageInputTwo').style.display = 'none';
            $('#img-imageInputTwo').attr('src', event.target.result);
            $('#img-imageInputTwo').show();
        };
        reader.readAsDataURL(this.files[0]);
    });
    //Hide icon "icn-imageInputThree"
    //Set image for "img-imageInputThree"
    $('#imageInputThree').change(function (event) {
        imageInputThreeChangedItemList = true;
        let reader = new FileReader();
        reader.onload = function (event) {
            document.getElementById('icn-imageInputThree').style.display = 'none';
            $('#img-imageInputThree').attr('src', event.target.result);
            $('#img-imageInputThree').show();
        };
        reader.readAsDataURL(this.files[0]);
    });
}

//Img remove btns
//Remove file in "imageInputOne" and "imageInputTwo"
//remove image from "img-imageInputOne" and "img-imageInputTwo" and ""img-imageInputThree""
//Show "icn-imageInputOne" again
function removeItemImgInUpdateItem(imgNum) {
    console.log("Method called !");
    if (imgNum === 1) {
        document.getElementById('imageInputOne').value = '';
        let img = document.getElementById('img-imageInputOne');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInputOne').style.display = 'block';
        imageInputOneChangedItemList = true;
    } else if (imgNum === 2) {
        document.getElementById('imageInputTwo').value = '';
        let img = document.getElementById('img-imageInputTwo');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInputTwo').style.display = 'block';
        imageInputTwoChangedItemList = true;
    } else if (imgNum === 3) {
        document.getElementById('imageInputThree').value = '';
        let img = document.getElementById('img-imageInputThree');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInputThree').style.display = 'block';
        imageInputThreeChangedItemList = true;
    }
}

// ------------- Starting Item list -------------

$(document).ready(function () {

    $("#itemList").click(function () {
        setNormalMDL();
        $("#div-rightmenu").html(loadingInfo);
        addRightMenuItemList();
    });
})

async function addRightMenuItemList() {
    $("#div-rightmenu").html("<!-- Change point # # # [div Id]-->\n" +
            "<div class=\"container-fluid\" id=\"itemList\">\n" +
            "    <!-- Head -->\n" +
            "    <div class=\"row shadow div-leftmenu-head\">\n" +
            "        <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
            "            <!-- Change point # # # [Text]-->\n" +
            "            <p class=\"m-0 txt-rightmenu-description\">Item List</p>\n" +
            "        </div>\n" +
            "        <div class=\"col d-flex align-items-center justify-content-end\">\n" +
            "            <!-- Change point # # # [Method]-->\n" +
            "            <button class=\"btn-categoryList-refresh me-3 p-0 py-2 px-3 border border-1 btn btn-light\" onclick=\"addRightMenuItemList()\"><i class=\"fa-solid fa-arrows-rotate fa-lg\"></i></button>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <!-- Body -->\n" +
            "    <div class=\"row shadow mt-3 pt-3 pb-4 div-leftmenu-body\">\n" +
            "        <div class=\"col px-4\">\n" +
            "            <table class=\"table table-hover align-middle\">\n" +
            "                <thead>\n" +
            "                    <tr>\n" +
            "                        <th scope=\"col\">Item ID</th>\n" +
            "                        <th scope=\"col\">Item Name</th>\n" +
            "                        <th scope=\"col\">Product</th>\n" +
            "                        <th scope=\"col\">SKU</th>\n" +
            "                        <th scope=\"col\">QTY</th>\n" +
            "                        <th scope=\"col\">Price</th>\n" +
            "                        <th scope=\"col\">Status</th>\n" +
            "                        <th scope=\"col\"></th>\n" +
            "                    </tr>\n" +
            "                </thead>\n" +
            "                <tbody id=\"table-body\">\n" +
            await loadItemList() +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>");
}

//Get Product list saved in DB as a html code(for table-body) by Request LoadProductList Servlet
async function loadItemList() {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadItemList",
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

function clickOnUpdateItem(id) {
    setLargMDLItemList();
    $("#defModel-content").html(loadingInfo);
    $('#defModel').modal('show');
    setUpdateItemMDL(id);

}

async function setUpdateItemMDL(id) {

    console.log("here 1");
    $('#defModel-content').html(await generateItemUpdateMDLData(id));

    document.getElementById('icn-imageInputOne').style.display = 'none';
    $('#img-imageInputOne').show();

    document.getElementById('icn-imageInputTwo').style.display = 'none';
    $('#img-imageInputTwo').show();

    document.getElementById('icn-imageInputThree').style.display = 'none';
    $('#img-imageInputThree').show();

    imageInputOneChangedItemList = false;
    imageInputTwoChangedItemList = false;
    imageInputThreeChangedItemList = false;

    ItemList_BindingChangeEventForIMG();

}

// Getting Json object from server. Then add data in that object to the modal
async function generateItemUpdateMDLData(itemId) {
    let myPromise = new Promise(function (resolve, reject) {
        console.log("here 2");
        $.ajax({
            type: "POST",
            url: "GenerateItemUpdateMDLData",
            data: {
                "itemId": itemId
            },
            success: function (response) {
                resolve(response);
                console.log("here 3");
            },
            error: function (error) {
                reject(error);
                console.log("here 4");
            }
        });

    });

    return myPromise;
}
// Check validations
function ItemUpdateConfirm(id) {

    if (updateItemValidation()) {
        setUpdateItemConfirmMDL(id);
        $('#subModel').modal('show');
    } else {
        alert('All fields must be filled !');
    }
}

function setUpdateItemConfirmMDL(id) {
    $('#subModel-content').html(
            "<div class=\"modal-header\">\n" +
            "    <div class=\"d-flex align-items-center justify-content-start\"><i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "        <h5 class=\"m-0 ms-3\">Confirm !</h5>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"modal-body\">\n" +
            "    <div class=\"container-fluid\">\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col\">\n" +
            "                <p class=\"m-0 fs-5 fw-normal\">Confirm update <span class=\"fw-bold\">" + id + "</span>.</p>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"modal-footer\">\n" +
            "        <button class=\"btn btn-primary\" style=\"width: 100px;\" id=\"btn-model-update\" onclick=\"updateItem()\" type=\"button\">Confirm</button>\n" +
            "    <button class=\"btn btn-danger\" style=\"width: 100px;\" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "</div>"
            );
}

// Continue the update process
async function updateItem() {
    $('#subModel').modal('hide');
    try {
        let serverResponse = await sendItemListUpdateReqToSRVR();
        setItemListUpdateResponseMDL(serverResponse);
        addRightMenuItemList();
    } catch (error) {
        setItemListUpdateResponseMDL('Error : [' + error + ']');
    }

}

// Send collected data to the server
async function sendItemListUpdateReqToSRVR() {
    let myPromise = new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-updateItem');
        let formData = new FormData(formElement);

        let optionsArray = [];

        let optionElements = document.getElementsByClassName('variationOption-update');
        for (var i = 0; i < optionElements.length; i++) {
            if(optionElements[i].value){
                optionsArray.push(optionElements[i].value);  
            }
        }

        formData.append("optionsArray",JSON.stringify(optionsArray));

        $.ajax({
            type: "POST",
            url: "UpdateItem",
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

// Response Modal content
function setItemListUpdateResponseMDL(serverResponse) {
    setNormalMDL();
    $("#defModel-content").html(
            "<div class=\"modal-header\">\n" +
            "    <div class=\"d-flex align-items-center justify-content-start\"><i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "        <h5 class=\"m-0 ms-3\">Server response !</h5>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"modal-body\">\n" +
            "    <div class=\"container-fluid\">\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col\">\n" +
            "                <p class=\"m-0 fs-5 fw-normal\">" + serverResponse + "</p>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"modal-footer\">\n" +
            "    <button class=\"btn btn-danger\" style=\"width: 100px;\" data-bs-dismiss=\"modal\">Ok</button>\n" +
            "</div>"
            );
}

// Validate process function
function updateItemValidation() {
    let valid = false;

    //when item update modal loaded there are images already set so imgOne and imgTwo and imgThree has value 1 until img remove or add new image. Those "if" conditions check if new imgs added or removed.
    let imgOne = true;
    let imgTwo = true;
    let imgThree = true;

    let product = $('#selectProductAddItem').val() === null;
    let name = $('#itemName').val() === '';
    let sku = $('#itemSKU').val() === '';
    let qty = $('#itemQTY').val() === '';
    let price = $('#itemPrice').val() === '';

    if (imageInputOneChangedItemList) {
        let imgOneCheck = $('#imageInputOne')[0].files;
        if (imgOneCheck.length > 0) {
            imgOne = true;
        } else {
            imgOne = false;
        }
    }

    if (imageInputTwoChangedItemList) {
        let imgTwoCheck = $('#imageInputTwo')[0].files;
        if (imgTwoCheck.length > 0) {
            imgTwo = true;
        } else {
            imgTwo = false;
        }
    }

    if (imageInputThreeChangedItemList) {
        let imgThreeCheck = $('#imageInputThree')[0].files;
        if (imgThreeCheck.length > 0) {
            imgThree = true;
        } else {
            imgThree = false;
        }
    }

    if (!product &&
            !name &&
            !sku &&
            !qty &&
            !price &&
            imgOne &&
            imgTwo &&
            imgThree) {
        valid = true;
    }

    return valid;
}


// ------------- Product Inactivate Proccess -------------

function clickOnInactivateItem(id) {
    setNormalMDL();
    setInactivateItemMDL(id);
    $('#defModel').modal('show');
}

function setInactivateItemMDL(id) {
    $('#defModel-content').html("<!-- Model content -->\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\">\n" +
            "                        <i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Inactivate Item</h5>\n" +
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
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"inactivateItem('" + id + "')\">Inactivate</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "                </div>");
}

async function inactivateItem(id) {
    try {
        let serverResponse = await sendInactivateItemReqToSRVR(id);
        setItemInactivateResponseMDL(serverResponse);
        addRightMenuItemList();
    } catch (error) {
        setItemInactivateResponseMDL('Error : [' + error + ']');
    }
}

async function sendInactivateItemReqToSRVR(id) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "ChangeStatusItem",
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

function setItemInactivateResponseMDL(serverResponse) {
    $("#defModel-content").html(serverResponse);

}

// ------------- Product Activate Proccess -------------

function clickOnActivateItem(id) {
    setNormalMDL();
    setActivateItemMDL(id);
    $('#defModel').modal('show');
}

function setActivateItemMDL(id) {
    $('#defModel-content').html("<!-- Model content -->\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\">\n" +
            "                        <i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Activate Item</h5>\n" +
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
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"ActivateItem('" + id + "')\">Activate</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "                </div>");
}

async function ActivateItem(id) {
    try {
        let serverResponse = await sendItemActivateReqToSRVR(id);
        setItemActivateResponseMDL(serverResponse);
        addRightMenuItemList();
    } catch (error) {
        setItemActivateResponseMDL('Error : [' + error + ']');
    }
}

async function sendItemActivateReqToSRVR(id) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "ChangeStatusItem",
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

function setItemActivateResponseMDL(serverResponse) {
    $("#defModel-content").html(serverResponse);

}