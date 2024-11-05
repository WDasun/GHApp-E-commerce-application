

async function clickOneCustomerList_dashBoard(){
$('#div-rightmenu').html(`
        <div class="container-fluid" id="categoryList">
                        <!-- Head -->
                        <div class="row shadow div-leftmenu-head">
                            <div class="col-3 ps-4 d-flex align-items-center justify-content-start">
                                <p class="m-0 txt-rightmenu-description">Customer List</p>
                            </div>
                            <div class="col-9 d-flex align-items-center justify-content-center">
                                <input type="search" class="form-control w-50" placeholder="Enter email" id="customerEmailSearch_dashboard">
                                <button class="btn btn-primary ms-1" onclick="searchCustomerByEmail_dashboard()">Search</button>
                                <button class="btn btn-primary ms-3" onclick="showAllCustomers_dashboard()">Show All</button>
                            </div>
                        </div>
                        <!-- Body -->
                        <div class="row shadow mt-3 pt-3 pb-4 div-leftmenu-body">
                            <div class="col px-4">
                                <table class="table table-hover align-middle">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Full Name</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Status</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="table-body-customer">
                                        ${await loadCustomerList_dashboard()}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
    `);
}

async function loadCustomerList_dashboard() {
return  new Promise(function (resolve, reject) {
$.ajax({
type: "POST",
        url: "LoadCustomerList",
        success: function (response) {
        resolve(response);
        },
        error: function (error) {
        reject(error);
        }
});
        });
}
// changing Status
async function activeCustomer_customerManage(customerId){
$('#subModel-content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <p class="text-center m-0">${await changeCustomerStatus_dashboard(customerId, true)}</p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
        $('#subModel').modal('show');
        clickOneCustomerList_dashBoard();
}
async function inactiveCustomer_customerManage(customerId){
$('#subModel-content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body fs-5">
                    <p class="text-center m-0">${await changeCustomerStatus_dashboard(customerId, false)}</p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
        $('#subModel').modal('show');
        clickOneCustomerList_dashBoard();
}
async function changeCustomerStatus_dashboard(customerId, status) {
return  new Promise(function (resolve, reject) {
$.ajax({
type: "POST",
        url: "ChnageCustomerStatus",
        data:{
        "customerId" : customerId,
                "status" : status
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

// Customer View
async function viewCustomer_customerManage(customerId){
    $('#defModel-content').html(await loadCustomerInfo_dashboard(customerId));
    $('#defModel').modal('show');
}

async function loadCustomerInfo_dashboard(customerId) {
    return  new Promise(function (resolve, reject) {
    $.ajax({
        type: "POST",
            url: "LoadCustomer",
            data:{
            "customerId" : customerId
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

// Search Customer by Email
async function searchCustomerByEmail_dashboard(){
    let email = $('#customerEmailSearch_dashboard').val();
    $('#table-body-customer').html(await searchCustomerByEmailSRVR_dashboard(email));
}

async function searchCustomerByEmailSRVR_dashboard(email) {
    return  new Promise(function (resolve, reject) {
    $.ajax({
        type: "POST",
            url: "SearchCustomerByEmail",
            data:{
            "email" : email
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

// Show all Customers
function showAllCustomers_dashboard(){
    clickOneCustomerList_dashBoard();
}
