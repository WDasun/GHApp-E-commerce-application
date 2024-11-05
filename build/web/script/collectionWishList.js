//Wish list manage

async function saveWishList_collection(customerId, productId) {
    $('#wishListbtn_collection' + productId).html("<button class='btn-itemActions border-end'><i class='fa-solid fa-heart fa-flip fa-2xl' style='color: #74C0FC;'></i></i> </button>");
    let wishListId = await saveWishListSRVR_collection(customerId, productId);
    $('#wishListbtn_collection' + productId).html(`<button class='btn-itemActions border-end' onclick='removeWishList_collection(${wishListId},${customerId},"${productId}")'><i class='fa-solid fa-heart fa-2xl me-2' style='color: #ff8787;'></i> </button>`);
}

async function saveWishListSRVR_collection(customerId, productId) {
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

async function removeWishList_collection(wishListId, customerId, productId) {
    $('#wishListbtn_collection' + productId).html("<button class='btn-itemActions border-end'><i class='fa-solid fa-heart fa-flip fa-2xl' style='color: #74C0FC;'></i></i> </button>");
    await removeWishListSRVR_collection(wishListId);
    $('#wishListbtn_collection' + productId).html(`
        <button class='btn-itemActions border-end' onclick="saveWishList_collection(${customerId}, '${productId}')">
            <i class='fa-solid fa-heart fa-2xl me-2' style='color: rgb(169, 142, 213);'></i>
        </button>`);
}

async function removeWishListSRVR_collection(wishListId) {
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
