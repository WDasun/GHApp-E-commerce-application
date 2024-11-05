async function loadCardInfo_chekout(id){
   let cardInfo = JSON.parse(await getCardInfo_checkout(id));
    $('#cardNumber_checkout').val(cardInfo.cardNumber);
    $('#nameOnCard_checkout').val(cardInfo.cardName);
    $('#cardTypeSelect_checkout').val(cardInfo.cardTypeId);
    $('#exYear_checkout').val(cardInfo.exYear);
    $('#exMonth_checkout').val(cardInfo.exMonth);
}


async function getCardInfo_checkout(id) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GetCardInfo",
            data:{
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
