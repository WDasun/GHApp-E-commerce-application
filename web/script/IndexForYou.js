let startPoint = 5;
let loadCount = 0;
function viewItem_index(productItemId) {
    window.location.href = "itemViewer.jsp?itemId=" + productItemId;
}

async function loadMoreItemRow_index(totalLoadCound) {
    if(loadCount === 0){
        loadCount = totalLoadCound;
    }
    let prevHtml = $('#div-forYou-index').html();
    $('#div-forYou-index').html(prevHtml + await getItemRow_index());
    startPoint = startPoint + 5;
}

async function getItemRow_index() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: 'POST',
            url: "LoadProductItems",
            data: {
                "startPoint": startPoint
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

