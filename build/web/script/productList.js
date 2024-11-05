// Other 
var imageInputOneChanged = false;
var imageInputTwoChanged = false;

var loadingInfo = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

// UpdateMDL image proccessing

function ProductList_BindingChangeEventForIMG() {
    //Image process
    //Hide icon "icn-imageInputOne"
    //Set image for "img-imageInputOne"
    $('#imageInputOne').change(function (event) {
        imageInputOneChanged = true;
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
        imageInputTwoChanged = true;
        let reader = new FileReader();
        reader.onload = function (event) {
            document.getElementById('icn-imageInputTwo').style.display = 'none';
            $('#img-imageInputTwo').attr('src', event.target.result);
            $('#img-imageInputTwo').show();
        };
        reader.readAsDataURL(this.files[0]);
    });
}

//Img remove btns
//Remove file in "imageInputOne" and "imageInputTwo"
//remove image from "img-imageInputOne" and "img-imageInputTwo"
//Show "icn-imageInputOne" again
function removeProductImgInAddPrdkt(imgNum) {
    if (imgNum === 1) {
        document.getElementById('imageInputOne').value = '';
        let img = document.getElementById('img-imageInputOne');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInputOne').style.display = 'block';
        imageInputOneChanged = true;
    } else if (imgNum === 2) {
        document.getElementById('imageInputTwo').value = '';
        let img = document.getElementById('img-imageInputTwo');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInputTwo').style.display = 'block';
        imageInputTwoChanged = true;
    }
}

// ------------- Starting category list -------------

$(document).ready(function () {
    $("#productList").click(function () {
        setNormalMDL();
        $("#div-rightmenu").html(loadingInfo);
        addRightMenuProductList();
    });
})

async function addRightMenuProductList() {
    console.log('right menu added !');
    $("#div-rightmenu").html(" <!-- Change point # # # [div Id]-->\n" +
            "                    <div class=\"container-fluid\" id=\"productList\">\n" +
            "                        <!-- Head -->\n" +
            "                        <div class=\"row shadow div-leftmenu-head\">\n" +
            "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
            "                                <!-- Change point # # # [Text]-->\n" +
            "                                <p class=\"m-0 txt-rightmenu-description\">Product List</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
            "                                <!-- Change point # # # [Method]-->\n" +
            "                                <button class=\"btn-categoryList-refresh me-3 p-0 py-2 px-3 border border-1 btn btn-light\" onclick=\"addRightMenuProductList()\"><i class=\"fa-solid fa-arrows-rotate fa-lg\"></i></button>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!-- Body -->\n" +
            "                        <div class=\"row shadow mt-3 pt-3 pb-4 div-leftmenu-body\">\n" +
            "                            <div class=\"col px-4\">\n" +
            "                                <table class=\"table table-hover align-middle\">\n" +
            "                                    <thead>\n" +
            "                                        <tr>\n" +
            "                                            <th scope=\"col\">Product ID</th>\n" +
            "                                            <th scope=\"col\">Product Name</th>\n" +
            "                                            <th scope=\"col\">Category</th>\n" +
            "                                            <th scope=\"col\">Status</th>\n" +
            "                                            <th scope=\"col\"></th>\n" +
            "                                        </tr>\n" +
            "                                    </thead>\n" +
            "                                    <tbody id=\"table-body\">\n" + await loadProductList() +
            "                                    </tbody>\n" +
            "                                </table>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>");
}

