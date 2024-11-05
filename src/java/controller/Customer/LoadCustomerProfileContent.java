/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoadCustomerProfileContent", urlPatterns = {"/LoadCustomerProfileContent"})
public class LoadCustomerProfileContent extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession hs = request.getSession();
            Customer c = (Customer) hs.getAttribute("currentCustomerAccount");
            if (c != null) {
                String status = "";
                if(c.isStatus()){
                    status = "Active";
                }else{
                    status = "Inactive";
                }
                String html = "<div class=\"div-profileDetails\">\n"
                        + "    <form id=\"form-profileDetails-customerProfile\">\n"
                        + "        <div class=\"d-flex\">\n"
                        + "            <div class=\"w-50 mb-3 me-4\">\n"
                        + "                <label for=\"fname-cProfile\" class=\"form-label\">First name</label>\n"
                        + "                <input type=\"text\" class=\"form-control\" name=\"fname\" value=\"" + c.getFname() + "\" id=\"fname-cProfile\" required>\n"
                        + "            </div>\n"
                        + "            <div class=\"w-50 mb-3\">\n"
                        + "                <label for=\"lname-cProfile\" class=\"form-label\">Last name</label>\n"
                        + "                <input type=\"text\" class=\"form-control\" name=\"lname\" value=\"" + c.getLname() + "\" id=\"lname-cProfile\" required>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "        <div>\n"
                        + "            <div class=\"d-flex\">\n"
                        + "                <div class=\"w-50 mb-3 me-4\">\n"
                        + "                    <label for=\"email-cProfile\" class=\"form-label\">Email</label>\n"
                        + "                    <input type=\"email\" class=\"form-control\" value=\"" + c.getEmail() + "\" name=\"email\" id=\"email-cProfile\" required>\n"
                        + "                </div>\n"
                        + "                <div class=\"w-50 mb-3\">\n"
                        + "                    <label for=\"contact-cProfile\" class=\"form-label\">Contact Number</label>\n"
                        + "                    <input type=\"text\" class=\"form-control\" value=\"" + c.getCntNumber() + "\" name=\"contact\" id=\"contact-cProfile\" required>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class=\"d-flex\">\n"
                        + "                <div class=\"w-50 mb-3 me-4\">\n"
                        + "                    <label for=\"dob-cProfile\" class=\"form-label\">Date of Birth</label>\n"
                        + "                    <input type=\"date\" class=\"form-control\" value=\"" + new SimpleDateFormat("yyyy-MM-dd").format(c.getDob()) + "\" name=\"dob\" id=\"dob-cProfile\" required>\n"
                        + "                </div>\n"
                        + "                <div class=\"mb-3\">\n"
                        + "                    <label for=\"status-cProfile\" class=\"form-label\">Status</label>\n"
                        + "                    <input type=\"text\" class=\"form-control\" value=\"" + status + "\" name=\"status\" id=\"status-cProfile\" readonly>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "            <div class=\"mb-3\">\n"
                        + "                <label for=\"createdDate-cProfile\" class=\"form-label\">Created Date</label>\n"
                        + "                <input type=\"text\" class=\"form-control\" value=\"" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getCreatedAt()) + "\" name=\"createdDate\" id=\"createdDate-cProfile\" readonly>\n"
                        + "            </div>\n"
                        + "            <div class=\"mb-3\">\n"
                        + "                <label for=\"lastUpdate-cProfile\" class=\"form-label\">Last Update</label>\n"
                        + "                <input type=\"text\" class=\"form-control\" value=\"" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getUpdatedAt()) + "\" name=\"lastUpdate\" id=\"lastUpdate-cProfile\" readonly>\n"
                        + "            </div>\n"
                        + "            <div class=\"mb-3 d-flex justify-content-end\">\n"
                        + "                <button type=\"button\" class=\"btn btn-primary w-25\" onclick=\"onclickUpdate_customerProfile()\">Update</button>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </form>\n"
                        + "</div>";
                
                out.print(html);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
