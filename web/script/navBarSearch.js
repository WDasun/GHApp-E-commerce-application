
$(document).ready(function () {
    $('#searchInput').on('input',async function () {
            $('#result-list').html("Searching ...");
            $('#result-list').html(await searchItem_navBar($('#searchInput').val()));
    });

});

async function searchItem_navBar(partialName) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "SearchItem",
            data: {
                "partialName" : partialName
            },
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                alert(error);
            }
        });
        
    });
}

function goToView_navBar(itemId){
    window.location.href = "itemViewer.jsp?itemId=" + itemId;
}