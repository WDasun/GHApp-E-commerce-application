package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CartItem;
import java.util.ArrayList;
import model.Customer;

public final class paymentResponse_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Response</title>\n");
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
      out.write("        ");
 
            String filePath = request.getParameter("filePath");
            String decodedFilePath = filePath.replace("\\", "\\\\");
        
      out.write("\n");
      out.write("        <div class=\"d-flex align-items-center justify-content-center vh-100\" style=\"margin-top: 7rem\">\n");
      out.write("            <div class=\"w-75\">\n");
      out.write("                <p class=\"fs-3 text-center\">Payment Successful!</p>\n");
      out.write("                <p class=\"text-center\">Thank you for your purchase.<br> An invoice has been generated and sent to your email.</p>\n");
      out.write("                <div class=\"d-flex align-items-center justify-content-center\">\n");
      out.write("                    ");

                        out.print("<button class='btn btn-primary w-25 me-2' onclick='downloadPdf_paymentResponse(\"" + decodedFilePath + "\")'>Download Invoice</button>");
                    
      out.write("\n");
      out.write("                    <button class=\"btn btn-primary w-25 ms-2\" onclick=\"goToIndex_paymentResponse()\">Go to Home</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("                     ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<link rel=\"stylesheet\" href=\"style/footerStyle.css\">\n");
      out.write("<footer>\n");
      out.write("        <div class=\"container-fluid div-footer mt-4\">\n");
      out.write("            <div class=\"row d-flex align-items-center justify-content-end\">\n");
      out.write("                <div class=\"div-socailMedia me-5 mt-4\">\n");
      out.write("                    <div>\n");
      out.write("                        <p class=\"fw-bold m-0 mb-2\">Follow us</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"d-flex\">\n");
      out.write("                        <div class=\"div-icon-facebook me-2\">\n");
      out.write("                            <a href=\"#\"><img src=\"images/facebook.png\" class=\"h-100 w-100\" alt=\"\"></a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"div-icon-youtube me-2\">\n");
      out.write("                            <a href=\"#\"><img src=\"images/youtube.png\" class=\"h-100 w-100\" alt=\"\"></a>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"div-icon-instagram me-2\">\n");
      out.write("                            <a href=\"#\"><img src=\"images/instagram.png\" class=\"h-100 w-100\" alt=\"\"></a>\n");
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
      out.write("    <script>\n");
      out.write("        function goToIndex_paymentResponse(){\n");
      out.write("            window.location.href = 'index.jsp';\n");
      out.write("        }\n");
      out.write("        function downloadPdf_paymentResponse(filePath) {\n");
      out.write("            window.location.href = 'DowloadInvoice?filePath=' + encodeURIComponent(filePath);\n");
      out.write("        }\n");
      out.write("    </script>\n");
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
