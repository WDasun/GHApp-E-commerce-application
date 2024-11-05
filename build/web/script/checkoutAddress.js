var loadingInfo_checkout = "                    <div class=\"container-fluid my-5\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

async function showNewAddressModal_checkout(customerId) {
    $('#modalDef_checkout').modal('show');
    let countryListHTML = await loadCountryList_checkout();
    $('#defModel-content-checkout').html(`
            
                            <!-- ---------- -->

                    <div class="modal-header">
                    <h5 class="modal-title" id="defModal_title_checkout">Add new Address</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="defModal_body_checkout">
                    <form id="newAddress_checkout_form" class="was-validated">
                        <input type="text" name="addressLineOne" id="addressLineOne" class="form-control my-2" placeholder="Address line one" required>
                        <input type="text" name="addressLineTwo" id="addressLineTwo" class="form-control my-2" placeholder="Address line two" required>
                        <div class="d-flex my-2">
                            <input type="text" name="city" id="city" class="form-control me-1" placeholder="City" required>
                            <input type="text" name="stateOrDistrict" id="stateOrDistrict" class="form-control ms-1" placeholder="State/District" required>
                        </div>
                        <div class="d-flex my-2">
                            <input type="text" name="postalCode" id="postalCode" class="form-control me-1" placeholder="Postal code" required>
                            <select class="form-select ms-1" name="country" id="country" aria-label="Selecting country" required>
                                  ${countryListHTML}   
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="saveAddress_checkout(${customerId})">Save Address</button>
                </div>

                <!-- ---------- -->
            
         `);
}

async function saveAddress_checkout(customerId) {
    let formElement = document.getElementById('newAddress_checkout_form');
    let formData = new FormData(formElement);
    $('#defModal_title_checkout').html('processing !');
    $('#defModal_body_checkout').html(loadingInfo_checkout);
    $('#defModel-content-checkout').html(await saveAddressDB_checkout(formData));
    refreshSelectAddress(customerId);
}

async function refreshSelectAddress(customerId){
        $('#address_select_checkout').html("<option selected disabled> Wait ...!</option>");
        let addresses = JSON.parse(await loadAddressList_checkout(customerId));
        let addressListHTML = "<option selected disabled>Select added Address</option>";
        addresses.forEach(address => {
            addressListHTML += "<option value='" + address.id + "'>" + address.addressLine1 + " " + address.addressLine2 + "</option>";
        });
        $('#address_checkout').html(addressListHTML);
}

async function saveAddressDB_checkout(formData) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "SaveCustomerAddress",
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

async function loadCountryList_checkout() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "loadCountryList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

async function loadAddressList_checkout(id) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "AddressListOfCustomer",
            data :{
                'id' : id
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