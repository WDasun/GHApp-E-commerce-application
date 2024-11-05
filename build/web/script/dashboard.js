function leftMenuBtnArrowRT(x) {
    var icon = document.getElementById(x);
    icon.classList.toggle('icn-arrow-rotated');
}

function setLargMDL() {
    document.getElementById("defModel").innerHTML = "<div class=\"modal-dialog modal-dialog-centered modal-xl\">\n" +
            "                <div class=\"modal-content\" id=\"defModel-content\">\n" +
            "\n" +
            "                </div>\n" +
            "            </div>";
}

function setNormalMDL() {
    document.getElementById("defModel").innerHTML = "<div class=\"modal-dialog modal-dialog-centered\">\n" +
            "                <div class=\"modal-content\" id=\"defModel-content\">\n" +
            "\n" +
            "                </div>\n" +
            "            </div>";
}

function employeeLogout() {
    $(document).ready(function () {

        $.ajax({
            type: "POST",
            url: "EmployeeLogout",

            success: function (response) {

                let data = JSON.parse(response);

                window.location.href = data.redirectUrl;

            },
            error: function (error) {
                console.log(error);
            }
        });

    });
}