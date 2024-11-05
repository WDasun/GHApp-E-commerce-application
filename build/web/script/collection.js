
var loadingInfoCollection = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

$(document).ready(function () {
    var selectedValues = [];

    $('.form-check-input').change(function () {
        var value = $(this).val();
        if ($(this).is(':checked')) {
            if (!selectedValues.includes(value)) {
                selectedValues.push(value);
            }
        } else {
            var index = selectedValues.indexOf(value);
            if (index > -1) {
                selectedValues.splice(index, 1);
            }
        }
        setItems();
    });

    async function setItems() {
        try {
            $('#div-items-collection').html(loadingInfoCollection);
            $('#div-items-collection').html(await loadOptionContent());

        } catch (e) {
            alert(e);
        }

    }

    async function loadOptionContent() {
        return new Promise(function (resolve, reject) {
            $.ajax({
                type: "POST",
                url: "loadOptionContent",
                data: {
                    "optionIdArray": JSON.stringify(selectedValues)
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
});

function onclickView_collection(itemId) {
    window.location.href = "itemViewer.jsp?itemId=" + itemId;
}

//When add a item to cart process
function onclickAddToCartBtn(itemId, btnId) {
    $('#defModal-collection').html(`
            <div class="modal-dialog modal-sm modal-dialog-centered">
                <div class="modal-content" id="defModel-content-collection">
                    <!-- ---------- -->

                    <div class="modal-header">
                        <div class="d-flex align-items-center">
                            <i class="fa-solid fa-cart-shopping fa-xl" style="color: #74C0FC;"></i>
                            <h5 class="m-0 ms-3">Add to Cart</h5>
                        </div>
                    </div>
                    <div class="modal-body d-flex justify-content-center">
                        <p class="fw-bold fs-5 m-0">Add this item to the Cart ?</p>
                    </div>
                    <div class="modal-footer d-flex align-items-center justify-content-center">
                        <button class="btn btn-primary " onclick="confirmToAddItemCart_collection('${itemId}','${btnId}')" style="width: 100px;">Yes</button>
                        <button class="btn btn-danger " style="width: 100px; " data-bs-dismiss="modal">No</button>
                    </div>

                    <!-- ---------- -->
                </div>
            </div>
`);
    $('#defModal-collection').modal("show");
}

async function confirmToAddItemCart_collection(itemId, btnDivId) {
    try {
        $('#defModal-collection').html(`
        <div class="modal-dialog modal-sm modal-dialog-centered">
            <div class="modal-content" id="defModel-content">
                <!-- ---------- -->

                <div class="modal-header">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-cart-shopping fa-xl" style="color: #74C0FC;"></i>
                        <h5 class="m-0 ms-3">Response</h5>
                    </div>
                </div>
                <div class="modal-body d-flex justify-content-center">
                    <p class="fw-bold fs-5 m-0">${await addItemToSessionCart(itemId)}</p>
                </div>
                <div class="modal-footer d-flex align-items-center justify-content-center">
                    <button class="btn btn-primary " style="width: 100px; " data-bs-dismiss="modal">Ok</button>
                </div>

                <!-- ---------- -->
            </div>
        </div>
        `);
        $('#' + btnDivId).html(`
            <button class="btn-itemActions" onclick="onclickRemoveItemCart('${itemId}', '${btnDivId}')"><i class="fa-solid fa-trash fa-2xl" style="color: #74C0FC;"></i></button>
        `);
        updateHeadCartItemQTY();
    } catch (e) {
        console.log(e);
        $('#defModal-collection').html(`
        <div class="modal-dialog modal-sm modal-dialog-centered">
            <div class="modal-content" id="defModel-content">
                <!-- ---------- -->

                <div class="modal-header">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-cart-shopping fa-xl" style="color: #74C0FC;"></i>
                        <h5 class="m-0 ms-3">Response</h5>
                    </div>
                </div>
                <div class="modal-body d-flex justify-content-center">
                    <p class="fw-bold fs-5 m-0">Something Wrong !</p>
                </div>
                <div class="modal-footer d-flex align-items-center justify-content-center">
                    <button class="btn btn-primary " style="width: 100px; " data-bs-dismiss="modal">Ok</button>
                </div>

                <!-- ---------- -->
            </div>
        </div>
        `);
    }

}

async function addItemToSessionCart(itemId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "AddToSessionCart",
            data: {
                "itemId": itemId
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

//When remove a item from the cart process

function onclickRemoveItemCart(itemId, btnDivId) {
    $('#defModal-collection').html(`
            <div class="modal-dialog modal-sm modal-dialog-centered">
                <div class="modal-content" id="defModel-content-collection">
                    <!-- ---------- -->

                    <div class="modal-header">
                        <div class="d-flex align-items-center">
                            <i class="fa-solid fa-trash fa-xl" style="color: #74C0FC;"></i>
                            <h5 class="m-0 ms-3">Remove Item</h5>
                        </div>
                    </div>
                    <div class="modal-body d-flex justify-content-center">
                        <p class="fw-bold fs-5 m-0">Remove item from cart ?</p>
                    </div>
                    <div class="modal-footer d-flex align-items-center justify-content-center">
                        <button class="btn btn-primary " onclick="confirmToRemoveItemCart_collection('${itemId}','${btnDivId}')" style="width: 100px;">Yes</button>
                        <button class="btn btn-danger " style="width: 100px; " data-bs-dismiss="modal">No</button>
                    </div>

                    <!-- ---------- -->
                </div>
            </div>
`);
    $('#defModal-collection').modal("show");
}

async function confirmToRemoveItemCart_collection(itemId, btnDivId) {
    try {
        $('#defModal-collection').html(`
        <div class="modal-dialog modal-sm modal-dialog-centered">
            <div class="modal-content" id="defModel-content">
                <!-- ---------- -->

                <div class="modal-header">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-trash fa-xl" style="color: #74C0FC;"></i>
                        <h5 class="m-0 ms-3">Response</h5>
                    </div>
                </div>
                <div class="modal-body d-flex justify-content-center">
                    <p class="fw-bold fs-5 m-0">${await removeItemFromSessionCart(itemId)}</p>
                </div>
                <div class="modal-footer d-flex align-items-center justify-content-center">
                    <button class="btn btn-primary " style="width: 100px; " data-bs-dismiss="modal">Ok</button>
                </div>

                <!-- ---------- -->
            </div>
        </div>
        `);
        console.log(btnDivId);
        $('#' + btnDivId).html(`
            <button class="btn-itemActions" onclick="onclickAddToCartBtn('${itemId}', '${btnDivId}')"><i class="fa-solid fa-cart-shopping fa-2xl" style="color: #B197FC;"></i></button>
        `);
        updateHeadCartItemQTY();
    } catch (e) {
        console.log(e);
        $('#defModal-collection').html(`
        <div class="modal-dialog modal-sm modal-dialog-centered">
            <div class="modal-content" id="defModel-content">
                <!-- ---------- -->

                <div class="modal-header">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-trash fa-xl" style="color: #74C0FC;"></i>
                        <h5 class="m-0 ms-3">Response</h5>
                    </div>
                </div>
                <div class="modal-body d-flex justify-content-center">
                    <p class="fw-bold fs-5 m-0">Something Wrong !</p>
                </div>
                <div class="modal-footer d-flex align-items-center justify-content-center">
                    <button class="btn btn-primary " style="width: 100px; " data-bs-dismiss="modal">Ok</button>
                </div>

                <!-- ---------- -->
            </div>
        </div>
        `);
    }

}

async function removeItemFromSessionCart(itemId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "RemoveItemFromSessionCart",
            data: {
                "itemId": itemId
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

async function updateHeadCartItemQTY() {
    $('#cartIcon-navBar').html(`
        <i class="fa-solid fa-cart-shopping fa-lg c-navbar-icon d-none d-lg-block"></i>Cart(<div style="width: 10px; height: 10px;" class="spinner-border text-info" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>)
    `);

    $('#cartIcon-navBar').html(`
        <i class="fa-solid fa-cart-shopping fa-lg c-navbar-icon d-none d-lg-block"></i>Cart(${await updateSessionCartItemCount_collection()})
    `);
}

async function updateSessionCartItemCount_collection() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GetSessionCartItemCount",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });
}

