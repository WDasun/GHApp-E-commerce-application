<%-- 
    Document   : dashBoard
    Created on : Jun 24, 2024, 11:41:45 PM
    Author     : ASUS
--%>

<%@page import="security.Commen.EmployeeAuthorization"%>
<%@page import="dto.EncodeImageToBase64"%>
<%@page import="model.SystemUser"%>
<%@page import="dto.CurrentEmployee"%>
<%@page import="org.hibernate.Session"%>
<%@page import="connection.ConnectionBuilder"%>
<%@page import="dao.SystemUserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%
            session = request.getSession();
            Session hSession = ConnectionBuilder.hibSession();
            CurrentEmployee currentEmployee = null;
            currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
            SystemUser systemUser = null;
            try {
                systemUser = new SystemUserDAO().searchById(currentEmployee.getUserName(), hSession);


        %>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>System</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <script src="https://kit.fontawesome.com/d13ca0de67.js" crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

        <!-- css -->
        <link rel="stylesheet" href="style/adminStyle.css">
        <link rel="stylesheet" href="style/addCatogryStyle.css">
        <link rel="stylesheet" href="style/categoryList.css">
        <link rel="stylesheet" href="style/dashBoardRoleManage.css">

        <!-- js -->
        <script src="script/dashboard.js"></script>
        <script src="script/addCategory.js"></script>
        <script src="script/categoryList.js"></script>
        <script src="script/addProduct.js"></script>
        <script src="script/productList.js"></script>
        <script src="script/addItem.js"></script>
        <script src="script/itemList.js"></script>
        <script src="script/addVariation.js"></script>
        <script src="script/variationList.js"></script>
        <script src="script/addVariationOption.js"></script>
        <script src="script/variationOptionList.js"></script>
        <script src="script/addEmployee.js"></script>
        <script src="script/employeeList.js"></script>
        <script src="script/role.js"></script>
        <script src="script/systemUserManage.js"></script>
        <script src="script/dashBoardCustomerOrders.js"></script>
        <script src="script/dashboardCustomerList.js"></script>
        <script src="script/categoryPromotionDashBoard.js"></script>


    </head>

    <body>
        <nav class="c-navbar">
            <div class="container-fluid h-100">
                <div class="row h-100 d-none d-xxl-flex">
                    <div class="col-2 d-flex align-items-center justify-content-center">
                        <p class="c-logo">Glimmer <span class="c-text-heaven">Heaven</span></p>
                    </div>
                    <div class="col-6 d-flex align-items-center">
                        <div class="input-group div-searchBar">
                           
                        </div>
                    </div>
                    <div class="col-4 d-flex align-items-center justify-content-between">
                        <div class="d-flex align-items-center">
                            <div class="div-profile-image">
                                <%                                    String imagePath = systemUser.getEmployee().getProfilePicturePath();

                                    String profileImageHTML = "<img src=\"" + new EncodeImageToBase64().encodeImage(imagePath) + "\" alt=\"\" class=\"img-profile-image\">";
                                    out.print(profileImageHTML);
                                %>
                            </div>
                            <div>
                                <%
                                    String nameAndRoleHTML = "<p class=\"mb-0 ms-2 txt-userName\">Wellcome, <span>" + systemUser.getEmployee().getFname() + " " + systemUser.getEmployee().getLname() + "</span><span class=\"txt-userType\"> / " + systemUser.getRole().getRoleName() + "</span></p>";
                                    out.print(nameAndRoleHTML);
                                %>
                            </div>
                        </div>
                            <div class="pe-3">
                            <button class="navbar-btn-icn dropdown-toggle" type="button" id="btn-settings" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa-solid fa-gear fa-lg"></i></button>
                            <ul class="dropdown-menu" aria-labelledby="btn-settings">
                                <li><button class="dropdown-item">Settings</button></li>
                                <li><button class="dropdown-item" onclick="employeeLogout()">Logout</button></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- Break point 1 -->
                <div class="row h-100 d-lg-flex d-xxl-none">
                    <div class="col-6 d-flex align-items-center justify-content-md-start ps-5">
                        <p class="c-logo">Glimmer <span class="c-text-heaven">Heaven</span></p>
                    </div>
                    <div class="col-6 d-flex align-items-center justify-content-end">
                        <button class="navbar-btn-hamburgerMenu me-5" data-bs-toggle="collapse" data-bs-target="#navbar-collapse"><i class="fa-solid fa-bars fa-2xl"></i></button>
                    </div>
                </div>
            </div>
        </nav>
        <!-- Nav bar collapse -->
        <div class="div-nebar-collapse collapse d-xxl-none" id="navbar-collapse">
            <div class="container-fluid">
                <div class="row border ps-4 d-flex align-items-center justify-content-sm-start div-navbar-collaps-items">
                    <div>
                        <p class="mb-0 ms-2 txt-userName">Wellcome, <span>Naruto Uzumaki</span><span class="txt-userType"> / Admin</span></p>
                    </div>
                </div>
                <div class="row border ps-4 d-flex align-items-center justify-content-sm-start div-navbar-collaps-items">
                    <button class="ms-2 d-flex btn-nebar-collapse">App Settings</button>
                </div>
                <div class="row border ps-4 d-flex align-items-center justify-content-sm-start div-navbar-collaps-items">
                    <button class="ms-2 d-flex btn-nebar-collapse" data-bs-toggle="offcanvas" data-bs-target="#leftmenu-offcanvas">Menu</button>
                </div>
            </div>
        </div>
        <!-- Offcanvas for left menu when goes to Navbar break point -->
        <div class="offcanvas offcanvas-start d-xxl-none" id="leftmenu-offcanvas">
            <div class="offcanvas-header">
                <h1 class="offcanvas-title">Menu</h1>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
            </div>
            <div class="offcanvas-body">

            </div>
        </div>
        <main>
            <div class="container-fluid">
                <div class="row d-none d-xxl-flex">
                    <!-- Left Menu -->
                    <div class="col-2 border py-3 div-leftmenu" id="div-leftmenu">
                        <!-- Shop Manage -->
                        <button class="p-0 btn-leftmenu" id="btn-shopManage" onclick="leftMenuBtnArrowRT('shop-icn-arrow')" type="button" data-bs-toggle="collapse" data-bs-target="#shopmanagecollapse">
                            <div class="container-fluid p-0">
                                <div class="row m-0">
                                    <div class="col-2 p-0 d-flex align-items-center justify-content-center">
                                        <i class="fa-solid fa-shop fa-lg"></i>
                                    </div>
                                    <div class="col-8 p-0 d-flex align-items-center justify-content-start">
                                        <p class="mb-0">Shop</p>
                                    </div>
                                    <div class="col-2 p-0 pe-1 d-flex align-items-center justify-content-end">
                                        <i class="fa-solid fa-angle-up icn-arrow" id="shop-icn-arrow"></i>
                                    </div>
                                </div>
                            </div>   
                        </button>
                        <div class="collapse" id="shopmanagecollapse">
                            <%
                                String shopManageHTML = " <!-- Category -->\n"
                                        + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" onclick=\"leftMenuBtnArrowRT('category-icn-arrow')\" data-bs-toggle=\"collapse\" data-bs-target=\"#categoriescollapse\">\n"
                                        + "                                <div class=\"container-fluid p-0\">\n"
                                        + "                                    <div class=\"row m-0\">\n"
                                        + "                                        <div class=\"col-2\"></div>\n"
                                        + "                                        <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n"
                                        + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Category</p>\n"
                                        + "                                        </div>\n"
                                        + "                                        <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n"
                                        + "                                            <i class=\"fa-solid fa-angle-up icn-arrow\" id=\"category-icn-arrow\"></i>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>\n"
                                        + "                                </div>   \n"
                                        + "                            </button>\n"
                                        + "                            <!-- Category sub -->\n"
                                        + "                            <div class=\"collapse\" id=\"categoriescollapse\">\n"
                                        + "                                <!-- btn Add category -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"addCategory\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Add category</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        
                                        + "                                <!-- btn Category list -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"categoryList\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Category list</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                      
                                          + "                                <!-- btn Category promotion -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" onclick=\"categoryPromotionDiv_dashBoard()\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Category Promotion</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        
                                        + "                            </div>\n"
                                        + "                            <!-- Product -->\n"
                                        + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" onclick=\"leftMenuBtnArrowRT('product-icn-arrow')\" data-bs-toggle=\"collapse\" data-bs-target=\"#productCollapse\">\n"
                                        + "                                <div class=\"container-fluid p-0\">\n"
                                        + "                                    <div class=\"row m-0\">\n"
                                        + "                                        <div class=\"col-2\"></div>\n"
                                        + "                                        <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n"
                                        + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Product</p>\n"
                                        + "                                        </div>\n"
                                        + "                                        <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n"
                                        + "                                            <i class=\"fa-solid fa-angle-up icn-arrow\" id=\"product-icn-arrow\"></i>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>\n"
                                        + "                                </div>   \n"
                                        + "                            </button>\n"
                                        + "                            <!-- Product sub -->\n"
                                        + "                            <div class=\"collapse\" id=\"productCollapse\">\n"
                                        + "                                <!-- btn Add product -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"addProduct\" onclick=\"addRightMenuAddProduct()\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Add product</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        + "                                <!-- btn product list -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"productList\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Product list</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        + "                            </div>\n"
                                        + "                            <!-- Item -->\n"
                                        + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" onclick=\"leftMenuBtnArrowRT('item-icn-arrow')\" data-bs-toggle=\"collapse\" data-bs-target=\"#itemCollapse\">\n"
                                        + "                                <div class=\"container-fluid p-0\">\n"
                                        + "                                    <div class=\"row m-0\">\n"
                                        + "                                        <div class=\"col-2\"></div>\n"
                                        + "                                        <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n"
                                        + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Item</p>\n"
                                        + "                                        </div>\n"
                                        + "                                        <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n"
                                        + "                                            <i class=\"fa-solid fa-angle-up icn-arrow\" id=\"item-icn-arrow\"></i>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>\n"
                                        + "                                </div>   \n"
                                        + "                            </button>\n"
                                        + "                            <!-- item sub -->\n"
                                        + "                            <div class=\"collapse\" id=\"itemCollapse\">\n"
                                        + "                                <!-- btn Add item -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"addItem\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Add Item</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        + "                                <!-- btn item list -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"itemList\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Item list</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        + "                            </div>\n"
                                        + "                            <!-- Variations and options -->\n"
                                        + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" onclick=\"leftMenuBtnArrowRT('variationAndOption-icn-arrow')\" data-bs-toggle=\"collapse\" data-bs-target=\"#variationAndOption\">\n"
                                        + "                                <div class=\"container-fluid p-0\">\n"
                                        + "                                    <div class=\"row m-0\">\n"
                                        + "                                        <div class=\"col-2\"></div>\n"
                                        + "                                        <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n"
                                        + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Variations and Options</p>\n"
                                        + "                                        </div>\n"
                                        + "                                        <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n"
                                        + "                                            <i class=\"fa-solid fa-angle-up icn-arrow\" id=\"variationAndOption-icn-arrow\"></i>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>\n"
                                        + "                                </div>   \n"
                                        + "                            </button>\n"
                                        + "                            <!-- Variations and options sub -->\n"
                                        + "                            <div class=\"collapse\" id=\"variationAndOption\">\n"
                                        + "                                <!-- btn Add Variations -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"addVariation\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Add variations</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        + "                                <!-- btn variation list -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"variationList\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Variation list</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        + "                                <!-- btn Add Variation option -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"addVariationOption\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Add variation option</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        + "                                <!-- btn variation option list -->\n"
                                        + "                                <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"variationOptionList\">\n"
                                        + "                                    <div class=\"container-fluid p-0\">\n"
                                        + "                                        <div class=\"row m-0\">\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                            <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                        + "                                                <p class=\"mb-0 txt-leftmenu-sub\">Variation option list</p>\n"
                                        + "                                            </div>\n"
                                        + "                                            <div class=\"col-2\"></div>\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>   \n"
                                        + "                                </button>\n"
                                        + "                            </div>";
                            %>
                            <!-- Customer order -->
                            <%
                                String customerOrderHTML = "<button class=\"p-0 btn-leftmenu\" type=\"button\" onclick=\"clickOnCustomerOrders_dashBoard()\">\n"
                                        + "                                <div class=\"container-fluid p-0\">\n"
                                        + "                                    <div class=\"row m-0\">\n"
                                        + "                                        <div class=\"col-2\"></div>\n"
                                        + "                                        <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n"
                                        + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Customer Orders</p>\n"
                                        + "                                        </div>\n"
                                        + "                                        <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n"
                                        + "\n"
                                        + "                                        </div>\n"
                                        + "                                    </div>\n"
                                        + "                                </div>   \n"
                                        + "                            </button>";
                                if (new EmployeeAuthorization().CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f3")) {
                                    if(new EmployeeAuthorization().CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f3") && !new EmployeeAuthorization().CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f5")){
                                        out.print(customerOrderHTML);
                                    }else{
                                        shopManageHTML += customerOrderHTML;
                                    }
                                }
                                if (new EmployeeAuthorization().CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f5")) {
                                    out.print(shopManageHTML);
                                }
                            %>
                        </div>
                        <%
                            String emplyeeManageHTML = "<!-- Employee manage -->\n"
                                    + "                        <button class=\"p-0 btn-leftmenu\" id=\"btn-usermanage\" onclick=\"leftMenuBtnArrowRT('employee-icn-arrow')\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collaps-emplyee\">\n"
                                    + "                            <div class=\"container-fluid p-0\">\n"
                                    + "                                <div class=\"row m-0\">\n"
                                    + "                                    <div class=\"col-2 p-0 d-flex align-items-center justify-content-center\">\n"
                                    + "                                        <i class=\"fa-solid fa-user-tie fa-lg\"></i>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n"
                                    + "                                        <p class=\"mb-0\">Employee</p>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n"
                                    + "                                        <i class=\"fa-solid fa-angle-up icn-arrow\" id=\"employee-icn-arrow\"></i>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>\n"
                                    + "                            </div>   \n"
                                    + "                        </button>\n"
                                    + "                        <!-- Emplyee collaps -->\n"
                                    + "                        <div class=\"collapse\" id=\"collaps-emplyee\">\n"
                                    + "                            <!-- btn Add employee -->\n"
                                    + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"btn-addEmployee\">\n"
                                    + "                                <div class=\"container-fluid p-0\">\n"
                                    + "                                    <div class=\"row m-0\">\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                        <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                    + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Add Employee</p>\n"
                                    + "                                        </div>\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>   \n"
                                    + "                            </button>\n"
                                    + "                            <!-- btn employee list-->\n"
                                    + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"btn-employeeList\">\n"
                                    + "                                <div class=\"container-fluid p-0\">\n"
                                    + "                                    <div class=\"row m-0\">\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                        <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                    + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Employee list</p>\n"
                                    + "                                        </div>\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>   \n"
                                    + "                            </button>\n"
                                    + "                        </div>";

                            if (new EmployeeAuthorization().CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f2")) {
                                out.print(emplyeeManageHTML);
                            }

                        %>
                        
                        <%                            String customerManageHTML = "<!-- Customer manage -->\n"
                                    + "                        <button class=\"p-0 btn-leftmenu\" id=\"btn-usermanage\" onclick=\"leftMenuBtnArrowRT('systemUserManage-icn-arrow')\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collaps-customerManage\">\n"
                                    + "                            <div class=\"container-fluid p-0\">\n"
                                    + "                                <div class=\"row m-0\">\n"
                                    + "                                    <div class=\"col-2 p-0 d-flex align-items-center justify-content-center\">\n"
                                    + "                                        <i class=\"fa-regular fa-circle-user fa-lg\"></i>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n"
                                    + "                                        <p class=\"mb-0\">Customer manage</p>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n"
                                    + "                                        <i class=\"fa-solid fa-angle-up icn-arrow\" id=\"systemUserManage-icn-arrow\"></i>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>\n"
                                    + "                            </div>   \n"
                                    + "                        </button>\n"
                                    + "                        <!-- System user manage -->\n"
                                    + "                        <div class=\"collapse\" id=\"collaps-customerManage\">\n"
                                    + "                            <!-- btn role -->\n"
                                    + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" onclick=\"clickOneCustomerList_dashBoard()\">\n"
                                    + "                                <div class=\"container-fluid p-0\">\n"
                                    + "                                    <div class=\"row m-0\">\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                        <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                    + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Customer List</p>\n"
                                    + "                                        </div>\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>   \n"
                                    + "                            </button>\n"
                                    + "                        </div>";

                            if (new EmployeeAuthorization().CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f6")) {
                                out.print(customerManageHTML);
                            }

                        %>

                        <%                            String systemUserManageHTML = "<!-- System user manage -->\n"
                                    + "                        <button class=\"p-0 btn-leftmenu\" id=\"btn-usermanage\" onclick=\"leftMenuBtnArrowRT('systemUserManage-icn-arrow')\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collaps-systemUserManage\">\n"
                                    + "                            <div class=\"container-fluid p-0\">\n"
                                    + "                                <div class=\"row m-0\">\n"
                                    + "                                    <div class=\"col-2 p-0 d-flex align-items-center justify-content-center\">\n"
                                    + "                                        <i class=\"fa-solid fa-circle-user fa-lg\"></i>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n"
                                    + "                                        <p class=\"mb-0\">System user manage</p>\n"
                                    + "                                    </div>\n"
                                    + "                                    <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n"
                                    + "                                        <i class=\"fa-solid fa-angle-up icn-arrow\" id=\"systemUserManage-icn-arrow\"></i>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>\n"
                                    + "                            </div>   \n"
                                    + "                        </button>\n"
                                    + "                        <!-- System user manage -->\n"
                                    + "                        <div class=\"collapse\" id=\"collaps-systemUserManage\">\n"
                                    + "                            <!-- btn role -->\n"
                                    + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"btn-role\">\n"
                                    + "                                <div class=\"container-fluid p-0\">\n"
                                    + "                                    <div class=\"row m-0\">\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                        <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                    + "                                            <p class=\"mb-0 txt-leftmenu-sub\">Role</p>\n"
                                    + "                                        </div>\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>   \n"
                                    + "                            </button>\n"
                                    + "                            <!-- btn System user-->\n"
                                    + "                            <button class=\"p-0 btn-leftmenu\" type=\"button\" id=\"btn-systemUser\">\n"
                                    + "                                <div class=\"container-fluid p-0\">\n"
                                    + "                                    <div class=\"row m-0\">\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                        <div class=\"col-8 ps-3 d-flex align-items-center justify-content-start\">\n"
                                    + "                                            <p class=\"mb-0 txt-leftmenu-sub\">System user</p>\n"
                                    + "                                        </div>\n"
                                    + "                                        <div class=\"col-2\"></div>\n"
                                    + "                                    </div>\n"
                                    + "                                </div>   \n"
                                    + "                            </button>\n"
                                    + "                        </div>";

                            if (new EmployeeAuthorization().CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f1")) {
                                out.print(systemUserManageHTML);
                            }

                        %>
                    </div>
                    <!-- Right Menu -->
                    <div class="col-10 pt-2 py-3 border div-rightmenu" id="div-rightmenu">
                        <!-- ............................... -->



                        <!-- ............................... -->
                    </div>
                </div>
            </div>
        </main>
        <!-- Modal -->
        <div class="modal fade" id="defModel" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" id="defModel-content">
                    <!-- ------------------------------------------- -->



                    <!-- ------------------------------------------- -->
                </div>
            </div>
        </div>

        <!--sub Modal -->
        <div class="modal fade" id="subModel" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" id="subModel-content">

                </div>
            </div>
        </div>

        <!-- SubModal 2 -->
        <div class="modal fade" id="subModal2-dashBoard" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content" id="content-subModal2-dashBoard">
                    <!-- ---------- -->

                </div>
            </div>
        </div>

    </body>
    <%        } catch (NullPointerException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/EmployeeLogin.jsp");
        } finally {

        }

    %>
</html>
