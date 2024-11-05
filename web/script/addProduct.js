function bindingChangeEventForIMG() {
    //Image process
    //Hide icon "icn-imageInputOne"
    //Set image for "img-imageInputOne"
    $('#imageInputOne').change(function(event) {
        let reader = new FileReader();
        reader.onload = function(event) {
            document.getElementById('icn-imageInputOne').style.display = 'none';
            $('#img-imageInputOne').attr('src', event.target.result);
            $('#img-imageInputOne').show();
        };
        reader.readAsDataURL(this.files[0]);
    });
    //Hide icon "icn-imageInputOne"
    //Set image for "img-imageInputOne"
    $('#imageInputTwo').change(function(event) {
        let reader = new FileReader();
        reader.onload = function(event) {
            document.getElementById('icn-imageInputTwo').style.display = 'none';
            $('#img-imageInputTwo').attr('src', event.target.result);
            $('#img-imageInputTwo').show();
        };
        reader.readAsDataURL(this.files[0]);
    });
}

//Img remove btns
//Remove file in "imageInputOne"
//Set remove image from "img-imageInputOne"
//Show "icn-imageInputOne" again
function removeProductImgInAddPrdkt(imgNum) {
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
    }
}

//Adding RightMenu Process
async function addRightMenuAddProduct() {
    setNormalMDL();
    //Adding html
    document.getElementById('div-rightmenu').innerHTML = "<div class=\"container-fluid\" id=\"div-addProduct\">\n" +
        "                        <!-- Head -->\n" +
        "                        <div class=\"row shadow div-leftmenu-head\">\n" +
        "                            <div class=\"col ps-4 d-flex align-items-center justify-content-start\">\n" +
        "                                <p class=\"m-0 txt-rightmenu-description\">Add a Product</p>\n" +
        "                            </div>\n" +
        "                            <div class=\"col d-flex align-items-center justify-content-end\">\n" +
        "                                <button class=\"btn btn-secondary me-2 btn-leftmenu-head\" type=\"button\" id=\"btn-addProductDiscard\" onclick=\"addRightMenuAddProduct()\">Discard</button>\n" +
        "                                <button class=\"btn btn-primary me-2 btn-leftmenu-head\" type=\"button\" id=\"btn-addProduct\" onclick=\"clickOnAddProduct()\">Add Product</button>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <!-- Body -->\n" +
        "                        <div class=\"row\">\n" +
        "                            <form id=\"form-addProduct\" class=\"p-0\">\n" +
        "                                <div class=\"container-fluid p-0\">\n" +
        "                                    <div class=\"row m-0\">\n" +
        "                                        <div class=\"col-7 p-0\">\n" +
        "                                            <div class=\"shadow py-3 px-3 my-3 me-3 div-leftmenu-body\">\n" +
        "                                                <label for=\"categoryId\" class=\"form-label lbl-leftMenu-body\">Product ID</label>\n" +
        //Generate and set Product ID
        "                                                <input type=\"text\" class=\"form-control\" id=\"productId\" name=\"productId\" value=\"" + await generateProductId() + "\" readonly>\n" +
        "                                                <div class=\"mt-2 was-validated\">\n" +
        "                                                    <label for=\"productName\" class=\"form-label lbl-leftMenu-body\">Product name</label>\n" +
        "                                                    <input type=\"text\" class=\"form-control\" id=\"productName\" name=\"productName\" required>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"shadow py-3 px-3 my-3 me-3 div-leftmenu-body\">\n" +
        "                                                <div class=\"was-validated\">\n" +
        "                                                    <label for=\"productDescription\" class=\"form-label lbl-leftMenu-body\">Product description</label>\n" +
        "                                                    <textarea class=\"form-control\" id=\"productDescription\" rows=\"5\" name=\"productDescription\" placeholder=\"Enter product description\" required></textarea>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                        </div>\n" +
        "                                        <div class=\"col-5 p-0\">\n" +
        "                                            <div class=\"shadow py-3 px-3 my-3 div-leftmenu-body\">\n" +
        "                                                <label for=\"selectCategory\" class=\"form-label lbl-leftMenu-body\">Select category</label>\n" +
        "                                                <select class=\"form-select\" aria-label=\"Default select example\" name=\"categoryId\" id=\"selectCategoryAddProduct\">\n" +
        //Generate and set Category  List
        await loadCategoryListAddProduct() + "</select>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"shadow py-3 px-3 my-3 div-leftmenu-body\">\n" +
        "                                                <label for=\"selectCategory\" class=\"form-label lbl-leftMenu-body\">Product Images</label>\n" +
        "                                                <div class=\"container-fluid border-1\">\n" +
        "                                                    <div class=\"row\">\n" +
        "                                                        <!-- Images One -->\n" +
        "                                                        <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n" +
        "                                                            <div class=\"form-group\">\n" +
        "                                                                <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputOne\" style=\"height: 300px; width: 200px;\">\n" +
        "                                        <i class=\"fa-solid fa-image\" id=\"icn-imageInputOne\"></i>\n" +
        "                                        <img id=\"img-imageInputOne\" src=\"#\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
        "                                    </label>\n" +
        "                                                                <input type=\"file\" class=\"form-control d-none\" id=\"imageInputOne\" name=\"imageOne\" accept=\"image/*\">\n" +
        "                                                            </div>\n" +
        "                                                            <div class=\"\" style=\"height: 300px;\">\n" +
        "                                                                <div class=\"container px-3\">\n" +
        "                                                                    <div class=\"row mt-1\">\n" +
        "                                                                        <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeProductImgInAddPrdkt(1)\">\n" +
        "                                                <i class=\"fa-solid fa-trash\"></i>\n" +
        "                                            </button>\n" +
        "                                                                    </div>\n" +
        "                                                                </div>\n" +
        "                                                            </div>\n" +
        "                                                        </div>\n" +
        "                                                        <!-- Images Two -->\n" +
        "                                                        <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n" +
        "                                                            <div class=\"form-group\">\n" +
        "                                                                <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputTwo\" style=\"height: 300px; width: 200px;\">\n" +
        "                                        <i class=\"fa-solid fa-image\" id=\"icn-imageInputTwo\"></i>\n" +
        "                                        <img id=\"img-imageInputTwo\" src=\"#\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n" +
        "                                    </label>\n" +
        "                                                                <input type=\"file\" class=\"form-control d-none\" id=\"imageInputTwo\" name=\"imageTwo\" accept=\"image/*\">\n" +
        "                                                            </div>\n" +
        "                                                            <div class=\"\" style=\"height: 300px;\">\n" +
        "                                                                <div class=\"container px-3\">\n" +
        "                                                                    <div class=\"row mt-1\">\n" +
        "                                                                        <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeProductImgInAddPrdkt(2)\">\n" +
        "                                                <i class=\"fa-solid fa-trash\"></i>\n" +
        "                                            </button>\n" +
        "                                                                    </div>\n" +
        "                                                                </div>\n" +
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
        "                    </div>";
    bindingChangeEventForIMG();
}

