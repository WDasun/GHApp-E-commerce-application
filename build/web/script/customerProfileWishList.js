
async function loadWishList_customerProfile(customerId) {
    $('#div-content-customerProfile').html(loadingInfo_customerProfile);
    let wishList = JSON.parse(await requestWishListByCustomer_customerProfile(customerId));
    let wListHTML = "<div class='h-75' style='overflow-y: auto;'>";
    wishList.forEach(function (item) {
        wListHTML += `  <div class="d-flex">
                            <div class='d-flex align-items-center w-75 border border-2 rounded-3 py-2 px-2 my-2'>
                                <div style='width: 7rem; height: 5rem;'>
                                    <img src='${item.itemImage}' style='height: inherit; width: inherit; overflow: hidden' alt='...'>
                                </div>
                                <div style='overflow: hidden;'>
                                    <p class='fw-bold m-0 ms-3'>MSI RAIDER GE78 HX 14VIG I9 14TH GEN + RTX 4090 MSI RAIDER GE78 HX 14VIG I9 14TH GEN + RTX 4090</p>
                                </div>
                            </div>
                            <div class="d-flex align-items-center justify-content-center w-25 rounded-3 py-2 px-2 my-2">
                                <button class="btn me-2" onclick="removeWishListItem_customerProfile(${item.id}, ${item.customerId})">
                                    <i class="fa-solid fa-heart fa-2xl me-2" style="color: #ff8787;"></i>
                                </button>
                                <button class="btn me-2" onclick="addWishListItemToCart_customerProfile(${item.id},'${item.productItemId}',${item.customerId})">
                                    <i class="fa-solid fa-cart-shopping fa-2xl me-2" style="color: #B197FC;"></i>
                                </button>
                                <button class="btn" onclick="viewWishListItem_customerProfile('${item.productItemId}')">
                                    <i class="fa-solid fa-magnifying-glass fa-2xl" style="color: #74C0FC;"></i>
                                </button>
                            </div>
                        </div>`;
    });
    wListHTML += "</div>";
    $('#div-content-customerProfile').html(wListHTML);
}

function viewWishListItem_customerProfile(itemId){
    window.location.href = "itemViewer.jsp?itemId=" + itemId;
}

async function addWishListItemToCart_customerProfile(wishListId,itemId,customerId){
    $('#div-content-customerProfile').html(loadingInfo_customerProfile);
    await removeWishListSRVR_customerProfile(wishListId);
    await addItemToSessionCart_customerProfile(itemId);
    await updateHeadCartItemQTY_customerProfile();
    await loadWishList_customerProfile(customerId);
}

async function updateHeadCartItemQTY_customerProfile() {
    $('#cartIcon-navBar').html(`
        <i class="fa-solid fa-cart-shopping fa-lg c-navbar-icon d-none d-lg-block"></i>Cart(<div style="width: 10px; height: 10px;" class="spinner-border text-info" role="status">
            <span class="visually-hidden">Loading...</span>
          </div>)
    `);

    $('#cartIcon-navBar').html(`
        <i class="fa-solid fa-cart-shopping fa-lg c-navbar-icon d-none d-lg-block"></i>Cart(${await updateSessionCartItemCount_customerProfile()})
    `);
}

async function updateSessionCartItemCount_customerProfile() {
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

async function removeWishListItem_customerProfile(wishListId, customerId){
        $('#div-content-customerProfile').html(loadingInfo_customerProfile);
        await removeWishListSRVR_customerProfile(wishListId);
        await loadWishList_customerProfile(customerId);
}

async function addItemToSessionCart_customerProfile(itemId) {
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

async function removeWishListSRVR_customerProfile(wishListId) {
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

async function requestWishListByCustomer_customerProfile(customerId) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "GetWishListByCustomer",
            data: {
                "customerId": customerId
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
