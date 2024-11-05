async function recentCustomerOrders_customerProfile(){
    let orderList = JSON.parse(await getOrderListSRVR_customerProfile());
    let orderListHTML = "<div class='h-75 border-start' style='overflow-y: auto;'>";
    orderList.forEach(function (item){
        orderListHTML += `
                        <div class="ps-4">
                            <div class="ps-4 py-2">
                                <div class="row">
                                    <div class="d-flex fw-bold mb-1" style="font-size: 20px;">
                                        <p class="m-0">[ INVOICE : ${item.invoiceId} ]</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="border px-3 py-2">
                                            <p class="m-0"><span class="fw-bold">Order ID: </span> ${item.orderId}</p>
                                            <p class="m-0"><span class="fw-bold">Order Total : </span> Rs. ${item.orderTotal} /-</p>
                                            <p class="m-0"><span class="fw-bold">Ordered Date :</span> ${item.orderedDate}</p>
                                            <p class="m-0"><span class="fw-bold">Order Status :</span> ${item.status}</p>
                                            <p class="m-0"><span class="fw-bold">Shipping Type :</span> ${item.shippingType}</p>
                                            <p class="m-0"><span class="fw-bold">Shipping Address :</span> ${item.shippingAddress}</p>
                                        </div>
                                    </div>
                                    <div class="col d-flex align-items-center justify-content-start">
                                        <button class="btn btn-dark h-100" onclick="viewOrderItem_customerProfile(${item.orderId},'${item.invoiceId}')">View Items</button>
                                    </div>
                                </div>
                            </div>
                        </div>
        `;
    });
    orderListHTML += "</div>";
    $('#div-content-customerProfile').html(orderListHTML);
}

async function viewOrderItem_customerProfile(id, invoiceId){
    let itemList = JSON.parse(await getItemListForOrderSRVR_customerProfile(id));
    let itemListHTML = `<div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">INVOICE : ${invoiceId}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div style="height: 20rem; overflow: auto;">`;
    itemList.forEach(function(item){
        itemListHTML += `
                        <div class="row border border-2 py-2">
                            <p class="m-0 fst-italic fw-bold">${item.itemName}</p>
                            <p class="m-0">QTY : ${item.qty}</p>
                            <p class="m-0">price : LKR ${item.price} /-</p>
                        </div>
        `;
    });
    itemListHTML += `
        </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `;
    $('#updateAddressModal_customerProfile_content').html(itemListHTML);
    $('#updateAddressModal_customerProfile').modal("show");
}

async function getItemListForOrderSRVR_customerProfile(orderId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GetItemsListInOrder",
            data: {
                "orderId" : orderId
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

async function getOrderListSRVR_customerProfile() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GetOrderList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

