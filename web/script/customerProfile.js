var loadingInfo_customerProfile = "                    <div class=\"container-fluid my-5\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";

$(document).ready(function () {
    setProfileDetailsContent_customerProfile();
});

function errorMessage_customerProfile(message) {
    return `    <div class="modal-header">
                    <div class="d-flex align-items-center">
                        <h5 class="m-0 ms-3">Response !</h5>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <p class="fw-normal fs-5">${message}</p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer ">
                    <button class="btn btn-danger " style="width: 100px; " data-bs-dismiss="modal">Close</button>
                </div>`;
}

// Customer Profile details
async function setProfileDetailsContent_customerProfile() {
    $("#div-content-customerProfile").html(loadingInfo_customerProfile);
    try {
        $("#div-content-customerProfile").html(await loadCustomerInfoContent());
    } catch (e) {
        $("#div-content-customerProfile").html("Somthing wrong !");
        console.log(e);
    }
}

async function loadCustomerInfoContent() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadCustomerProfileContent",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

function onclickUpdate_customerProfile() {
    let confirmMsgModalBody = `
    <div class="modal-header">
        <h5 class="modal-title fw-bold" id="exampleModalLabel">Confirm !</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
        <p class="fs-5 m-0 ms-3">Do you want to update profile details ?</p>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary" id="continueUpdate_customerProfile">Continue update</button>
    </div>`;
    $("#defModel-content-customerProfile").html(confirmMsgModalBody);
    $("#defModal-customerProfile").modal('show');
    $("#continueUpdate_customerProfile").click(async function () {
        $("#defModel-content-customerProfile").html(loadingInfo_customerProfile);
        try {
            await updateCustomer_customerProfile();
            setProfileDetailsContent_customerProfile();
            $("#defModal-customerProfile").modal('hide');
        } catch (e) {
            console.log(e);
        }

    });
}

