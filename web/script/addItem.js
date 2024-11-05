var loadingInfo_addItem = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

function bindingChangeEventForIMGItemAdd() {
    //Image process
    //Hide icon "icn-imageInputOne"
    //Set image for "img-imageInputOne"
    $('#imageInputOne').change(function (event) {
        let reader = new FileReader();
        reader.onload = function (event) {
            document.getElementById('icn-imageInputOne').style.display = 'none';
            $('#img-imageInputOne').attr('src', event.target.result);
            $('#img-imageInputOne').show();
        };
        reader.readAsDataURL(this.files[0]);
    });
    //Hide icon "icn-imageInputOTwo"
    //Set image for "img-imageInputTwo"
    $('#imageInputTwo').change(function (event) {
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
//Remove file in "imageInputOne"/"imageInputTwo"/"imageInputThree"
//Set remove image from "img-imageInputOne"/"img-imageInputTwo"/"img-imageInputThree"
//Show "icn-imageInputOne" again
function removeProductImgInAddItem(imgNum) {
    if (imgNum === 1) {
        document.getElementById('imageInputOne').value = '';
        let img = document.getElementById('img-imageInputOne');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInputOne').style.display = 'block';
    } else if (imgNum === 2) {
        document.getElementById('imageInputTwo').value = '';
        let img = document.getElementById('img-imageInputTwo');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInputTwo').style.display = 'block';
    } else if (imgNum === 3) {
        document.getElementById('imageInputThree').value = '';
        let img = document.getElementById('img-imageInputThree');
        img.src = '';
        img.style.display = 'none';
        document.getElementById('icn-imageInputThree').style.display = 'block';
    }
}

// ------------- Starting Add Item -------------

$(document).ready(function () {

    $("#addItem").click(function () {
        setNormalMDL();
        setRightMenu_addItem();
    });
});

// Adding addVariation div to the rightn menu
async function setRightMenu_addItem() {
    try {
        $('#div-rightmenu').html(loadingInfo_addItem);
        $('#div-rightmenu').html("<div class=\"container-fluid\" id=\"div-addProduct\">\n" +
                "                        <!-- Head -->\n" +
                "                        <div class=\"row shadow div-leftmenu-head\">\n" +
                "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
                "                                <!-- Change point # # # [Text]-->\n" +
                "                                <p class=\"m-0 txt-rightmenu-description\">Add Item</p>\n" +
                "                            </div>\n" +
                "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
                "                                <!-- Change point # # # [Method]-->\n" +
                "                                <button class=\"btn btn-secondary me-2 btn-leftmenu-head\" type=\"button\" id=\"btn-discard-addItem\">Discard</button>\n" +
                "                                <!-- Change point # # # [Method]-->\n" +
                "                                <button class=\"btn btn-primary me-2 btn-leftmenu-head\" type=\"button\" id=\"btn-addItem\">Add Item</button>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- Body -->\n" +
                "                        <div class=\"row\">\n" +
                "                            <form id=\"form-addItem\" class=\"p-0\">\n" +
                "                                <div class=\"container-fluid p-0 mt-3\">\n" +
                "                                    <div class=\"row\">\n" +
                "                                        <div class=\"col\">\n" +
                "                                            <div class=\"px-4 pt-1 pb-4 shadow div-leftmenu-body\">\n" +
                "                                                <div class=\"mt-2\">\n" +
                "                                                    <label for=\"itemId\" class=\"form-label lbl-leftMenu-body\">Item ID</label>\n" +
                "                                                    <input type=\"text\" class=\"form-control\" id=\"itemId\" name=\"itemId\" value=\"" + await generateId__addItem() + "\" readonly>\n" +
                "                                                </div>\n" +
                "                                                <div class=\"mt-2\">\n" +
                "                                                    <label for=\"selectProductAddItem\" class=\"form-label lbl-leftMenu-body\">Select Product</label>\n" +
                "                                                    <select class=\"form-select\" aria-label=\"Default select example\" name=\"productId\" id=\"selectProductAddItem\">\n" + await generateProductList__addItem() +
                "                            </select>\n" +
                "                                                </div>\n" +
                "                                                <div class=\"mt-2  was-validated\">\n" +
                "                                                    <label for=\"itemName\" class=\"form-label lbl-leftMenu-body\">Item name</label>\n" +
                "                                                    <input type=\"text\" class=\"form-control\" id=\"itemName\" name=\"itemName\" required>\n" +
                "                                                </div>\n" +
                "                                                <div class=\"mt-2  was-validated\">\n" +
                "                                                    <label for=\"itemSKU\" class=\"form-label lbl-leftMenu-body\">SKU</label>\n" +
                "                                                    <input type=\"text\" class=\"form-control\" id=\"itemSKU\" name=\"itemSKU\" required>\n" +
                "                                                </div>\n" +
                "                                                <div class=\"mt-2  was-validated\">\n" +
                "                                                    <label for=\"itemQTY\" class=\"form-label lbl-leftMenu-body\">QTY</label>\n" +
                "                                                    <input type=\"text\" class=\"form-control\" id=\"itemQTY\" name=\"itemQTY\" required>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                        <div class=\"col ps-0\">\n" +
                "                                            <div class=\"px-4 pt-1 pb-4  shadow div-leftmenu-body\">\n" +
                "                                                <div class=\"mt-2  was-validated\">\n" +
                "                                                    <label for=\"itemPrice\" class=\"form-label lbl-leftMenu-body\">Price (LKR)</label>\n" +
                "                                                    <input type=\"text\" class=\"form-control\" id=\"itemPrice\" name=\"itemPrice\" required>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"mt-3 px-4 pt-1 pb-4 shadow div-leftmenu-body\" style=\"height: 70.59%;\">\n" +
                "                                                <div class=\"mt-2 was-validated\">\n" +
                "                                                    <label for=\"itemDescription\" class=\"form-label lbl-leftMenu-body\">Item description</label>\n" +
                "                                                    <textarea class=\"form-control\" id=\"itemDescription\" rows=\"8\" name=\"itemDescription\" placeholder=\"Enter item description\" required></textarea>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <div class=\"row shadow m-0 mt-3 pt-2 pb-4 div-leftmenu-body\">\n" +
                "                                        <div class=\"container-fluid\">\n" +
                "                                            <div class=\"row py-1\">\n" +
                "                                                <label class=\"form-label lbl-leftMenu-body\">Item images</label>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"row d-flex align-items-center justify-content-between\">\n" +
                "                                                <!-- Images One -->\n" +
                "                                                <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n" +
                "                                                    <div class=\"form-group\">\n" +
                "                                                        <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputOne\" style=\"height: 300px; width: 200px;\">\n" +
                "                    <i class=\"fa-solid fa-image\" id=\"icn-imageInputOne\"></i>\n" +
                "                    <img id=\"img-imageInputOne\" src=\"#\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
                "                    </label>\n" +
                "                                                        <input type=\"file\" class=\"form-control d-none\" id=\"imageInputOne\" name=\"imageOne\" accept=\"image/*\">\n" +
                "                                                    </div>\n" +
                "                                                    <div class=\"\" style=\"height: 300px;\">\n" +
                "                                                        <div class=\"container px-3\">\n" +
                "                                                            <div class=\"row mt-1\">\n" +
                "                                                                <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeProductImgInAddItem(1)\">\n" +
                "                    <i class=\"fa-solid fa-trash\"></i>\n" +
                "                    </button>\n" +
                "                                                            </div>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                                <!-- Images Two -->\n" +
                "                                                <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n" +
                "                                                    <div class=\"form-group\">\n" +
                "                                                        <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputTwo\" style=\"height: 300px; width: 200px;\">\n" +
                "                    <i class=\"fa-solid fa-image\" id=\"icn-imageInputTwo\"></i>\n" +
                "                    <img id=\"img-imageInputTwo\" src=\"#\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
                "                    </label>\n" +
                "                                                        <input type=\"file\" class=\"form-control d-none\" id=\"imageInputTwo\" name=\"imageTwo\" accept=\"image/*\">\n" +
                "                                                    </div>\n" +
                "                                                    <div class=\"\" style=\"height: 300px;\">\n" +
                "                                                        <div class=\"container px-3\">\n" +
                "                                                            <div class=\"row mt-1\">\n" +
                "                                                                <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeProductImgInAddItem(2)\">\n" +
                "                        <i class=\"fa-solid fa-trash\"></i>\n" +
                "                    </button>\n" +
                "                                                            </div>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                                <!-- Images Three -->\n" +
                "                                                <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n" +
                "                                                    <div class=\"form-group\">\n" +
                "                                                        <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputThree\" style=\"height: 300px; width: 200px;\">\n" +
                "                    <i class=\"fa-solid fa-image\" id=\"icn-imageInputThree\"></i>\n" +
                "                    <img id=\"img-imageInputThree\" src=\"#\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
                "                    </label>\n" +
                "                                                        <input type=\"file\" class=\"form-control d-none\" id=\"imageInputThree\" name=\"imageThree\" accept=\"image/*\">\n" +
                "                                                    </div>\n" +
                "                                                    <div class=\"\" style=\"height: 300px;\">\n" +
                "                                                        <div class=\"container px-3\">\n" +
                "                                                            <div class=\"row mt-1\">\n" +
                "                                                                <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeProductImgInAddItem(3)\">\n" +
                "                        <i class=\"fa-solid fa-trash\"></i>\n" +
                "                    </button>\n" +
                "                                                            </div>\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </form>\n" +
                "                        </div>\n" +
                " <div class=\"row\">\n" +
                "                            <div class=\"container-fluid shadow m-0 mt-3 pt-2 pb-4 div-leftmenu-body\" id=\"div-otherOptions-itemAdd\">\n" +
                "                                <!-- ......................................... -->\n" + +
                "                                <!-- ......................................... -->\n" +
                "                            </div>\n" +
                "                        </div>" +
                "                    </div>");
        bindingBtnClickMainDiv__addItem();
        bindingChangeEventForIMGItemAdd();
        bindingDataInputValidations_addItem();
        bindingEventListnerSelectProduct();
    } catch (error) {
        alert("Error : " + error)
    }
}

// Generating Item ID
async function generateId__addItem() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateItemId",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

// Generate product list
async function generateProductList__addItem() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateProductList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

// Generate other options
async function generateOtherOptions_addItem() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GenerateOtherOptions",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

// Add event listner to the "selectProduct" to change "div-otherOptions-itemAdd" div.
function bindingEventListnerSelectProduct(){
    $('#selectProductAddItem').change(async function (){
        try {
            let contentHTML = await getOtherOptionContent($('#selectProductAddItem').val());
            $('#div-otherOptions-itemAdd').html(contentHTML);
        } catch (e) {
            alert(e)
        }

    });
}

async function getOtherOptionContent(productId){
    return new Promise(function (resolve, reject){
        $.ajax({
            type: 'POST',
            url: "GenerateOtherOptions",
            data: {
                "productId" : productId
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
function bindingBtnClickMainDiv__addItem() {
    $('#btn-addItem').click(function () {
        if (validDataCheck_addItem()) {
            setModal_addItem();
            $('#defModel').modal('show');
        } else {
            alert('All fields must completed !');
        }
    });
    $('#btn-discard-addItem').click(function () {
        setRightMenu_addItem();
    });
}

//Add data input validations to new Div input fields
function bindingDataInputValidations_addItem() {
    onlyTypeNumbersWithDecim("itemQTY");
    onlyTypeNumbersWithDecim("itemPrice");
    limitCharacterLength("itemName", 60);
    limitCharacterLength("itemSKU", 60);
    limitCharacterLength("itemDescription", 1000);
}

// Set default variation modal
function setModal_addItem() {
    $('#defModel-content').html("                <div class=\"modal-header\">\n" +
            "                    <h5>Confirm request</h5>\n" +
            "                </div>\n" +
            "                <div class=\"modal-body\">\n" +
            "                    <div class=\"container-fluid\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <p class=\"fw-normal fs-5\">Are you sure, do you want to add this Item !</p>\n" +
            "                        </div>\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6\">Item ID</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#itemId').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6\">Product ID</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4\">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#selectProductAddItem').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row \">\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">Item name</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#itemName').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row \">\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">SKU</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#itemSKU').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row \">\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">QTY</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#itemQTY').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                        <div class=\"row \">\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">Price (LKR)</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \">\n" +
            "                                <p class=\"fw-normal fs-6 \">: " + $('#itemPrice').val() + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-4 \"></div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"modal-footer \">\n" +
            "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-modelConfirm-addItem\">Confirm</button>\n" +
            "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Close</button>\n" +
            "                </div>");
    // Add event listner to the defModal confirm button
    $('#btn-modelConfirm-addItem').click(async function () {
        $('#defModel-content').html(loadingInfo_addItem);
        try {
            $('#defModel-content').html(await saveItem());
            setRightMenu_addItem();
        } catch (error) {
            alert("Error : " + error)
        }
    });
}

async function saveItem() {
    return new Promise(function (resolve, reject) {
        
        let optionValuesArray = [];
        let optionElements = document.getElementsByClassName('option-select');
        for (var i = 0; i < optionElements.length; i++) {
            optionValuesArray.push(optionElements[i].value);
        }     
        
        let formElement = document.getElementById('form-addItem');
        let formData = new FormData(formElement);
        
        formData.append('optionValuesArray', JSON.stringify(optionValuesArray));
        
        $.ajax({
            type: "POST",
            url: "SaveItem",
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

function validDataCheck_addItem() {
    let valid = false;

    let imgOne = !($('#imageInputOne')[0].files.length > 0);
    let imgTwo = !($('#imageInputTwo')[0].files.length > 0);
    let imgThree = !($('#imageInputThree')[0].files.length > 0);
    let product = $('#selectProductAddItem').val() === null;
    let name = $('#itemName').val() === '';
    let sku = $('#itemSKU').val() === '';
    let qty = $('#itemQTY').val() === '';
    let price = $('#itemPrice').val() === '';
    let description = $('#itemDescription').val() === '';

    if (!imgOne &&
            !imgTwo &&
            !imgThree &&
            !product &&
            !name &&
            !sku &&
            !qty &&
            !price &&
            !description
            ) {
        valid = true;
    }


    return valid;
}

//Input Validation functions
function onlyTypeNumbersWithDecim(id) {
    document.getElementById(id).addEventListener('input', function (e) {
        const value = e.target.value;

        let validValue = value.replace(/[^0-9.]/g, '');

        const parts = validValue.split(".");

        if (parts.length > 2) {
            validValue = parts[0] + "." + parts.slice(1).join('');
        }

        e.target.value = validValue;

    });
}

function limitCharacterLength(id, MaximumLength) {
    document.getElementById(id).addEventListener('input', function (e) {
        const input = e.target;

        if (input.value.length > MaximumLength) {
            input.value = input.value.slice(0, MaximumLength);
            alert("Maximum character lenght is " + MaximumLength + ".");
        }

    });
}