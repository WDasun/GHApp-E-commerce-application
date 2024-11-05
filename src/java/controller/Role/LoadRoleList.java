/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Role;

import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Role;

/**
 *
 * @author Dasun
 */
public class LoadRoleList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            List<Role> roleList = new RoleDAO().getRoleList();
            String html = "";
            for (Role role : roleList) {
                if (!role.getRoleId().equals("admin")) {
                    html += "<tr>\n"
                            + "<td>" + role.getRoleId() + "</td>\n"
                            + "<td>" + role.getRoleName() + "</td>\n"
                            + "<td>" + role.getRoleDescription() + "</td>\n"
                            + "<td class=\"\">\n"
                            + "<button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateRole('" + role.getRoleId() + "')\">Update</button>\n"
                            + "</td>\n"
                            + "</tr>";
                }
            }
            out.print(html);
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
