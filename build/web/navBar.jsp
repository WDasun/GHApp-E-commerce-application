<%-- 
    Document   : navBar
    Created on : Jun 26, 2024, 1:25:21 AM
    Author     : ASUS
--%>
<%@page import="dto.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<section>
    <%
        Customer customer = (Customer) session.getAttribute("currentCustomerAccount");
    %>
    <script src="script/header.js"></script>
    <script src="script/navBarSearch.js"></script>
    <link rel="stylesheet" href="style/headerStyle.css">
    <div class="container-fluid div-headLebal p-0">
        <div class="row m-0 py-2">
            <div class="d-flex">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-phone fa-sm" style="color: #74C0FC;"></i>
                    <p class="txt-cnt-headLebal m-0 ms-2">+767694090</p>
                </div>
                <div class="d-flex align-items-center ms-4">
                    <i class="fa-solid fa-envelope fa-sm" style="color: #74C0FC;"></i>
                    <p class="txt-cnt-headLebal m-0 ms-2">vimukthi233@gmail.com</p>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="container-fluid c-navbar py-1 border-bottom">
    <div class="row">
        <div class="col-lg-2 d-flex align-items-center justify-content-center">
            <div class="c-img-container d-flex align-items-center">
                <button class="btn-logo" onclick="onClickLogoBtn()">Glimmer <span class="c-text-heaven">Heaven</span></button>
            </div>
        </div>
        <div class="col-lg-6 d-flex">
            <div class="input-group flex-nowrap my-2" style="width: 100%;">
                <input id="searchInput" type="text" class="form-control" aria-label="What is on your mind ?" aria-describedby="addon-wrapping">
                <i class="fa-solid input-group-text fa-magnifying-glass fa-lg  d-none d-lg-flex"></i>
            </div>
        </div>
        <div class="col-4 d-flex align-items-center justify-content-around">

            <button class="d-flex align-items-center justify-content-center btn-icn-navbar" onclick="showOffcanvas_header()" data-bs-toggle="offcanvas" data-bs-target="#offcanvasMain">
                <i class="fa-solid fa-icons fa-lg c-navbar-icon d-none d-lg-block"></i>Category
            </button>
            <button class="d-flex align-items-center justify-content-center btn-icn-navbar" id="cartIcon-navBar" onclick="cartBtn_navBar()"><i class="fa-solid fa-cart-shopping fa-lg c-navbar-icon d-none d-lg-block"></i>
                <%
                    ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute("sessionCartItemList");
                    if (cartItemList != null) {
                        for (int i = 0; i < cartItemList.size(); i++) {
                            if(!cartItemList.get(i).getProductItem().isStatus()){
                                cartItemList.remove(i);
                            }
                        }
                        out.print("Cart(" + cartItemList.size() + ")");
                    } else {
                        out.print("Cart(0)");
                    }
                %>
            </button>
            <%
                if (customer != null) {
                    out.print("<button class=\"dropdown-toggle d-flex align-items-center justify-content-center btn-icn-navbar\" type=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\" id=\"btn-profile-index\">\n"
                            + "                            <div class=\"d-flex align-items-center justify-content-center\">\n"
                            + "                                <div class=\"me-2\">\n"
                            + "                                    <i class=\"fa-solid fa-id-badge fa-2xl\"></i>\n"
                            + "                                </div>\n"
                            + "                                <div>\n"
                            + "                                    <p class=\"text-start m-0 me-2\">Hello, " + customer.getFname() + "</p>\n"
                            + "                                    <p class=\"text-start m-0 me-2\" style=\"font-size: 0.7rem;\">Profile Manage :</p>\n"
                            + "                                </div>\n"
                            + "                            </div>\n"
                            + "                        </button>\n"
                            + "                        <ul class=\"dropdown-menu\" aria-labelledby=\"btn-profile-index\">\n"
                            + "                            <li>\n"
                            + "                                <button class=\"btn-option-profileManage border-0\" onclick=\"onClickProfileManage_header()\">\n"
                            + "                                        <div class=\"d-flex align-items-center justify-content-start ms-2\">\n"
                            + "                                            <div class=\"me-3\"><i class=\"fa-regular fa-user\"></i></div>\n"
                            + "                                            <div>Profile</div>\n"
                            + "                                        </div>\n"
                            + "                                    </button>\n"
                            + "                            </li>\n"
                            + "                            <li>\n"
                            + "                                <button class=\"btn-option-profileManage border-0\" onclick=\"onClickLogOut_header()\">\n"
                            + "                                    <div class=\"d-flex align-items-center justify-content-start ms-2\">\n"
                            + "                                        <div class=\"me-3\"><i class=\"fa-solid fa-person-running\"></i></i>\n"
                            + "                                        </div>\n"
                            + "                                        <div>Logout</div>\n"
                            + "                                    </div>\n"
                            + "                                    </button>\n"
                            + "                            </li>\n"
                            + "                        </ul>");
                } else {
                    out.print("<button class=\"d-flex align-items-center justify-content-center btn-icn-navbar\" "
                            + "onclick=\"onClickLogin_header()\"><i class=\"fa-solid fa-user fa-lg c-navbar-icon d-none d-lg-block\">"
                            + "</i>Login</button>");
                }
            %>        
        </div>
    </div>
    <div class="row">
        <div class="col-2"></div>
        <div class="col-6">
            <ul class="list-group" id="result-list"> 
            </ul>
        </div>
        <div class="col-4"></div>
    </div>
</section>
<!-- Offcanvas content -->
<div class="offcanvas offcanvas-start offCanvas-index" tabindex="-1" id="offcanvasMain" aria-labelledby="offcanvasMain">
    <div class="offcanvas-header border offCanvas-head-index">
        <h5 class="offcanvas-title" id="offcanvasExampleLabel">
            <%
                if (customer != null) {
                    out.print("Hello, " + customer.getFname() + " " + customer.getLname() + "");
                } else {
                    out.print("You are not login !");
                }
            %>
        </h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body p-0" id="div-categoryList-header">

    </div>
</div>

