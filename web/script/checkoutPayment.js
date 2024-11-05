
async function PayNow_checkout() { 
    if (validation_checkout()) {
        let saveCardInfo = 0;
        if ($('#saveCardInfor_checkout').val() == 1) {
            saveCardInfo = 1;
        } else {
            saveCardInfo = 0;
        }

        let checkoutInfo = {
            cardTypeId: $('#cardTypeSelect_checkout').val(),
            cardNumber: $('#cardNumber_checkout').val(),
            nameOnCard: $('#nameOnCard_checkout').val(),
            expYear: $('#exYear_checkout').val(),
            expMonth: $('#exMonth_checkout').val(),
            cvv: $('#cvv_checkout').val(),
            saveCardInfo: saveCardInfo,
            shippingTypeId: $('#shippingType_checkout').val(),
            addressId: $('#address_checkout').val(),
        }

        let jsonCheckoutInfo = JSON.stringify(checkoutInfo);

        try {
            setErrorMsgAndShowModal('Wait...');
            let response = await PayNowSRVR_checkout(jsonCheckoutInfo);
            window.location.href = "paymentResponse.jsp?filePath="+response+"";
        } catch (e) {
            if (e.status === 400) {
                setErrorMsgAndShowModal('Data not valid ! Please check again.');
            }
        }
    }
}

async function PayNowSRVR_checkout(jsonCheckoutInfo) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "PayNow",
            data: {
                "jsonCheckoutInfo": jsonCheckoutInfo
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

function validation_checkout() {
    let cardType = $('#cardTypeSelect_checkout').val();
    let cardNumber = $('#cardNumber_checkout').val();
    let nameOneCard = $('#nameOnCard_checkout').val();
    let shippingType = $('#shippingType_checkout').val();
    let addresss = $('#address_checkout').val();

    let valid = false;

    if (cardType === null) {
        setErrorMsgAndShowModal('Select card type !');
    } else if (!isValidCreditCard_checkout(cardNumber)) {
        setErrorMsgAndShowModal('Card number is not valid !');
    } else if (nameOneCard === '') {
        setErrorMsgAndShowModal('Name on card can not be empty !');
    } else if (shippingType === null) {
        setErrorMsgAndShowModal('Select shipping type !');
    } else if (addresss === null) {
        setErrorMsgAndShowModal('Select address !');
    } else {
        valid = true;
    }

    return valid;
}

function isValidCreditCard_checkout(value) {
    // accept only digits, dashes or spaces
    if (/[^0-9-\s]+/.test(value))
        return false;

// The Luhn Algorithm. It's so pretty.
    var nCheck = 0, nDigit = 0, bEven = false;
    value = value.replace(/\D/g, "");

    for (var n = value.length - 1; n >= 0; n--) {
        var cDigit = value.charAt(n),
                nDigit = parseInt(cDigit, 10);

        if (bEven) {
            if ((nDigit *= 2) > 9)
                nDigit -= 9;
        }

        nCheck += nDigit;
        bEven = !bEven;
    }

    return (nCheck % 10) == 0;
}

function ErrorMsgSetup_checkout(msg) {
    let modalContent = `<div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Response</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p class="fs-5 text-center m-0">${msg}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>`;
    return modalContent;
}

function setErrorMsgAndShowModal(msg) {
    $('#errorModal-content-checkout').html(ErrorMsgSetup_checkout(msg));
    $('#errorModal-checkout').modal('show');
}

