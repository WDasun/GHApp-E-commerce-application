var loadingInfoHeader = "                    <div class=\"container-fluid\" style=\"width: 100%; height: 100%;\">\n" +
        "                        <div class=\"row h-100 align-items-center justify-content-center\">\n" +
        "                            <div class=\"spinner-border text-info\" role=\"status\">\n" +
        "                                <span class=\"visually-hidden\">Loading...</span>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";


function clickOnSubCategory_header(id) { 
    window.location.href = "collection.jsp?categoryId=" + id;
}

function showOffcanvas_header() {
    console.log('Called !');
    loadCategoryies_header();
    
    var myOffcanvas = new bootstrap.Offcanvas($('#offcanvasMain'));
    myOffcanvas.show();
}

async function loadCategoryies_header() {
    $('#div-categoryList-header').html(loadingInfoHeader);
    $('#div-categoryList-header').html(await loadCategoryListContent());
}

async function loadCategoryListContent() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "LoadCategoryListOffcanvas",
            success: function (response) {
                resolve(response);
            },
            error: function (error) {
                alert(error);
            }
        });
        
    });
}

function onClickLogin_header() {
    window.location.href = "CustomerLogin.jsp";
}

function onClickLogOut_header() {
    $.ajax({
        type: "POST",
        url: "CustomerLogOut",
        success: function (response) {
            window.location.href = "index.jsp";
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function onClickProfileManage_header(){
    window.location.href = "customerProfile.jsp";
}

function onClickLogoBtn(){
    window.location.href = "index.jsp";
}

function cartBtn_navBar(){
    window.location.href = "Cart.jsp";
}