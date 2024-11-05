let customerOrderSearchOption = 4;
let statusTypeValue = 0;
let inputValue = '';
async  function clickOnCustomerOrders_dashBoard() {
    console.log("customerOrderSearchOption : " + customerOrderSearchOption);
    console.log("statusTypeValue : " + statusTypeValue);
    console.log("InputValuePvr : " + inputValue);
    let customerOrderList = JSON.parse(await getOrderList_dashBoardCustomerOrder());
    let customerOrderStatusList = JSON.parse(await getOrderStatusList_dashBoardCustomerOrder());
    let orderTrHTML = ``;
    let typeListHTML = `<option value="0" selected>All status</option>`;

    // Setting type list
    customerOrderStatusList.forEach(function (item) {
        if (item.typeId === statusTypeValue) {
            typeListHTML += `<option value="${item.typeId}" selected>${item.typeName}</option>`;
        } else {
            typeListHTML += `<option value="${item.typeId}">${item.typeName}</option>`;
        }
    });
    //Setting Order list table rows
    customerOrderList.forEach(function (item) {
        orderTrHTML += `
                                        <tr>
                                            <td>${item.invoiceId}</td>
                                            <td>${item.orderId}</td>
                                            <td>${item.fname} ${item.lname}</td>
                                            <td>${item.status}</td>
                                            <td class="">
                                                <button class="btn btn-primary btn-categoryList-tableTow" onclick="changeStatusCustomerOrderDashBoard(${item.orderId},'${item.status}')">Status</button>
                                                <button class="btn btn-danger btn-categoryList-tableTow" onclick="viewCustomerOrderDashBoard(${item.orderId})">View</button>
                                                <button class="btn btn-secondary btn-categoryList-tableTow" onclick="viewOrderLineCustomerOrderDashBoard(${item.orderId})">Order line</button>
                                            </td>
                                        </tr>
        `;
    });

    $('#div-rightmenu').html(`
                            <div class="container-fluid" id="categoryList">
                        <!-- Head -->
                        <div class="row shadow div-leftmenu-head">
                            <div class="col-3 ps-4 d-flex align-items-center justify-content-start">
                                <p class="m-0 txt-rightmenu-description">Customer Orders</p>
                            </div>
                            <div class="col-9 d-flex align-items-center justify-content-end">
                                <input type="search" value="${inputValue}" id="searchInput_dashBoardCustomerOrder" class="form-control w-25" onkeydown="handleKeyPress_dashBoardCustomerOrder(event)">
                                <div class="btn-group ms-3 me-3" role="group" aria-label="Basic example">
                                    <button type="button" value="1" id="selectOrderId-customerOrderOption" class="btn btn-customerOrderOption">Order ID</button>
                                    <button type="button" value="2" id="selectInvoice-customerOrderOption" class="btn btn-customerOrderOption">Invoice</button>
                                    <button type="button" value="3" id="selectCustomer-customerOrderOption" class="btn btn-customerOrderOption">Customer</button>
                                    <button type="button" value="4" id="selectShowAll-customerOrderOption" class="btn btn-customerOrderOption">Show All</button>
                                </div>
                                <select class="form-select w-25" aria-label="Default select example" id="orderStatusSelector-customerOrderOption" disabled>
                                    ${typeListHTML}
                                  </select>
                            </div>
                        </div>
                        <!-- Body -->
                        <div class="row shadow mt-3 pt-3 pb-4 div-leftmenu-body">
                            <div class="col px-4">
                                <table class="table table-hover align-middle">
                                    <thead>
                                        <tr>
                                            <th scope="col">Invoice</th>
                                            <th scope="col">Order ID</th>
                                            <th scope="col">Customer Name</th>
                                            <th scope="col">Order Status</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="table-body">
                                        ${orderTrHTML}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
    `);
    let btnList = $('.btn-customerOrderOption');
    btnList.each(function () {
        if ($(this).val() == customerOrderSearchOption) {
            $(this).css('background-color', 'rgb(77, 147, 231)');
        }
    });
    console.log(customerOrderSearchOption == 1 || customerOrderSearchOption == 2);
    if (customerOrderSearchOption == 1 || customerOrderSearchOption == 2) {
        console.log('Called !');
        $('#orderStatusSelector-customerOrderOption').prop('disabled', true);
    } else {
        $('#orderStatusSelector-customerOrderOption').prop('disabled', false);
    }
    $('.btn-customerOrderOption').click(function () {
        customerOrderSearchOption = $(this).val();
        btnList.each(function () {
            $(this).css('background-color', '');
        });
        $(this).css('background-color', 'rgb(77, 147, 231)');

        if ($(this).attr('id') === 'selectCustomer-customerOrderOption' || $(this).attr('id') === 'selectShowAll-customerOrderOption') {
            $('#orderStatusSelector-customerOrderOption').prop('disabled', false);
        } else {
            $('#orderStatusSelector-customerOrderOption').prop('disabled', true);
        }
    });

}

async function handleKeyPress_dashBoardCustomerOrder(event) {
    if (event.key === "Enter") {
        inputValue = $('#searchInput_dashBoardCustomerOrder').val();
        statusTypeValue = $('#orderStatusSelector-customerOrderOption').val();
        if (customerOrderSearchOption == 1 || customerOrderSearchOption == 2) {
            statusTypeValue = 0;
        }
        await clickOnCustomerOrders_dashBoard();
    }
}

async function getOrderStatusList_dashBoardCustomerOrder() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadOrderStatusTypes",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });
}

