async function paymentOption_customerProfile() {
    console.log('Called !');
let divContentHTML = `<div class='h-75 border-start' style='overflow-y: auto;'>
                        <div class="mb-3 d-flex justify-content-end">
                            <button class="btn btn-warning fs-6 w-25 fw-bold" style="height: 3rem;" onclick="newCard_customerProfile()">Add new card</button>
                        </div>`;
        let cardList = JSON.parse(await getCardListSRVR_customerProfile());
        cardList.forEach(function (item) {
        divContentHTML += `
            <div class="ps-4">
                            <div class="d-flex justify-content-between align-items-center ps-4 py-2 border-top border-bottom">
                                <div class="me-5" style="font-size: 15px;">
                                    <p class="m-0 fw-bold">Card type : <span class="fw-normal">${item.cardType}</span></p>
                                    <p class="m-0 fw-bold">Card number : <span class="fw-normal">${item.cardNumber}</span></p>
                                    <p class="m-0 fw-bold">Name on Card : <span class="fw-normal">${item.nameOnCard}</span></p>
                                    <p class="m-0 fw-bold">Expire date : <span class="fw-normal">${item.expYear}/${item.expMonth}</span></p>
                                </div>
                                <div class="w-50 ms-5">
                                    <button class="btn btn-secondary d-block w-50 mb-2" onclick="editCard_customerProfile(${item.cardDetailsId})">Edit</button>
                                    <button class="btn btn-danger d-block w-50" onclick="deleteCard_customerProfile(${item.cardDetailsId})">Delete</button>
                                </div>
                            </div>
                        </div>
        `;
        });
        divContentHTML += `</div>`;
        $('#div-content-customerProfile').html(divContentHTML);
        }

async function editCard_customerProfile(id){
$('#updateAddressModal_customerProfile_content').html(`
                        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Update Card</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <select class="form-select mb-3" aria-label="Card type" id="cardType_update_customerProfile">
                        <option selected disabled>Select card type</option>
                        <option value="1">VISA</option>
                        <option value="2">MASTER</option>
                      </select>
                    <input type="text" class="form-control mb-3" placeholder="Enter card number" id="cardNumber_update_customerProfile">
                    <input type="text" class="form-control mb-3" placeholder="Enter name on card" id="cardName_update_customerProfile">
                    <label for="expiredate_div">Expire Date</label>
                    <div class="d-flex" id="expiredate_div">
                        <input type="text" class="form-control me-2 mt-2" placeholder="Year" id="expYear_update_customerProfile">
                        <select class="form-select mt-2" aria-label="Card type" id="expMonth_update_customerProfile">
                            <option selected disabled>Month</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                          </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="updateConfirmBtn-cardDetails-customerProfile">Update</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
        $('#updateAddressModal_customerProfile').modal('show');
        $('#updateConfirmBtn-cardDetails-customerProfile').click(async function(){
let updateCardInfo = {
cardDetailsId : id,
        cardType : $('#cardType_update_customerProfile').val(),
        cardNumber : $('#cardNumber_update_customerProfile').val(),
        nameOnCard : $('#cardName_update_customerProfile').val(),
        expYear : $('#expYear_update_customerProfile').val(),
        expMonth : $('#expMonth_update_customerProfile').val(),
        };
        $('#updateAddressModal_customerProfile_content').html(loadingInfo_customerProfile);
        let Response = await updateCardSRVR_customerProfile(JSON.stringify(updateCardInfo));
        await paymentOption_customerProfile();
        $('#updateAddressModal_customerProfile_content').html(`
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center fs-5 m-0">${Response}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            `);
        });
        }

async function updateCardSRVR_customerProfile(CardDetailCustomerProfile) {
return new Promise(function (resolve, reject) {
$.ajax({
type: "POST",
        url: "UpdateCard",
        data : {
        "CardDetailCustomerProfile" : CardDetailCustomerProfile
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

async function newCard_customerProfile(){
$('#updateAddressModal_customerProfile_content').html(`
                        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New Card</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <select class="form-select mb-3" aria-label="Card type" id="cardType_newCard_customerProfile">
                        <option selected disabled>Select card type</option>
                        <option value="1">VISA</option>
                        <option value="2">MASTER</option>
                      </select>
                    <input type="text" class="form-control mb-3" placeholder="Enter card number" id="cardNumber_newCard_customerProfile">
                    <input type="text" class="form-control mb-3" placeholder="Enter name on card" id="cardName_newCard_customerProfile">
                    <label for="expiredate_div">Expire Date</label>
                    <div class="d-flex" id="expiredate_div">
                        <input type="text" class="form-control me-2 mt-2" placeholder="Year" id="expYear_newCard_customerProfile">
                        <select class="form-select mt-2" aria-label="Card type" id="expMonth_newCard_customerProfile">
                            <option selected disabled>Month</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                          </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="saveConfirmBtn-cardDetails-customerProfile">Save</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
        $('#updateAddressModal_customerProfile').modal('show');
        $('#saveConfirmBtn-cardDetails-customerProfile').click(async function(){
let newCardInfo = {
cardType : $('#cardType_newCard_customerProfile').val(),
        cardNumber : $('#cardNumber_newCard_customerProfile').val(),
        nameOnCard : $('#cardName_newCard_customerProfile').val(),
        expYear : $('#expYear_newCard_customerProfile').val(),
        expMonth : $('#expMonth_newCard_customerProfile').val(),
        };
        $('#updateAddressModal_customerProfile_content').html(loadingInfo_customerProfile);
        let Response = await saveCardSRVR_customerProfile(JSON.stringify(newCardInfo));
        await paymentOption_customerProfile();
        $('#updateAddressModal_customerProfile_content').html(`
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center fs-5 m-0">${Response}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            `);
        });
        await paymentOption_customerProfile();
}


async function saveCardSRVR_customerProfile(CardDetailCustomerProfile) {
return new Promise(function (resolve, reject) {
$.ajax({
type: "POST",
        url: "SaveCard",
        data : {
        "CardDetailCustomerProfile" : CardDetailCustomerProfile
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

async function deleteCard_customerProfile(id){
$('#updateAddressModal_customerProfile_content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center fs-5 m-0">Do you want delete this card info !</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary w-25" id="yes-btn-deletConfirm-cardDetails-customerProfile">Yes</button>
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
        $('#updateAddressModal_customerProfile').modal('show');
        $('#yes-btn-deletConfirm-cardDetails-customerProfile').click(async function (){
try {
let response = await deleteCardSRVR_customerProfile(id);
        $('#updateAddressModal_customerProfile_content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center fs-5 m-0">${response}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
        await paymentOption_customerProfile();
} catch (e) {
$('#updateAddressModal_customerProfile_content').html(`
        <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="text-center fs-5 m-0">Somthing wrong !</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary w-25" data-bs-dismiss="modal">Close</button>
                </div>
    `);
}
});
        }

async function deleteCardSRVR_customerProfile(id) {
return new Promise(function (resolve, reject) {
$.ajax({
type: "POST",
        url: "DeleteCard",
        data : {
        "id" : id
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

async function getCardListSRVR_customerProfile() {
return new Promise(function (resolve, reject) {
$.ajax({
type: "POST",
        url: "LoadCardList",
        success: function (response) {
        resolve(response);
        },
        error: function (error) {
        reject(error);
        }
});
});
        }


