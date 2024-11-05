$(document).ready(function () {

    const checkboxes = document.querySelectorAll('.cbx-itemSelect-cart');

    // Add an event listener to each checkbox
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            if (this.checked) {
                selectItem_cart(this.value);
            } else {
                unselectItem_cart(this.value);
            }
        });
    });

});

// Select and Unselect
async function selectItem_cart(itemId) {

    await ChangeSelectStatus_cart(itemId, true);
    $('#div-summery-cart').html(await RefreshSummery_cart());
}
async function unselectItem_cart(itemId) {

    await ChangeSelectStatus_cart(itemId, false);
    $('#div-summery-cart').html(await RefreshSummery_cart());
}

async function ChangeSelectStatus_cart(itemId, status) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "ChangeItemSelectStatus",
            data: {
                "itemId": itemId,
                "status": status
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

async function RefreshSummery_cart() {
        return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "RefreshSummery",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

function checkoutNow_cart(){
    window.location.href = "Checkout.jsp";
}
