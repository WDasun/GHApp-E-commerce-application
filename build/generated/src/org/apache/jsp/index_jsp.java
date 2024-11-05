package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.PromotionDAO;
import dto.EncodeImageToBase64;
import model.ProductItem;
import dao.ItemDAO;
import java.util.List;
import org.hibernate.Session;
import connection.ConnectionBuilder;
import dto.CartItem;
import java.util.ArrayList;
import model.Customer;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/links.jsp");
    _jspx_dependants.add("/navBar.jsp");
    _jspx_dependants.add("/footer.jsp");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        ");

            Session hSession = ConnectionBuilder.hibSession();
        
      out.write("\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <title>Home Page</title>\n");
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
      out.write("        <link rel=\"stylesheet\" href=\"style/indexStyle.css\">\n");
      out.write("        <script src=\"script/IndexBestSellerProduct.js\"></script>\n");
      out.write("        <script src=\"script/IndexForYou.js\"></script>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <main>\n");
      out.write("            ");
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
      out.write("        <div class=\"col-lg-2 d-flex align-items-center justify-content-center\">\n");
      out.write("            <div class=\"c-img-container d-flex align-items-center\">\n");
      out.write("                <button class=\"btn-logo\" onclick=\"onClickLogoBtn()\">Glimmer <span class=\"c-text-heaven\">Heaven</span></button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-lg-6 d-flex\">\n");
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
                        for (int i = 0; i < cartItemList.size(); i++) {
                            if(!cartItemList.get(i).getProductItem().isStatus()){
                                cartItemList.remove(i);
                            }
                        }
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
      out.write("            <section class=\"c-carousel d-none d-lg-block\">\n");
      out.write("                <div id=\"carouselExampleCaptions\" class=\"carousel slide mx-5 my-4\" data-bs-ride=\"carousel\">\n");
      out.write("                    <div class=\"carousel-indicators\">\n");
      out.write("                        <button type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide-to=\"0\" class=\"active\" aria-current=\"true\" aria-label=\"Slide 1\"></button>\n");
      out.write("                        <button type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide-to=\"1\" aria-label=\"Slide 2\"></button>\n");
      out.write("                        <button type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide-to=\"2\" aria-label=\"Slide 3\"></button>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"carousel-inner c-carousel-item\">\n");
      out.write("                        <div class=\"carousel-item active c-item\">\n");
      out.write("                            <img src=\"https://images.unsplash.com/photo-1472851294608-062f824d29cc?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\" class=\"d-block w-100\" alt=\"...\">\n");
      out.write("                            <div class=\"carousel-caption d-none d-md-block\" style=\"background-color: rgba(0,0,0,0.2)\">\n");
      out.write("                                <h5>Welcome to Glimmer Heaven</h5>\n");
      out.write("                                <p>Where every sparkle tells a story. Discover elegance, style, and timeless treasures tailored just for you.</p>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"carousel-item c-item\">\n");
      out.write("                            <img src=\"https://plus.unsplash.com/premium_photo-1664202526744-516d0dd22932?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\" class=\"d-block w-100\" alt=\"...\">\n");
      out.write("                            <div class=\"carousel-caption d-none d-md-block\" style=\"background-color: rgba(0,0,0,0.2)\">\n");
      out.write("                                <h5>Step into Elegance</h5>\n");
      out.write("                                <p>Discover the perfect blend of fashion and comfort with every pair. Let your style shine with Glimmer Heaven.</p>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"carousel-item c-item\">\n");
      out.write("                            <img src=\"https://images.unsplash.com/photo-1519033628719-72e1861f20cf?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\" class=\"d-block w-100\" alt=\"...\">\n");
      out.write("                            <div class=\"carousel-caption d-none d-md-block\" style=\"background-color: rgba(0,0,0,0.2)\">\n");
      out.write("                                <h5>Work with Excellence</h5>\n");
      out.write("                                <p>Where creativity meets productivity. Transform your workspace with Glimmer Heaven’s inspired office essentials.</p>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide=\"prev\">\n");
      out.write("                        <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n");
      out.write("                        <span class=\"visually-hidden\">Previous</span>\n");
      out.write("                    </button>\n");
      out.write("                    <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#carouselExampleCaptions\" data-bs-slide=\"next\">\n");
      out.write("                        <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n");
      out.write("                        <span class=\"visually-hidden\">Next</span>\n");
      out.write("                    </button>\n");
      out.write("                </div>\n");
      out.write("            </section>\n");
      out.write("\n");
      out.write("            <!-- Best seller product -->\n");
      out.write("            <section class=\"\">\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("                    <div class=\"row w-100 py-5\">\n");
      out.write("                        <p class=\"txt-sectionHeadTextStyle-1 text-center\">Best seller product</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col d-flex align-items-center justify-content-end\">\n");
      out.write("                            <button onclick=\"rightBtnClick()\" class=\"btn btn-secondary w-25 h-25 me-5\"><i class=\"fa-solid fa-chevron-left fa-2xl\"></i></button>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col mx-2 border rounded-3\">\n");
      out.write("                            <div id=\"product_placeOne\">\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col mx-2 border rounded-3\">\n");
      out.write("                            <div id=\"product_placeTwo\">\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col mx-2 border rounded-3\">\n");
      out.write("                            <div id=\"product_placeThree\">\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col mx-2 border rounded-3\">\n");
      out.write("                            <div id=\"product_placeFour\">\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col d-flex align-items-center justify-content-start\">\n");
      out.write("                            <button onclick=\"leftBtnClick()\" class=\"btn btn-secondary w-25 h-25 ms-5\"><i class=\"fa-solid fa-chevron-left fa-flip-horizontal fa-2xl\"></i></button>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </section>\n");
      out.write("\n");
      out.write("            <!-- Latest Deals -->\n");
      out.write("            <section>\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <p class=\"txt-sectionHeadTextStyle-1 text-center my-5\">Latest Deals</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col d-flex align-items-center justify-content-end\">\n");
      out.write("                            <img src=\"/GHApp/images/add4.webp\" alt=\"\" class=\"w-75 rounded-3\" style=\"height: 400px;\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col d-flex align-items-center justify-content-center\">\n");
      out.write("                            <img src=\"/GHApp/images/add3.webp\" alt=\"\" class=\"w-75 rounded-3\" style=\"height: 400px;\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col d-flex align-items-center justify-content-start\">\n");
      out.write("                            <img src=\"/GHApp/images/add2.webp\" alt=\"\" class=\"w-75 rounded-3\" style=\"height: 400px;\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </section>\n");
      out.write("\n");
      out.write("            <!-- For you -->\n");
      out.write("            <section>\n");
      out.write("                <div class=\"container-fluid px-5 pb-5\">\n");
      out.write("                    <div class=\"row px-5 pb-3\">\n");
      out.write("                        <p class=\"txt-sectionHeadTextStyle-1 pt-5\">For you</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div id=\"div-forYou-index\">\n");
      out.write("                        ");
                            out.print(new ItemDAO().generateItemRowIndex(0, 5, hSession));
                        
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"d-flex align-items-center justify-content-center my-3\">\n");
      out.write("                        <button class=\"btn fs-5 border-bottom\" onclick=\"loadMoreItemRow_index()\">Load more..</button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </section>\n");
      out.write("\n");
      out.write("        </main>\n");
      out.write("                        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<footer>\n");
      out.write("        <div class=\"container-fluid div-footer\">\n");
      out.write("            <div class=\"row d-flex align-items-center justify-content-end\">\n");
      out.write("                <div class=\"div-socailMedia me-5 mt-4\">\n");
      out.write("                    <div>\n");
      out.write("                        <p class=\"fw-bold m-0 mb-2\">Follow us</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"d-flex\">\n");
      out.write("                        <div class=\"div-icon-facebook me-2\">\n");
      out.write("                            <a href=\"#\"><img src=\"logo/facebook.png\" class=\"h-100 w-100\" alt=\"\"></a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"div-icon-youtube me-2\">\n");
      out.write("                            <a href=\"#\"><img src=\"logo/youtube.png\" class=\"h-100 w-100\" alt=\"\"></a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"div-icon-instagram me-2\">\n");
      out.write("                            <a href=\"#\"><img src=\"logo/instagram.png\" class=\"h-100 w-100\" alt=\"\"></a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row d-flex align-items-center justify-content-center\">\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </footer>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("    ");


    
      out.write("\n");
      out.write("</html>");
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