async function generateProductId() {

    let myPromise = new Promise(function(resolve, reject) {
        $.ajax({
                    type: "POST",
                    url: "GenerateProductId",
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

async function loadCategoryListAddProduct() {

    let myPromise = new Promise(function(resolve, reject) {
        $.ajax({
                    type: "POST",
                    url: "GenerateCategoryList",
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

// Product Adding Process
function clickOnAddProduct() {
    if (validationAddProduct()) {
        setAddProductMDL();
        $('#defModel').modal('show');

    } else {
        alert('All fields must completed !');
    }

}

function setAddProductMDL() {
    $('#defModel-content').html("<div class=\"modal-header\">\n" +
        "                    <h5>Add Product</h5>\n" +
        "                </div>\n" +
        "                <div class=\"modal-body\">\n" +
        "                    <div class=\"container-fluid\">\n" +
        "                        <div class=\"row\">\n" +
        "                            <div class=\"col\">\n" +
        "                                <p class=\"m-0 fs-5 fw-normal\">Are sure, you want to add Product : <span class=\"fw-bold\">" + $('#productId').val() + "</span> ?</p>\n    " +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"modal-footer \">\n" +
        "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"addProduct('" + $('#productId').val() + "')\">Save</button>\n" +
        "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n" +
        "                </div>");
}

async function addProduct(id) {
    try {
        let serverResponse = await sendAddProductReqToSRVR(id);
        setAddProductResponseMDL(serverResponse);
        $('#defModel').modal('hide');
        addRightMenuAddProduct();
    } catch (error) {
        setAddProductResponseMDL('Error : [' + error + ']');
    }
}

async function sendAddProductReqToSRVR(id) {
    let myPromise = new Promise(function(resolve, reject) {         

            let formElement = document.getElementById('form-addProduct');
            let formData = new FormData(formElement);
            
            $.ajax({
                    type: "POST",
                    url: "SaveProduct",
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

function setAddProductResponseMDL(serverResponse) {
    alert(serverResponse);

}

function validationAddProduct() {
    let valid = true;
    let imgOne = $('#imageInputOne')[0].files;
    let imgTwo = $('#imageInputTwo')[0].files;
    if ($('#productName').val() === '' ||
        $('#productDescription').val() === '' ||
        $('#selectCategoryAddProduct').val() === null ||
        !(imgOne.length > 0) ||
        !(imgTwo.length > 0)) {
        valid = false;
    }
    return valid;
}