async function updateCustomer_customerProfile() {
    return new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-profileDetails-customerProfile');
        let formData = new FormData(formElement);

        $.ajax({
            type: "POST",
            url: "UpdateCustomer",
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

// Address Pool

async function setAddressPoolContent_customerProfile() {
    $("#div-content-customerProfile").html(loadingInfo_customerProfile);
    try {
        $("#div-content-customerProfile").html(await loadAdressPoolContent());
    } catch (e) {
        $("#div-content-customerProfile").html("Somthing wrong !");
        console.log(e);
    }
}

async function newAddress__customerProfile() {
    $("#defModel-content-customerProfile").html(
            `<!-- Modal content Add new Address -->
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add new Address</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="form-newAddress-customerProfile" class="was-validated">
                            <input type="text" name="addressLineOne" id="addressLineOne" class="form-control my-2" placeholder="Address line one" required>
                            <input type="text" name="addressLineTwo" id="addressLineTwo" class="form-control my-2" placeholder="Address line two" required>
                            <div class="d-flex my-2">
                                <input type="text" name="city" id="city" class="form-control me-1" placeholder="City" required>
                                <input type="text" name="stateOrDistrict" id="stateOrDistrict" class="form-control ms-1" placeholder="State/District" required>
                            </div>
                            <div class="d-flex my-2">
                                <input type="text" name="postalCode" id="postalCode" class="form-control me-1" placeholder="Postal code" required>
                                <select class="form-select ms-1" name="country" id="country" aria-label="Selecting country" required>
                                    ${await loadCountryList_customerProfile()}
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="OnclickSaveAddress_customerProfile()">Save Address</button>
                    </div>`
            );
    $("#defModal-customerProfile").modal('show');
}

async function loadAdressPoolContent() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadCustomerAddressPool",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
}

async function loadCountryList_customerProfile() {
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

async function OnclickSaveAddress_customerProfile() {
    if (saveAddressValidation_customerProfile()) {
        try {
            $("#defModel-content-customerProfile").html(await saveAddress_customerProfile());
            await setAddressPoolContent_customerProfile();
        } catch (e) {
            $("#defModel-content-customerProfile").html(errorMessage_customerProfile("Somthing Wrong, Please contact customer care !"));
            console.log(e);
        }
    } else {
        alert("All fileds must completed !");
    }
}

async function saveAddress_customerProfile() {
    return new Promise(function (resolve, reject) {

        let formElement = document.getElementById('form-newAddress-customerProfile');
        let formData = new FormData(formElement);

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

function saveAddressValidation_customerProfile() {

    let validData = false;
    let addressLineOne = $("#addressLineOne").val();
    let city = $("#city").val();
    let stateOrDistrict = $("#stateOrDistrict").val();
    let postalCode = $("#postalCode").val();
    let country = $("#country").val();
    if (addressLineOne !== null && addressLineOne !== ""
            && city !== null && city !== ""
            && stateOrDistrict !== null && stateOrDistrict !== ""
            && postalCode !== null && postalCode !== ""
            && country !== null && country !== "") {
        validData = true;
    }
    return validData;
}

// Delete a Address

function deleteAddress_customerProfile(addressId) {
    $("#defModel-content-customerProfile").html(
            `<div class="modal-header">
             <h5 class="modal-title fw-bold" id="exampleModalLabel">Confirm !</h5>
             <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
         </div>
         <div class="modal-body">
             <p class="text-center fs-5 m-0 ms-3">Do you want to this address ?</p>
         </div>
         <div class="modal-footer">
             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
             <button type="button" class="btn btn-primary" onclick="onclickDeleteAddress('${addressId}')">Delete Address</button>
         </div>`
            );
    $("#defModal-customerProfile").modal('show');
}

async function onclickDeleteAddress(addressId) {
    $("#defModel-content-customerProfile").html(loadingInfo_customerProfile);
    try {
        $("#defModel-content-customerProfile").html(await deleteAddress(addressId));
        setAddressPoolContent_customerProfile();
    } catch (e) {
        console.log(e);
        $("#defModel-content-customerProfile").html("Somthing wrong !, Please contact customer care.");
    }

}

async function deleteAddress(addressId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "DeleteCustomerAddress",
            data: {
                "addressId": addressId
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

let updatingAddressId;
async function editAddress_customerProfile(addressId) {
    updatingAddressId = addressId;
    $('#updateAddressModal_customerProfile_content').html(`
        <!-- Modal content Add new Address -->
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit Address</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="form-newAddress-customerProfile" class="was-validated">
                        <input type="text" name="addressLineOne" id="addressLineOne_update_customerProfile" class="form-control my-2" placeholder="Address line one" required>
                        <input type="text" name="addressLineTwo" id="addressLineTwo_update_customerProfile" class="form-control my-2" placeholder="Address line two" required>
                        <div class="d-flex my-2">
                            <input type="text" name="city" id="city_update_customerProfile" class="form-control me-1" placeholder="City" required>
                            <input type="text" name="stateOrDistrict" id="stateOrDistrict_update_customerProfile" class="form-control ms-1" placeholder="State/District" required>
                        </div>
                        <div class="d-flex my-2">
                            <input type="text" name="postalCode" id="postalCode_update_customerProfile" class="form-control me-1" placeholder="Postal code" required>
                            <select class="form-select ms-1" name="country" id="country_update_customerProfile" aria-label="Selecting country" required>
                            ${await loadCountryList_customerProfile()}
                        </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="updateAddress_customerProfile()">Update</button>
                </div>
    `);
    $('#updateAddressModal_customerProfile').modal('show');
}

async function updateAddress_customerProfile() {
    let addressId = updatingAddressId;
    let countryId = $('#country_update_customerProfile').val();
    let addressLine1 = $('#addressLineOne_update_customerProfile').val();
    let addressLine2 = $('#addressLineTwo_update_customerProfile').val();
    let city = $('#city_update_customerProfile').val();
    let stateOrDistrict = $('#stateOrDistrict_update_customerProfile').val();
    let postalCode = $('#postalCode_update_customerProfile').val();

    $('#updateAddressModal_customerProfile_content').html(loadingInfo_customerProfile);
    let response = await updateAddressSERVR_customerProfile(addressId, countryId, addressLine1, addressLine2, city, stateOrDistrict, postalCode);
    $('#updateAddressModal_customerProfile_content').html(`
                            <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit Address</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center fs-5 m-0">${response}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
      `);
    setAddressPoolContent_customerProfile();
}

async function updateAddressSERVR_customerProfile(addressId, countryId, addressLine1, addressLine2, city, stateOrDistrict, postalCode) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "UpdateCustomerAddress",
            data: {
                "addressId": addressId,
                "countryId": countryId,
                "addressLine1": addressLine1,
                "addressLine2": addressLine2,
                "city": city,
                "stateOrDistrict": stateOrDistrict,
                "postalCode": postalCode
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