package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import dao.WishListDAO;
import model.WishList;
import connection.ConnectionBuilder;
import org.hibernate.Session;
import dto.CartItem;
import java.util.ArrayList;
import dto.EncodeImageToBase64;
import model.Product;
import model.ProductItem;
import model.VariationOption;
import java.util.Set;
import model.Variation;
import model.ProductCategory;
import dao.CategoryDAO;
import dto.CartItem;
import java.util.ArrayList;
import model.Customer;

public final class collection_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/links.jsp");
    _jspx_dependants.add("/navBar.jsp");
  }

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
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <title>");

            String categoryId = request.getParameter("categoryId");
            ProductCategory subCategory = null;
            Session hsession_collection = ConnectionBuilder.hibSession();
            if (!categoryId.equals("") && categoryId != null) {
                subCategory = new CategoryDAO().searchById(categoryId, hsession_collection);
                out.print(subCategory.getName());
            }
            
      out.write("</title>\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css\">\n");
      out.write("<script defer src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("<script src=\"https://kit.fontawesome.com/d13ca0de67.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.7.1.js\" integrity=\"sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n");
      out.write("<link href=\"https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap\" rel=\"stylesheet\">");
      out.write("\n");
      out.write("\n");
      out.write("        <script src=\"script/collection.js\"></script>\n");
      out.write("        <script src=\"script/collectionWishList.js\"></script>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"style/collectionStyle.css\">\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<section>\n");
      out.write("    ");

        Customer customer = (Customer) session.getAttribute("currentCustomerAccount");
    
      out.write("\n");
      out.write("    <script src=\"script/header.js\"></script>\n");
      out.write("    <script src=\"script/navBarSearch.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" href=\"style/headerStyle.css\">\n");
      out.write("    <div class=\"container-fluid div-headLebal p-0\">\n");
      out.write("        <div class=\"row m-0 py-2\">\n");
      out.write("            <div class=\"d-flex\">\n");
      out.write("                <div class=\"d-flex align-items-center\">\n");
      out.write("                    <i class=\"fa-solid fa-phone fa-sm\" style=\"color: #74C0FC;\"></i>\n");
      out.write("                    <p class=\"txt-cnt-headLebal m-0 ms-2\">+767694090</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"d-flex align-items-center ms-4\">\n");
      out.write("                    <i class=\"fa-solid fa-envelope fa-sm\" style=\"color: #74C0FC;\"></i>\n");
      out.write("                    <p class=\"txt-cnt-headLebal m-0 ms-2\">vimukthi233@gmail.com</p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("<section class=\"container-fluid c-navbar py-1 border-bottom\">\n");
      out.write("    <div class=\"row\">\n");
      out.write("        <div class=\"col-2 d-flex align-items-center justify-content-center\">\n");
      out.write("            <div class=\"c-img-container d-flex align-items-center\">\n");
      out.write("                <button class=\"btn-logo\" onclick=\"onClickLogoBtn()\">Glimmer <span class=\"c-text-heaven\">Heaven</span></button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-6 d-flex\">\n");
      out.write("            <div class=\"input-group flex-nowrap my-2\" style=\"width: 100%;\">\n");
      out.write("                <input id=\"searchInput\" type=\"text\" class=\"form-control\" aria-label=\"What is on your mind ?\" aria-describedby=\"addon-wrapping\">\n");
      out.write("                <i class=\"fa-solid input-group-text fa-magnifying-glass fa-lg  d-none d-lg-flex\"></i>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-4 d-flex align-items-center justify-content-around\">\n");
      out.write("\n");
      out.write("            <button class=\"d-flex align-items-center justify-content-center btn-icn-navbar\" onclick=\"showOffcanvas_header()\" data-bs-toggle=\"offcanvas\" data-bs-target=\"#offcanvasMain\">\n");
      out.write("                <i class=\"fa-solid fa-icons fa-lg c-navbar-icon d-none d-lg-block\"></i>Category\n");
      out.write("            </button>\n");
      out.write("            <button class=\"d-flex align-items-center justify-content-center btn-icn-navbar\" id=\"cartIcon-navBar\" onclick=\"cartBtn_navBar()\"><i class=\"fa-solid fa-cart-shopping fa-lg c-navbar-icon d-none d-lg-block\"></i>\n");
      out.write("                ");

                    ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute("sessionCartItemList");
                    if (cartItemList != null) {
                        out.print("Cart(" + cartItemList.size() + ")");
                    } else {
                        out.print("Cart(0)");
                    }
                
      out.write("\n");
      out.write("            </button>\n");
      out.write("            ");

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
            
      out.write("        \n");
      out.write("            <button class=\"d-lg-none\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#section-one-collapse\" aria-expanded=\"false\" aria-controls=\"section-one-collapse\">\n");
      out.write("                <i class=\"fa-solid fa-bars fa-xl\"></i>\n");
      out.write("            </button>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"row\">\n");
      out.write("        <div class=\"col-2\"></div>\n");
      out.write("        <div class=\"col-6\">\n");
      out.write("            <ul class=\"list-group\" id=\"result-list\"> \n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-4\"></div>\n");
      out.write("    </div>\n");
      out.write("</section>\n");
      out.write("<!-- Offcanvas content -->\n");
      out.write("<div class=\"offcanvas offcanvas-start offCanvas-index\" tabindex=\"-1\" id=\"offcanvasMain\" aria-labelledby=\"offcanvasMain\">\n");
      out.write("    <div class=\"offcanvas-header border offCanvas-head-index\">\n");
      out.write("        <h5 class=\"offcanvas-title\" id=\"offcanvasExampleLabel\">\n");
      out.write("            ");

                if (customer != null) {
                    out.print("Hello, " + customer.getFname() + " " + customer.getLname() + "");
                } else {
                    out.print("You are not login !");
                }
            
      out.write("\n");
      out.write("        </h5>\n");
      out.write("        <button type=\"button\" class=\"btn-close text-reset\" data-bs-dismiss=\"offcanvas\" aria-label=\"Close\"></button>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"offcanvas-body p-0\" id=\"div-categoryList-header\">\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <section id=\"resultDetails\">\n");
      out.write("            <div class=\"container-fluid shadow-sm p-0 ps-3 d-flex align-items-center justify-content-start\" style=\"height: 37px;\">\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("        <section id=\"resultBody\">\n");
      out.write("            <div class=\"container-fluid \">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <!-- Left side  -->\n");
      out.write("                    <div class=\"col-2 ps-5\">\n");
      out.write("                        <div class=\"mt-4 div-siteDetails\">\n");
      out.write("                            <p class=\"m-0 fw-bold txt-title-siteDetails\">Site Details</p>\n");
      out.write("                            <div class=\"ps-3\">\n");
      out.write("                                ");
                                    if (subCategory != null) {
                                        out.print("<p class=\"m-0 fw-bold\">Parent category</p>\n"
                                                + "                                <p class=\"m-0\">- " + subCategory.getProductCategory().getName() + "</p>\n"
                                                + "                                <p class=\"m-0 fw-bold\">Sub category</p>\n"
                                                + "                                <p class=\"m-0\">- " + subCategory.getName() + "</p>");
                                    }
                                
      out.write("\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"mt-3 div-variationsAndOptins\">\n");
      out.write("                            <p class=\"m-0 fw-bold mb-1\">Filter products</p>\n");
      out.write("\n");
      out.write("                            ");

                                if (subCategory != null) {

                                    String html = "";

                                    Set<Variation> variationSet = subCategory.getVariations();
                                    for (Variation v : variationSet) {
                                        html += "<div class=\"ps-2\">\n"
                                                + "<p class=\"m-0 fw-bold\">" + v.getName() + "</p>\n"
                                                + "<div class=\"ms-3 pt-2 select-variationOptions\">";
                                        Set<VariationOption> variationOptionSet = v.getVariationOptions();
                                        for (VariationOption vo : variationOptionSet) {
                                            html += "<div class=\"form-check\">\n"
                                                    + "<input class=\"form-check-input\" type=\"checkbox\" value=\"" + vo.getId() + "\" id=\"flexCheckDefault\">\n"
                                                    + "<label class=\"form-check-label\" for=\"flexCheckDefault\">\n"
                                                    + "" + vo.getValue() + "\n"
                                                    + "</label>\n"
                                                    + "</div>";
                                        }
                                        html += "</div></div>";
                                    }
                                    out.print(html);
                                }
                            
      out.write("                        \n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <!-- Right side -->\n");
      out.write("                    <div class=\"col-10 p-0\">\n");
      out.write("                        <div class=\"container-fluid p-0\">\n");
      out.write("                            <!-- Title -->\n");
      out.write("                            <div class=\"row my-2 ps-2 pt-2 pb-2\">\n");
      out.write("                                ");

                                    String title = "<p class=\"txt-title-leftSideDiv m-0\">Item result for "+subCategory.getName()+"</p>";
                                    out.print(title);
                                
      out.write("\n");
      out.write("                               \n");
      out.write("                            </div>\n");
      out.write("                            <!-- Item List -->\n");
      out.write("                            <div class=\"row\">\n");
      out.write("                                <div>\n");
      out.write("                                    <div class=\"container-fluid p-0\" id=\"div-items-collection\">\n");
      out.write("                                        <!-- -------- Item row Start -------- -->\n");
      out.write("\n");
      out.write("                                        ");

                                            if (subCategory != null) {

                                                EncodeImageToBase64 e64 = new EncodeImageToBase64();

                                                String html = "";

                                                Set<Product> productSet = subCategory.getProducts();

                                                for (Product p : productSet) {
                                                    Set<ProductItem> itemSet = p.getProductItems();
                                                    Iterator<ProductItem> iterator = itemSet.iterator();
                                                    while (iterator.hasNext()) {
                                                        if (!iterator.next().isStatus()) {
                                                            iterator.remove();
                                                        }
                                                    }
                                                    ProductItem[] itemList = itemSet.toArray(new ProductItem[itemSet.size()]);

                                                    int itemAmount = itemList.length;
                                                    int itemAmountMin = itemAmount - 1;

                                                    int itemAmountForARow = 4;

                                                    int loopAmount = itemAmountMin + (itemAmountForARow - (itemAmountMin % itemAmountForARow));

                                                    for (int i = 0; i < loopAmount; i++) {
                                                        if ((i % itemAmountForARow) == 0) {
                                                            html += "<!-- New row -->"
                                                                    + "<div class=\"row m-0 mb-2\">";
                                                        }

                                                        if (itemAmountMin >= i) {
                                                            //Cart button manage
                                                            String addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickAddToCartBtn('" + itemList[i].getId() + "','addtoCartBtnDiv" + itemList[i].getId() + "')\"><i class=\"fa-solid fa-cart-shopping fa-2xl me-2\" style=\"color: #B197FC;\"></i></button>\n";
                                                            if (cartItemList != null) {
                                                                for (CartItem cartItem : cartItemList) {
                                                                    if (cartItem.getProductItem().getId().equals(itemList[i].getId())) {
                                                                        addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickRemoveItemCart('" + itemList[i].getId() + "','addtoCartBtnDiv" + itemList[i].getId() + "')\"><i class=\"fa-solid fa-trash fa-2xl\" style=\"color: #74C0FC;\"></i></button>";
                                                                        break;
                                                                    } else {
                                                                        addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickAddToCartBtn('" + itemList[i].getId() + "','addtoCartBtnDiv" + itemList[i].getId() + "')\"><i class=\"fa-solid fa-cart-shopping fa-2xl me-2\" style=\"color: #B197FC;\"></i></button>\n";
                                                                    }
                                                                }
                                                            } else {
                                                                addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickAddToCartBtn('" + itemList[i].getId() + "','addtoCartBtnDiv" + itemList[i].getId() + "')\"><i class=\"fa-solid fa-cart-shopping fa-2xl me-2\" style=\"color: #B197FC;\"></i></button>\n";
                                                            }

                                                            //Wish list manage
                                                            String wishListBtnHTML = "";
                                                            WishList wishList = new WishListDAO().searchByCustomerAndProductItem(customer, itemList[i]);
                                                            if (customer != null) {
                                                                if (wishList != null) {
                                                                    wishListBtnHTML = "<button class='btn-itemActions border-end' onclick='removeWishList_collection(" + wishList.getId() + "," + customer.getCustomerId() + ",\"" + itemList[i].getId() + "\")'><i class='fa-solid fa-heart fa-2xl me-2' style='color: #ff8787;'></i> </button>";
                                                                } else {
                                                                    wishListBtnHTML = "<button class='btn-itemActions border-end' onclick='saveWishList_collection(" + customer.getCustomerId() + ", \"" + itemList[i].getId() + "\")'><i class='fa-solid fa-heart fa-2xl me-2' style='color: rgb(169, 142, 213);'></i> </button>";
                                                                }
                                                            }

                                                            html += "<div class=\"col p-0\">\n"
                                                                    + "<!-- Card -->\n"
                                                                    + "<div class=\"card card-item\">\n"
                                                                    + "<div class=\"div-img-item\">\n"
                                                                    + "<img src=\"" + e64.encodeImage(itemList[i].getItemImgOnePath()) + "\" class=\"card-img-top\" alt=\"...\">\n"
                                                                    + " </div>\n"
                                                                    + "\n"
                                                                    + "<div class=\"card-body p-0 p-2\">\n"
                                                                    + "<h5 class=\"card-title m-0\">" + itemList[i].getName() + "</h5>\n"
                                                                    + "<div class=\"d-flex py-1 mt-1\">\n"
                                                                    + "<div>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "</div>\n"
                                                                    + "<p class=\"m-0 ms-2\">(256)</p>\n"
                                                                    + "</div>\n"
                                                                    + "<div class=\"d-flex\">\n"
                                                                    + "<p class=\"m-0 fs-6 me-1\">Price (LKR) : </p>\n"
                                                                    + "<p class=\"m-0 fs-5\">" + itemList[i].getPrice() + "</p>\n"
                                                                    + "</div>\n"
                                                                    + "<div class=\"d-flex mt-3\">\n"
                                                                    + "<div style='width: 100%;' id='wishListbtn_collection" + itemList[i].getId() + "'>"
                                                                    + wishListBtnHTML
                                                                    + "</div>"
                                                                    + "<div style=\"width: 100%;\" id=\"addtoCartBtnDiv" + itemList[i].getId() + "\">"
                                                                    + addToCartBtnHTML
                                                                    + "</div>"
                                                                    + "</div>\n"
                                                                    + "<div class=\"mt-3\">\n"
                                                                    + "<button class=\"w-100 btn-item-view\" onclick=\"onclickView_collection('" + itemList[i].getId() + "')\">View</button>\n"
                                                                    + "</div>\n"
                                                                    + "</div>\n"
                                                                    + "</div>\n"
                                                                    + "</div>";
                                                        } else {
                                                            html += "<div class=\"col p-0\">\n"
                                                                    + "<!-- Card -->\n"
                                                                    + "<div class=\"card card-item\">\n"
                                                                    + "\n"
                                                                    + "</div>\n"
                                                                    + "</div>";
                                                        }

                                                        if ((i % itemAmountForARow) == 4) {
                                                            html += "</div>"
                                                                    + "<!-- Row End -->";
                                                        }

                                                    }
                                                }

                                                out.print(html);
                                            }

                                        
      out.write("\n");
      out.write("                                        <!-- ---------- item rwo end -------- -->\n");
      out.write("\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("        <!-- Offcanvas content -->\n");
      out.write("        <div class=\"offcanvas offcanvas-start offCanvas-index\" tabindex=\"-1\" id=\"offcanvasMain\" aria-labelledby=\"offcanvasMain\">\n");
      out.write("            <div class=\"offcanvas-header border offCanvas-head-index\">\n");
      out.write("                <h5 class=\"offcanvas-title\" id=\"offcanvasExampleLabel\">Hello, Dasun Wimukthi</h5>\n");
      out.write("                <button type=\"button\" class=\"btn-close text-reset\" data-bs-dismiss=\"offcanvas\" aria-label=\"Close\"></button>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"offcanvas-body p-0\" id=\"div-categoryList-header\">\n");
      out.write("                <div class=\"pt-4 pb-3 border-bottom border-2\">\n");
      out.write("                    <p class=\"ms-5 txt-patentCategory\">Electronic Accessories</p>\n");
      out.write("                    <button class=\"btn p-0 ps-5 d-flex align-items-center btn-subCategory\" onclick=\"clickOnSubCategory_index('xxx')\">Laptops</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- Modal Def -->\n");
      out.write("        <div class=\"modal fade\" id=\"defModal-collection\" tabindex=\"-1\" aria-labelledby=\"defModel\" aria-hidden=\"true\">\n");
      out.write("            <!------------->\n");
      out.write("\n");
      out.write("            <!------------->\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </body>\n");
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