async function getOrderList_dashBoardCustomerOrder() {
    let newData;
    if (customerOrderSearchOption == 4) {
        newData = {
            "orderStatus": statusTypeValue
        }
    } else if (customerOrderSearchOption == 3) {
        newData = {
            "orderStatus": statusTypeValue,
            "customerId": $('#searchInput_dashBoardCustomerOrder').val()
        }
    } else if (customerOrderSearchOption == 2) {
        newData = {
            "orderStatus": statusTypeValue,
            "invoice": $('#searchInput_dashBoardCustomerOrder').val()
        }
    } else if (customerOrderSearchOption == 1) {
        newData = {
            "orderStatus": statusTypeValue,
            "orderId": $('#searchInput_dashBoardCustomerOrder').val()
        }
    }
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadCustomerOrders",
            data: newData,
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });
}

async function viewOrderLineCustomerOrderDashBoard(orderId) {
    let orderLineList = JSON.parse(await getOrderLineForOrder_dashBoardCustomerOrder(orderId));
    let modalHTML = `<!-- Modal content Add new Address -->
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">ORDER ID : ${orderId}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div style="height: 20rem; overflow: auto;">`;
    orderLineList.forEach(function (item) {
        modalHTML += `
            <div class="row border border-2 py-2">
                            <p class="m-0 fst-italic fw-bold">${item.itemName}</p>
                            <p class="m-0">[PID : ${item.productItemId}]</p>
                            <p class="m-0">QTY : ${item.qty}</p>
                            <p class="m-0">price : LKR ${item.price} /-</p>
                        </div>  
        `;
    });
    modalHTML += `
                </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
                <!-- ---------- -->
                `;
    $('#content-subModal2-dashBoard').html(modalHTML);
    $('#subModal2-dashBoard').modal('show');
}

async function getOrderLineForOrder_dashBoardCustomerOrder(orderId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadOrderLineForOrder",
            data: {
                "orderId": orderId
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

async function viewCustomerOrderDashBoard(orderId) {
    let orderDetails = JSON.parse(await getOrderDetails_dashBoardCustomerOrder(orderId));
    $('#content-subModal2-dashBoard').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">ORDER ID : ${orderDetails.orderId}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <div style="height: 20rem; overflow: auto;">
                        <div class="border border-2 py-1 px-1">
                            <p class="m-0 fw-bold">Customer :</p>
                            <p class="m-0">[CID : ${orderDetails.customerId}] ${orderDetails.fname} ${orderDetails.lname}</p>
                        </div>
                        <div class="border border-2 py-1 px-1">
                            <p class="m-0 fw-bold">Ordered Date :</p>
                            <p class="m-0">${orderDetails.orderedDate}</p>
                        </div>
                        <div class="border border-2 py-1 px-1">
                            <p class="m-0 fw-bold">Total price :</p>
                            <p class="m-0">LKR ${orderDetails.orderTotal} /-</p>
                        </div>
                        <div class="border border-2 py-1 px-1">
                            <p class="m-0 fw-bold">Shipping type :</p>
                            <p class="m-0">${orderDetails.shippingType}</p>
                        </div>
                        <div class="border border-2 py-1 px-1">
                            <p class="m-0 fw-bold">Shipping address :</p>
                            <p class="m-0">${orderDetails.shippingAddress}</p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
    $('#subModal2-dashBoard').modal('show');
}

async function getOrderDetails_dashBoardCustomerOrder(orderId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadCustomerOrderDetail",
            data: {
                "orderId": orderId
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

async function changeStatusCustomerOrderDashBoard(orderId, statusName) {
    let orderStatusList = JSON.parse(await getOrderStatusList_dashBoardCustomerOrder());
    setModalForChangeStatus_customerOrderDashBoard(orderStatusList, statusName, orderId);
}

function setConfirmModalForChangeStatus_customerOrderDashBoard(orderId, selectedStatusId) {
    $('#content-subModal2-dashBoard').html(`
                    <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <div>
                        <p class="text-center m-0">Do you want to change status for Order : ${orderId} ?</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="btn-Yes-changeStatus-customerOrder-dashBoard">Yes</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">No</button>
                </div>
                `);
    $('#subModal2-dashBoard').modal('show');
    $('#btn-Yes-changeStatus-customerOrder-dashBoard').click(async function () {
        let response = await updateStatusSRVR_customerOrderDashBoard(orderId, selectedStatusId);
        $('#content-subModal2-dashBoard').html(`
                    <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <div>
                        <p class="text-center m-0">${response}</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
                `);
        await clickOnCustomerOrders_dashBoard();
    });
}

async function updateStatusSRVR_customerOrderDashBoard(orderId, selectedStatusId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "changeCustomerOrderStatus",
            data: {
                "orderId": orderId,
                "selectedStatusId": selectedStatusId
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

function setModalForChangeStatus_customerOrderDashBoard(orderStatusList, statusName, orderId) {
    let modalContentHTML = `
            <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Change status [ORDER ID : 57]</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <div>
                        <select class="form-select" aria-label="Default select example" id="list-changeStatus-customerOrders-dashBoard">
    `;
    orderStatusList.forEach(function (item) {
        if (statusName == item.typeName) {
            modalContentHTML += `<option value="${item.typeId}" selected>${item.typeName}</option>`;
        } else {
            modalContentHTML += `<option value="${item.typeId}">${item.typeName}</option>`;
        }
    });
    modalContentHTML += `
        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="btn-update-changeStatus-customerOrder-dashBoard">Update</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Cancel</button>
                </div>
    `;
    $('#content-subModal2-dashBoard').html(modalContentHTML);
    $('#subModal2-dashBoard').modal('show');
    $('#btn-update-changeStatus-customerOrder-dashBoard').click(function () {
        let selectedStatusId = $('#list-changeStatus-customerOrders-dashBoard').val();
        setConfirmModalForChangeStatus_customerOrderDashBoard(orderId, selectedStatusId);
    });
}