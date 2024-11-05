
$(document).ready(function () {
    $('#shippingType_checkout').change(async function () {
        $('#orderSummery_checkout').html(loadingInfo_checkout);
        $('#orderSummery_checkout').html(await getOrderSummery_checkout(this.value));
    });
});


async function getOrderSummery_checkout(shippingTypeId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GetCheckoutOrderSummery",
            data: {
                'shippingTypeId': shippingTypeId
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

