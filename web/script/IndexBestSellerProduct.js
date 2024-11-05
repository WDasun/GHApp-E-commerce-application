
let objArray;
let placeOneIndexNum;
let placeTwoIndexNum;
let placeThreeIndexNum;
let placeFourIndexNum;
$(document).ready(async function () {
    
    objArray = JSON.parse(await getProductInfo_index());
    
    if (0 < objArray.length) {
        placeOneIndexNum = 0;
    }
    if (1 < objArray.length) {
        placeTwoIndexNum = 1;
    }
    if (2 < objArray.length) {
        placeThreeIndexNum = 2;
    }
    if (3 < objArray.length) {
        placeFourIndexNum = 3;
    }

    setValuesForPlaces();
});

async function getProductInfo_index() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadSubCategoryList",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

function setValuesForPlaces() {
    $('#product_placeOne').html(createPlaceHtml(placeOneIndexNum));   
    $('#product_placeTwo').html(createPlaceHtml(placeTwoIndexNum));   
    $('#product_placeThree').html(createPlaceHtml(placeThreeIndexNum));   
    $('#product_placeFour').html(createPlaceHtml(placeFourIndexNum));   
}

function createPlaceHtml(indexNumber) {
    return `
        <div class="w-100" style="height: 260px;">
           <img src=${objArray[indexNumber].image} alt="" class="img-bestSellerProduct w-100" style="height: 260px;">
        </div>
        <div class="py-3">
           <button onclick="goToCollection('${objArray[indexNumber].categoryId}')" class="btn w-100 fs-5 text-center m-0" style="font-weight: 700;">${objArray[indexNumber].productName}</button>
        </div>
    `;
}

function goToCollection(catogoryId){
    window.location.href = "collection.jsp?categoryId=" + catogoryId;
}
    function rightBtnClick() {
        if ((placeOneIndexNum + 1) === objArray.length) {
            placeOneIndexNum = 0;
        } else {
            placeOneIndexNum++;
        }
        if ((placeTwoIndexNum + 1) === objArray.length) {
            placeTwoIndexNum = 0;
        } else {
            placeTwoIndexNum++;
        }
        if ((placeThreeIndexNum + 1) === objArray.length) {
            placeThreeIndexNum = 0;
        } else {
            placeThreeIndexNum++;
        }
        if ((placeFourIndexNum + 1) === objArray.length) {
            placeFourIndexNum = 0;
        } else {
            placeFourIndexNum++;
        }
        setValuesForPlaces();
    }

    function leftBtnClick() {
        if ((placeOneIndexNum - 1) === -1) {
            placeOneIndexNum = objArray.length - 1;
        } else {
            placeOneIndexNum--;
        }
        if ((placeTwoIndexNum - 1) === -1) {
            placeTwoIndexNum = objArray.length - 1;
        } else {
            placeTwoIndexNum--;
        }
        if ((placeThreeIndexNum - 1) === -1) {
            placeThreeIndexNum = objArray.length - 1;
        } else {
            placeThreeIndexNum--;
        }
        if ((placeFourIndexNum - 1) === -1) {
            placeFourIndexNum = objArray.length - 1;
        } else {
            placeFourIndexNum--;
        }
        setValuesForPlaces();
    }