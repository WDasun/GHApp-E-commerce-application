package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dao.CustomerDAO;
import dto.CartItem;
import java.util.ArrayList;
import model.Customer;

public final class customerProfile_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Customer Profile</title>\n");
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
      out.write("        ");

            Customer c = (Customer) session.getAttribute("currentCustomerAccount");
            if (c == null) {
                response.sendRedirect("CustomerLogin.jsp");
            }
        
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"style/customerProfileStyle.css\">\n");
      out.write("        <script src=\"script/customerProfile.js\"></script>\n");
      out.write("        <script src=\"script/customerProfileWishList.js\"></script>\n");
      out.write("    </head>\n");
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
      out.write("                <input type=\"text\" class=\"form-control\" placeholder=\"What is on your mind ?\" aria-label=\"What is on your mind ?\" aria-describedby=\"addon-wrapping\">\n");
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
                    if(cartItemList != null){
                        out.print("Cart("+cartItemList.size()+")");
                    }else{
                         out.print("Cart(0)");
                    }
                
      out.write("\n");
      out.write("            </button>\n");
      out.write("            ");
            
                if (customer != null) {
                    out.print("<button class=\"dropdown-toggle d-flex align-items-center justify-content-center btn-icn-navbar\" type=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\" id=\"btn-profile-index\">\n" +
"                            <div class=\"d-flex align-items-center justify-content-center\">\n" +
"                                <div class=\"me-2\">\n" +
"                                    <i class=\"fa-solid fa-id-badge fa-2xl\"></i>\n" +
"                                </div>\n" +
"                                <div>\n" +
"                                    <p class=\"text-start m-0 me-2\">Hello, "+customer.getFname()+"</p>\n" +
"                                    <p class=\"text-start m-0 me-2\" style=\"font-size: 0.7rem;\">Profile Manage :</p>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </button>\n" +
"                        <ul class=\"dropdown-menu\" aria-labelledby=\"btn-profile-index\">\n" +
"                            <li>\n" +
"                                <button class=\"btn-option-profileManage border-0\" onclick=\"onClickProfileManage_header()\">\n" +
"                                        <div class=\"d-flex align-items-center justify-content-start ms-2\">\n" +
"                                            <div class=\"me-3\"><i class=\"fa-regular fa-user\"></i></div>\n" +
"                                            <div>Profile</div>\n" +
"                                        </div>\n" +
"                                    </button>\n" +
"                            </li>\n" +
"                            <li>\n" +
"                                <button class=\"btn-option-profileManage border-0\" onclick=\"onClickLogOut_header()\">\n" +
"                                    <div class=\"d-flex align-items-center justify-content-start ms-2\">\n" +
"                                        <div class=\"me-3\"><i class=\"fa-solid fa-person-running\"></i></i>\n" +
"                                        </div>\n" +
"                                        <div>Logout</div>\n" +
"                                    </div>\n" +
"                                    </button>\n" +
"                            </li>\n" +
"                        </ul>");
                }else{
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
      out.write("        <div class=\"container-fluid p-0\">\n");
      out.write("            <div class=\"row m-0\">\n");
      out.write("                <div class=\"col-1 p-0\"></div>\n");
      out.write("                <div class=\"col-2 p-0 pt-4\" style=\"height: 50rem;\">\n");
      out.write("                    <div class=\"border-bottom\">\n");
      out.write("                        <p class=\"fs-4 m-0 ps-2\">Wellcome,</p>\n");
      out.write("                        <p class=\"fs-4 m-0 ps-5\">");

                            if (c != null) {
                                out.print(c.getFname() + " " + c.getLname());
                            }
                            
      out.write("</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"mt-3 ms-4\">\n");
      out.write("                        <p class=\"fs-5 fw-bold m-0\">Manage Account</p>\n");
      out.write("                        <button class=\"d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2 mt-2\" onclick=\"setProfileDetailsContent_customerProfile()\">Profile details</button>\n");
      out.write("                        <button class=\"d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2\" onclick=\"setAddressPoolContent_customerProfile()\">Address pool</button>\n");
      out.write("                        <button class=\"d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2\">Payment options</button>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"mt-3 ms-4\">\n");
      out.write("                        <p class=\"fs-5 fw-bold m-0\">Orders</p>\n");
      out.write("                        <button class=\"d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2 mt-2\">Recent</button>\n");
      out.write("                        <button class=\"d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2\">Cancellations</button>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"mt-3 ms-4\">\n");
      out.write("                        ");

                            out.print("<button onclick=\"loadWishList_customerProfile("+customer.getCustomerId()+")\" class=\"d-flex fs-5 fw-bold align-items-center w-100 btn-subTopic-cProfile p-0 mt-2\">Wish List</button>");
                        
      out.write("\n");
      out.write("                        \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-9 p-0 pt-4 ps-2 pe-5\">\n");
      out.write("                    <div class=\"h-100 was-validated w-100 div-dContent-cProfile px-4 py-4\" id=\"div-content-customerProfile\">\n");
      out.write("\n");
      out.write("                        <!-- content start -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <!-- content end -->\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <!-- Def Modal -->\n");
      out.write("\n");
      out.write("    <!-- Modal -->\n");
      out.write("    <div class=\"modal fade\" id=\"defModal-customerProfile\" tabindex=\"-1\" aria-labelledby=\"defModal-customerProfile\" aria-hidden=\"true\">\n");
      out.write("        <div class=\"modal-dialog modal-dialog-centered modal-xl\">\n");
      out.write("            <div class=\"modal-content\" id=\"defModel-content-customerProfile\">\n");
      out.write("            <!-- body start -->\n");
      out.write("\n");
      out.write("            <!-- body end -->\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("        <!-- Address Update Modal -->\n");
      out.write("    <div class=\"modal fade\" id=\"updateAddressModal_customerProfile\" tabindex=\"-1\" aria-labelledby=\"defModel\" aria-hidden=\"true\">\n");
      out.write("        <div class=\"modal-dialog modal-dialog-centered\">\n");
      out.write("            <div class=\"modal-content\" id=\"updateAddressModal_customerProfile_content\">\n");
      out.write("                <!-- ---------- -->\n");
      out.write("                \n");
      out.write("                <!-- ---------- -->\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
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
