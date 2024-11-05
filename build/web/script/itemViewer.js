let productId;
let customerId;
let rateValue;
function writeAReview_itemViewer(productIdParam, customerIdParam) {
    productId = productIdParam;
    customerId = customerIdParam;
    $('#writeAComment-modal-itemViewer').modal('show');
}

async function saveReview_itemViewer() {
    await saveReviewSRVR_itemViewer();
//    window.location.href = "itemViewer.jsp?itemId=" + productId;
window.location.href = "itemViewer.jsp?itemId=" + productId + "&t=" + new Date().getTime();

}

async function updateReview_itemViewer(){ 
    $('#topReviewDiv_itemViewer').html(await updateReviewSRVR_itemViewer());
    $('#writeAComment-modal-itemViewer').modal('hide');
}

async function updateReviewSRVR_itemViewer() {
    return new Promise(function (resolve, reject) {
        let title = $('#title_review_modal_itemViewer').val();
        let comment = $('#comment_review_modal_itemViewer').val();
        $.ajax({
            type: "POST",
            url: "UpdateReview",
            data: {
                "productId": productId,
                "customerId": customerId,
                "title": title,
                "comment": comment,
                "rateValue": rateValue
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

async function saveReviewSRVR_itemViewer() {
    return new Promise(function (resolve, reject) {
        let title = $('#title_review_modal_itemViewer').val();
        let comment = $('#comment_review_modal_itemViewer').val();
        $.ajax({
            type: "POST",
            url: "SaveReview",
            data: {
                "productId": productId,
                "customerId": customerId,
                "title": title,
                "comment": comment,
                "rateValue": rateValue
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
// Star button haddle
function setRate_itemViewer(rate) {
    rateValue = rate;
    console.log(rateValue);
    for (let index = (rateValue - 1); index < 5; index++) {
        let btnId = "#btn-star-" + (index + 1);
        $(btnId).html('<i class="fa-regular fa-star" style="color: #ffa41c;"></i>');
    }
    for (let index = 0; index < rateValue; index++) {
        let btnId = "#btn-star-" + (index + 1);
        $(btnId).html('<i class="fa-solid fa-star" style="color: #ffa41c;">');
    }
}

// <-------- Wish List manage ---------->

async function saveWishList(customerId, productId){
    console.log('called!');
    let wishListId = await saveWishListSRVR_itemViewer(customerId, productId);
    $('#wishListbtn_div_itemViewer').html(`<button class="btn-itemActions" onclick="removeWishList(${wishListId},${customerId},'${productId}')">Remove from wish list<i class="fa-solid fa-heart fa-2xl ms-2" style="color: #ff8787;"></i></button>`);
}

async function removeWishList(wishListId,customerId, productId){
    try {
        console.log('called!');
    await removeWishListSRVR_itemViewer(wishListId);
    $('#wishListbtn_div_itemViewer').html(`<button class="btn-itemActions" onclick="saveWishList(${customerId},'${productId}')">Add to wish list<i class="fa-solid fa-heart fa-2xl ms-2" style="color: rgb(169, 142, 213);"></i></button>`);

    } catch (e) {
        console.log(e);
    }
}

async function saveWishListSRVR_itemViewer(customerId, productId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "SaveWishList",
            data: {
                "customerId": customerId,
                "productId": productId
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

async function removeWishListSRVR_itemViewer(wishListId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "RemoveWishList",
            data: {
                "id": wishListId
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

// <---------- Add to Cart process ----------->

async function addToCart_itemViewer(itemId){
    await addItemToSessionCart_itemViewer(itemId);
    await updateHeadCartItemQTY_itemViewer();
    $('#cartbtn_div_itemViewer').html(`<button class="btn btn-danger w-100" style="height: 3rem;" onclick="removeFromCart_itemViewer('${itemId}')">Remove from the Cart</button>`);
}

async function removeFromCart_itemViewer(itemId){
    await removeItemFromSessionCart_itemViewer(itemId); 
    await updateHeadCartItemQTY_itemViewer();
    $('#cartbtn_div_itemViewer').html(`<button class="btn btn-danger w-100" style="height: 3rem;" onclick="addToCart_itemViewer('${itemId}')">Add to Cart</button>`);
}

async function updateHeadCartItemQTY_itemViewer() {
    $('#cartIcon-navBar').html(`
        <i class="fa-solid fa-cart-shopping fa-lg c-navbar-icon d-none d-lg-block"></i>Cart(<div style="width: 10px; height: 10px;" class="spinner-border text-info" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>)
    `);

    $('#cartIcon-navBar').html(`
        <i class="fa-solid fa-cart-shopping fa-lg c-navbar-icon d-none d-lg-block"></i>Cart(${await updateSessionCartItemCount_itemViewer()})
    `);
}

async function updateSessionCartItemCount_itemViewer() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GetSessionCartItemCount",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });

    });
}

async function addItemToSessionCart_itemViewer(itemId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "AddToSessionCart",
            data: {
                "itemId": itemId
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

async function removeItemFromSessionCart_itemViewer(itemId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "RemoveItemFromSessionCart",
            data: {
                "itemId": itemId
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