/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Category;

import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "changeStatusCategory", urlPatterns = {"/changeStatusCategory"})
public class changeStatusCategory extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String newValueStr = request.getParameter("newValue").trim();
            boolean newValue = Boolean.parseBoolean(newValueStr);
            String id = request.getParameter("id").trim();

            if (!id.equals("") && id != null && newValueStr != null) {
                CategoryDAO cDAO = new CategoryDAO();
                cDAO.updateStatus(newValue, id);
                if (newValue) {
                    out.print("<!-- Activate Confirm Model content -->\n"
                            + "<div class=\"modal-header\">\n"
                            + "    <div class=\"d-flex align-items-center justify-content-start\"><i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n"
                            + "        <h5 class=\"m-0 ms-3\">Response</h5>\n"
                            + "    </div>\n"
                            + "</div>\n"
                            + "<div class=\"modal-body\">\n"
                            + "    <div class=\"container-fluid\">\n"
                            + "        <div class=\"row\">\n"
                            + "            <div class=\"col\">\n"
                            + "                <p class=\"m-0 fs-5 fw-normal\">Category <span class=\"fw-bold\">" + id + "</span> activated !</p>\n"
                            + "            </div>\n"
                            + "        </div>\n"
                            + "    </div>\n"
                            + "</div>\n"
                            + "<div class=\"modal-footer\">\n"
                            + "    <button class=\"btn btn-danger\" style=\"width: 100px;\" data-bs-dismiss=\"modal\">Ok</button>\n"
                            + "</div>");
                } else {
                    out.print("<!-- Inactivate Confirm Model content -->\n"
                            + "<div class=\"modal-header\">\n"
                            + "    <div class=\"d-flex align-items-center justify-content-start\"><i class=\"fa-solid fa-circle-question fa-2xl pt-1\" style=\"color: #e60000;\"></i>\n"
                            + "        <h5 class=\"m-0 ms-3\">Response</h5>\n"
                            + "    </div>\n"
                            + "</div>\n"
                            + "<div class=\"modal-body\">\n"
                            + "    <div class=\"container-fluid\">\n"
                            + "        <div class=\"row\">\n"
                            + "            <div class=\"col\">\n"
                            + "                <p class=\"m-0 fs-5 fw-normal\">Category <span class=\"fw-bold\">"+id+"</span> inactivated !</p>\n"
                            + "            </div>\n"
                            + "        </div>\n"
                            + "    </div>\n"
                            + "</div>\n"
                            + "<div class=\"modal-footer\">\n"
                            + "    <button class=\"btn btn-danger\" style=\"width: 100px;\" data-bs-dismiss=\"modal\">Ok</button>\n"
                            + "</div>");
                }
            } else {
                out.print("Data not valid !");
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
