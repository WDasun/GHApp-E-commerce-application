package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import security.Commen.EmployeeAuthorization;
import dto.EncodeImageToBase64;
import model.SystemUser;
import dto.CurrentEmployee;
import org.hibernate.Session;
import connection.ConnectionBuilder;
import dao.SystemUserDAO;

public final class dashBoard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        ");

            session = request.getSession();
            Session hSession = ConnectionBuilder.hibSession();
            CurrentEmployee currentEmployee = null;
            currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
            SystemUser systemUser = null;
            try {
                systemUser = new SystemUserDAO().searchById(currentEmployee.getUserName(), hSession);


        
      out.write("\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <title>System</title>\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css\">\n");
      out.write("        <script defer src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"https://kit.fontawesome.com/d13ca0de67.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.7.1.js\" integrity=\"sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("        <!-- css -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"style/adminStyle.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"style/addCatogryStyle.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"style/categoryList.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"style/dashBoardRoleManage.css\">\n");
      out.write("\n");
      out.write("        <!-- js -->\n");
      out.write("        <script src=\"script/dashboard.js\"></script>\n");
      out.write("        <script src=\"script/addCategory.js\"></script>\n");
      out.write("        <script src=\"script/categoryList.js\"></script>\n");
      out.write("        <script src=\"script/addProduct.js\"></script>\n");
      out.write("        <script src=\"script/productList.js\"></script>\n");
      out.write("        <script src=\"script/addItem.js\"></script>\n");
      out.write("        <script src=\"script/itemList.js\"></script>\n");
      out.write("        <script src=\"script/addVariation.js\"></script>\n");
      out.write("        <script src=\"script/variationList.js\"></script>\n");
      out.write("        <script src=\"script/addVariationOption.js\"></script>\n");
      out.write("        <script src=\"script/variationOptionList.js\"></script>\n");
      out.write("        <script src=\"script/addEmployee.js\"></script>\n");
      out.write("        <script src=\"script/employeeList.js\"></script>\n");
      out.write("        <script src=\"script/role.js\"></script>\n");
      out.write("        <script src=\"script/systemUserManage.js\"></script>\n");
      out.write("        <script src=\"script/dashBoardCustomerOrders.js\"></script>\n");
      out.write("        <script src=\"script/categoryPromotionDashBoard.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"c-navbar\">\n");
      out.write("            <div class=\"container-fluid h-100\">\n");
      out.write("                <div class=\"row h-100 d-none d-xxl-flex\">\n");
      out.write("                    <div class=\"col-2 d-flex align-items-center justify-content-center\">\n");
      out.write("                        <p class=\"c-logo\">Glimmer <span class=\"c-text-heaven\">Heaven</span></p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-6 d-flex align-items-center\">\n");
      out.write("                        <div class=\"input-group div-searchBar\">\n");
      out.write("                           \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-4 d-flex align-items-center justify-content-between\">\n");
      out.write("                        <div class=\"d-flex align-items-center\">\n");
      out.write("                            <div class=\"div-profile-image\">\n");
      out.write("                                ");
                                    String imagePath = systemUser.getEmployee().getProfilePicturePath();

                                    String profileImageHTML = "<img src=\"" + new EncodeImageToBase64().encodeImage(imagePath) + "\" alt=\"\" class=\"img-profile-image\">";
                                    out.print(profileImageHTML);
                                
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                            <div>\n");
      out.write("                                ");

                                    String nameAndRoleHTML = "<p class=\"mb-0 ms-2 txt-userName\">Wellcome, <span>" + systemUser.getEmployee().getFname() + " " + systemUser.getEmployee().getLname() + "</span><span class=\"txt-userType\"> / " + systemUser.getRole().getRoleName() + "</span></p>";
                                    out.print(nameAndRoleHTML);
                                
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                            <div class=\"pe-3\">\n");
      out.write("                            <button class=\"navbar-btn-icn dropdown-toggle\" type=\"button\" id=\"btn-settings\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"><i class=\"fa-solid fa-gear fa-lg\"></i></button>\n");
      out.write("                            <ul class=\"dropdown-menu\" aria-labelledby=\"btn-settings\">\n");
      out.write("                                <li><button class=\"dropdown-item\">Settings</button></li>\n");
      out.write("                                <li><button class=\"dropdown-item\" onclick=\"employeeLogout()\">Logout</button></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <!-- Break point 1 -->\n");
      out.write("                <div class=\"row h-100 d-lg-flex d-xxl-none\">\n");
      out.write("                    <div class=\"col-6 d-flex align-items-center justify-content-md-start ps-5\">\n");
      out.write("                        <p class=\"c-logo\">Glimmer <span class=\"c-text-heaven\">Heaven</span></p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-6 d-flex align-items-center justify-content-end\">\n");
      out.write("                        <button class=\"navbar-btn-hamburgerMenu me-5\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbar-collapse\"><i class=\"fa-solid fa-bars fa-2xl\"></i></button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("        <!-- Nav bar collapse -->\n");
      out.write("        <div class=\"div-nebar-collapse collapse d-xxl-none\" id=\"navbar-collapse\">\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <div class=\"row border ps-4 d-flex align-items-center justify-content-sm-start div-navbar-collaps-items\">\n");
      out.write("                    <div>\n");
      out.write("                        <p class=\"mb-0 ms-2 txt-userName\">Wellcome, <span>Naruto Uzumaki</span><span class=\"txt-userType\"> / Admin</span></p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"row border ps-4 d-flex align-items-center justify-content-sm-start div-navbar-collaps-items\">\n");
      out.write("                    <button class=\"ms-2 d-flex btn-nebar-collapse\">App Settings</button>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"row border ps-4 d-flex align-items-center justify-content-sm-start div-navbar-collaps-items\">\n");
      out.write("                    <button class=\"ms-2 d-flex btn-nebar-collapse\" data-bs-toggle=\"offcanvas\" data-bs-target=\"#leftmenu-offcanvas\">Menu</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- Offcanvas for left menu when goes to Navbar break point -->\n");
      out.write("        <div class=\"offcanvas offcanvas-start d-xxl-none\" id=\"leftmenu-offcanvas\">\n");
      out.write("            <div class=\"offcanvas-header\">\n");
      out.write("                <h1 class=\"offcanvas-title\">Menu</h1>\n");
      out.write("                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"offcanvas\"></button>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"offcanvas-body\">\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <main>\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <div class=\"row d-none d-xxl-flex\">\n");
      out.write("                    <!-- Left Menu -->\n");
      out.write("                    <div class=\"col-2 border py-3 div-leftmenu\" id=\"div-leftmenu\">\n");
      out.write("                        <!-- Shop Manage -->\n");
      out.write("                        <button class=\"p-0 btn-leftmenu\" id=\"btn-shopManage\" onclick=\"leftMenuBtnArrowRT('shop-icn-arrow')\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#shopmanagecollapse\">\n");
      out.write("                            <div class=\"container-fluid p-0\">\n");
      out.write("                                <div class=\"row m-0\">\n");
      out.write("                                    <div class=\"col-2 p-0 d-flex align-items-center justify-content-center\">\n");
      out.write("                                        <i class=\"fa-solid fa-shop fa-lg\"></i>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"col-8 p-0 d-flex align-items-center justify-content-start\">\n");
      out.write("                                        <p class=\"mb-0\">Shop</p>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"col-2 p-0 pe-1 d-flex align-items-center justify-content-end\">\n");
      out.write("                                        <i class=\"fa-solid fa-angle-up icn-arrow\" id=\"shop-icn-arrow\"></i>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>   \n");
      out.write("                        </button>\n");
      out.write("                        <div class=\"collapse\" id=\"shopmanagecollapse\">\n");
      out.write("                            ");

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
                            
      out.write("\n");
      out.write("                            <!-- Customer order -->\n");
      out.write("                            ");

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
                            
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                        ");

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

                        
      out.write("\n");
      out.write("\n");
      out.write("                        ");
                            String systemUserManageHTML = "<!-- System user manage -->\n"
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

                        
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <!-- Right Menu -->\n");
      out.write("                    <div class=\"col-10 pt-2 py-3 border div-rightmenu\" id=\"div-rightmenu\">\n");
      out.write("                        <!-- ............................... -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <!-- ............................... -->\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </main>\n");
      out.write("        <!-- Modal -->\n");
      out.write("        <div class=\"modal fade\" id=\"defModel\" tabindex=\"-1\" aria-labelledby=\"defModel\" aria-hidden=\"true\">\n");
      out.write("            <div class=\"modal-dialog\">\n");
      out.write("                <div class=\"modal-content\" id=\"defModel-content\">\n");
      out.write("                    <!-- ------------------------------------------- -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <!-- ------------------------------------------- -->\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!--sub Modal -->\n");
      out.write("        <div class=\"modal fade\" id=\"subModel\" tabindex=\"-1\" aria-labelledby=\"defModel\" aria-hidden=\"true\">\n");
      out.write("            <div class=\"modal-dialog\">\n");
      out.write("                <div class=\"modal-content\" id=\"subModel-content\">\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- SubModal 2 -->\n");
      out.write("        <div class=\"modal fade\" id=\"subModal2-dashBoard\" tabindex=\"-1\" aria-labelledby=\"defModel\" aria-hidden=\"true\">\n");
      out.write("            <div class=\"modal-dialog modal-dialog-centered\">\n");
      out.write("                <div class=\"modal-content\" id=\"content-subModal2-dashBoard\">\n");
      out.write("                    <!-- ---------- -->\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("    ");
        } catch (NullPointerException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/EmployeeLogin.jsp");
        } finally {

        }

    
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