//Get Product list saved in DB as a html code(for table-body) by Request LoadProductList Servlet
async function loadProductList() {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadProductList",
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

function clickOnUpdateProduct(id) {
    setLargMDL();
    setUpdateProductMDL(id);
    $('#defModel').modal('show');
}

async function setUpdateProductMDL(id) {

    let data = await generateUpdateMDL(id);

    $('#defModel-content').html("<!-- ----- Modal content-----  -->\n" +
            "<form id=\"form-productList-updateProduct\">\n" +
            "    <div class=\"modal-header\">\n" +
            "        <h5>Update Product</h5>\n" +
            "    </div>\n" +
            "    <div class=\"modal-body\">\n" +
            "        <div class=\"container-fluid\">\n" +
            "            <div class=\"row\">\n" +
            "                <div class=\"col\">\n" +
            "                    <div class=\"mt-2\">\n" +
            "                        <label for=\"txt-newProductId\" class=\"form-label\">Product ID</label>\n" +
            "                        <input type=\"text\" class=\"form-control\" id=\"txt-newProductId\" value=\"" + data.productId + "\" name=\"productId\" readonly>\n" +
            "                    </div>\n" +
            "                    <div class=\"mt-2 was-validated\">\n" +
            "                        <label for=\"txt-newProductName\" class=\"form-label\">Product Name</label>\n" +
            "                        <input type=\"text\" class=\"form-control\" id=\"txt-newProductName\" value=\"" + data.productName + "\" placeholder=\"Enter new name\" name=\"productName\" required>\n" +
            "                    </div>\n" +
            "                    <div class=\"mt-2\">\n" +
            "                        <label for=\"selectCategory\" class=\"form-label lbl-leftMenu-body\">Select category</label>\n" +
            "                        <select class=\"form-select\" aria-label=\"Default select example\" name=\"categoryId\" id=\"selectCategoryAddProduct\">\n" +
            data.categoryListHTML +
            "</select>\n" +
            "                    </div>\n" +
            "                    <div class=\"mt-2 was-validated\">\n" +
            "                        <label for=\"productDescription\" class=\"form-label lbl-leftMenu-body\">Product description</label>\n" +
            "                        <textarea class=\"form-control\" id=\"productDescription\" rows=\"5\" name=\"productDescription\" placeholder=\"Enter product description\" required>" + data.productDescription + "</textarea>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"col mt-2\">\n" +
            "                    <label for=\"div-imgRow\" class=\"form-label lbl-leftMenu-body\">Product images</label>\n" +
            "                    <div class=\"row\" id=\"div-imgRow\">\n" +
            "                        <!-- Images One -->\n" +
            "                        <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n" +
            "                            <div class=\"form-group\">\n" +
            "                                <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputOne\" style=\"height: 300px; width: 200px;\">\n" +
            "<i class=\"fa-solid fa-image\" id=\"icn-imageInputOne\"></i>\n" +
            "<img id=\"img-imageInputOne\" src=\"" + data.imageOne + "\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
            "</label>\n" +
            "                                <input type=\"file\" class=\"form-control d-none\" id=\"imageInputOne\" name=\"imageOne\" accept=\"image/*\">\n" +
            "                            </div>\n" +
            "                            <div class=\"\" style=\"height: 300px;\">\n" +
            "                                <div class=\"container px-3\">\n" +
            "                                    <div class=\"row mt-1\">\n" +
            "                                        <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeProductImgInAddPrdkt(1)\">\n" +
            "  <i class=\"fa-solid fa-trash\"></i>\n" +
            "</button>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!-- Images Two -->\n" +
            "                        <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n" +
            "                            <div class=\"form-group\">\n" +
            "                                <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputTwo\" style=\"height: 300px; width: 200px;\">\n" +
            "<i class=\"fa-solid fa-image\" id=\"icn-imageInputTwo\"></i>\n" +
            "<img id=\"img-imageInputTwo\" src=\"" + data.imageTwo + "\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
            "</label>\n" +
            "                                <input type=\"file\" class=\"form-control d-none\" id=\"imageInputTwo\" name=\"imageTwo\" accept=\"image/*\">\n" +
            "                            </div>\n" +
            "                            <div class=\"\" style=\"height: 300px;\">\n" +
            "                                <div class=\"container px-3\">\n" +
            "                                    <div class=\"row mt-1\">\n" +
            "                                        <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeProductImgInAddPrdkt(2)\">\n" +
            "  <i class=\"fa-solid fa-trash\"></i>\n" +
            "</button>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"modal-footer \">\n" +
            "        <button class=\"btn btn-primary\" style=\"width: 100px;\" id=\"btn-model-update\" onclick=\"updateConfirm('" + data.productId + "')\" type=\"button\">Update</button>\n" +
            "        <button class=\"btn btn-danger\" style=\"width: 100px;\" data-bs-dismiss=\"modal\" type=\"button\">Cancel</button>\n" +
            "    </div>\n" +
            "</form>");

    document.getElementById('icn-imageInputOne').style.display = 'none';
    $('#img-imageInputOne').show();

    document.getElementById('icn-imageInputTwo').style.display = 'none';
    $('#img-imageInputTwo').show();

    imageInputOneChanged = false;
    imageInputTwoChanged = false;

    ProductList_BindingChangeEventForIMG();

}

// Getting Json object from server. Then add data in that object to the modal
async function generateUpdateMDL(productId) {
    let myPromise = new Promise(function (resolve, reject) {

        $.ajax({
            type: "POST",
            url: "GenerateUpdateMDL",
            data: {
                "productId": productId
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

    return myPromise;
}

function updateConfirm(id) {
    console.log('Called !');
    console.log(updateProductValidation());
    if (updateProductValidation()) {
        setUpdateProductConfirmMDL(id);
        $('#subModel').modal('show');
    } else {
        alert('All fields must be filled !');
    }
}

function setUpdateProductConfirmMDL(id) {
    console.log('setUpdateProductConfirmMDL Called !');
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
            "        <button class=\"btn btn-primary\" style=\"width: 100px;\" id=\"btn-model-update\" onclick=\"updateProduct()\" type=\"button\">Confirm</button>\n" +
            "    <button class=\"btn btn-danger\" style=\"width: 100px;\" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "</div>"
            );
}

// Check validations and continue the update process
async function updateProduct() {
    $('#subModel').modal('hide');
    try {
        let serverResponse = await sendProductListUpdateReqToSRVR();
        setProductListUpdateResponseMDL(serverResponse);
    } catch (error) {
        setProductListUpdateResponseMDL('Error : [' + error + ']');
    }
    addRightMenuProductList();
}

// Send collected data to the server
async function sendProductListUpdateReqToSRVR() {
    let myPromise = new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-productList-updateProduct');
        let formData = new FormData(formElement);


        $.ajax({
            type: "POST",
            url: "UpdateProduct",
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
function setProductListUpdateResponseMDL(serverResponse) {
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
function updateProductValidation() {
    let valid = true;

    //when product update modal loaded there are images already set so imgOne and imgTwo has value 1 until img remove or add new image. Those "if" conditions check if new imgs added or removed.
    let imgOne = null;
    let imgTwo = null;

    if (imageInputOneChanged) {
        imgOne = $('#imageInputOne')[0].files;
        if (!(imgOne.length > 0)) {
            valid = false;
        }
    }

    if (imageInputTwoChanged) {
        imgTwo = $('#imageInputTwo')[0].files;
        if (!(imgTwo.length > 0)) {
            valid = false;
        }
    }

    if ($('#txt-newProductName').val() === '' ||
            $('#productDescription').val() === '' ||
            $('#selectCategoryAddProduct').val() === null) {
        valid = false;
    }

    return valid;
}


// ------------- Product Inactivate Proccess -------------

function clickOnInactivateProduct(id) {
    setNormalMDL();
    setInactivateProductMDL(id);
    $('#defModel').modal('show');
}

function setInactivateProductMDL(id) {
    $('#defModel-content').html("<!-- Model content -->\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\">\n" +
            "                        <i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Inactivate Product</h5>\n" +
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
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"inactivateProduct('" + id + "')\">Inactivate</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "                </div>");
}

async function inactivateProduct(id) {
    try {
        let serverResponse = await sendInactivateProductReqToSRVR(id);
        setProductInactivateResponseMDL(serverResponse);
        addRightMenuProductList();
    } catch (error) {
        setProductInactivateResponseMDL('Error : [' + error + ']');
    }
}

async function sendInactivateProductReqToSRVR(id) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "ChangeStatusProduct",
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

function setProductInactivateResponseMDL(serverResponse) {
    $("#defModel-content").html(serverResponse);

}

// ------------- Product Activate Proccess -------------

function clickOnActivateProduct(id) {
    setNormalMDL();
    setActivateProductMDL(id);
    $('#defModel').modal('show');
}

function setActivateProductMDL(id) {
    $('#defModel-content').html("<!-- Model content -->\n" +
            "                <div class=\"modal-header\">\n" +
            "                    <div class=\"d-flex align-items-center justify-content-start\">\n" +
            "                        <i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n" +
            "                        <h5 class=\"m-0 ms-3\">Activate Product</h5>\n" +
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
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"ActivateProduct('" + id + "')\">Activate</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
            "                </div>");
}

async function ActivateProduct(id) {
    try {
        let serverResponse = await sendProductActivateReqToSRVR(id);
        setProductActivateResponseMDL(serverResponse);
        addRightMenuProductList();
    } catch (error) {
        setProductActivateResponseMDL('Error : [' + error + ']');
    }
}

async function sendProductActivateReqToSRVR(id) {
    let myPromise = new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "ChangeStatusProduct",
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

function setProductActivateResponseMDL(serverResponse) {
    $("#defModel-content").html(serverResponse);

}