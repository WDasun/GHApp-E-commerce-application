
//QTY changing proccess
async function AddItemQty_cart(itemId) {
    let textElementId = 'txt-qty-cartItem-' + itemId + '-cart';
    $('#' + textElementId).html(
            `<span class="spinner-grow text-info spinner-grow-sm" role="status" aria-hidden="true"></span>
                    <span class="visually-hidden">Loading...</span>`
            );

    $('.btn-qty').prop('disabled', true);

    $('#txt-qty-cartItem-' + itemId + '-cart').html(await changeSummery('add', 1, itemId));

    $('.btn-qty').prop('disabled', false);
}

async function subtractItemQty_cart(itemId) {

    let textElementId = 'txt-qty-cartItem-' + itemId + '-cart';
    $('#' + textElementId).html(
            `<span class="spinner-grow text-info spinner-grow-sm" role="status" aria-hidden="true"></span>
                    <span class="visually-hidden">Loading...</span>`
            );

    $('.btn-qty').prop('disabled', true);

    $('#' + textElementId).html(await changeSummery('subtract', 1, itemId));

    $('.btn-qty').prop('disabled', false);
}

async function changeSummery(operation, changingQty, itemId) {
    $('#div-summery-cart').html(`
        <div class="w-100 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-info" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
            </div>
    `);
    let jsonText = await ChangeQtyAndCalculateSummery_cart(operation, changingQty, itemId)
    let dataObj = JSON.parse(jsonText);

    $('#div-summery-cart').html(dataObj.summeryHTML);
    return dataObj.newQTY;
}

async function ChangeQtyAndCalculateSummery_cart(operation, qtyChange, itemId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "ChangeQtyAndCalculateSummery",
            data: {
                "operation": operation,
                "qtyChange": qtyChange,
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

// Trash remove from cart process

function onclickTrashBtn_cart(itemId) {
    $('#modalDefCart').html(
            `   <div class="modal-dialog modal-sm modal-dialog-centered">
                    <div class="modal-content" id="defModel-content">
                        <!-- ---------- -->

                        <div class="modal-header">
                            <div class="d-flex align-items-center">
                                <i class="fa-solid fa-trash fa-xl" style="color: #74C0FC;"></i>
                                <h5 class="m-0 ms-3">Confirm !</h5>
                            </div>
                        </div>
                        <div class="modal-body d-flex justify-content-center">
                            <p class="fw-bold fs-5 m-0">Do you want to remove this item from the Cart ?</p>
                        </div>
                        <div class="modal-footer d-flex align-items-center justify-content-center">
                            <button class="btn btn-primary" onclick="removeTheItem_cart('${itemId}')">Remove the item</button>
                            <button class="btn btn-danger" style="width: 100px; " data-bs-dismiss="modal">Cancel</button>
                        </div>

                        <!-- ---------- -->
                    </div>
                </div>`
            );
    $('#modalDefCart').modal('show');
}

async function removeTheItem_cart(itemId) {
    $('#modalDefCart').html(
            `<div class="modal-dialog modal-sm modal-dialog-centered">
            <div class="modal-content" id="defModel-content">
                <!-- ---------- -->

                <div class="modal-header">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-cart-shopping fa-xl" style="color: #74C0FC;"></i>
                        <h5 class="m-0 ms-3">Response</h5>
                    </div>
                </div>
                <div class="modal-body d-flex justify-content-center">
                    <div class="spinner-border text-info" role="status">
                        <span class="visually-hidden">Loading...</span>
                    </div>
                </div>
                <div class="modal-footer d-flex align-items-center justify-content-center">
                    <button class="btn btn-primary " style="width: 100px; " data-bs-dismiss="modal">Ok</button>
                </div>

                <!-- ---------- -->
            </div>
        </div>`
            );
    await RemoveItemFromCart_cart(itemId)
    location.reload();
}

async function RemoveItemFromCart_cart(itemId) {
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


