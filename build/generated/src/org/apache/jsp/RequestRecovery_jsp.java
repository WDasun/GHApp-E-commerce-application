package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class RequestRecovery_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/links.jsp");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Request recovery</title>\n");
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
      out.write("        <script src=\"script/requestRecovery.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"style/emplyeeLoginStyle.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"vh-100\">\n");
      out.write("            <main>\n");
      out.write("            <section class=\"c-navbar container-fluid border border-bottom-1\">\n");
      out.write("                <div class=\"row h-100\">\n");
      out.write("\n");
      out.write("                    <div class=\"d-flex align-items-center justify-content-center\">\n");
      out.write("                        <div class=\"c-img-container d-flex align-items-center justify-content-center\">\n");
      out.write("                            <p class=\"c-logo m-0 fw-bold my-2 fs-1\">Glimmer <span class=\"c-text-heaven\">Heaven</span></p>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </section>\n");
      out.write("        </main>\n");
      out.write("        <section>\n");
      out.write("            <div class=\"container mt-5\">\n");
      out.write("                <div class=\"row div-login justify-content-center\">\n");
      out.write("                    <div class=\"col-md-6\">\n");
      out.write("                        <div class=\"h-100 w-100 d-flex align-items-center\">\n");
      out.write("                            <div class=\"w-100\" id=\"div_requestRecivery\">\n");
      out.write("                                <h3 class=\"card-title text-center\">Request Recovery</h3>\n");
      out.write("                                <p class=\"text-center text-muted\">Recovery</p>\n");
      out.write("                                <div class=\"fw-bold was-validated\">\n");
      out.write("                                    <div class=\"mb-3\">\n");
      out.write("                                        <label for=\"email_recovery\" class=\"form-label\">Email</label>\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" id=\"email_requestRecovery\" placeholder=\"Enter email\" required>\n");
      out.write("                                        <div class=\"invalid-feedback\">Please enter a user email.</div>\n");
      out.write("                                    </div>     \n");
      out.write("                                </div>\n");
      out.write("                                <button type=\"button\" class=\"btn btn-warning fw-bold w-100\" onclick=\"requestRecovery()\">Request</button>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("        </div>\n");
      out.write("        ");
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
