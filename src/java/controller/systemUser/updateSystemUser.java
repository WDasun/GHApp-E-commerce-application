/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.systemUser;

import connection.ConnectionBuilder;
import dao.RoleDAO;
import dao.SystemUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Role;
import model.SystemUser;
import org.hibernate.Session;

/**
 *
 * @author Dasun wimukthi
 */
@MultipartConfig
@WebServlet(name = "updateSystemUser", urlPatterns = {"/updateSystemUser"})
public class updateSystemUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String userName = request.getParameter("userName");
            String roleId = request.getParameter("roleId");

            if (!roleId.equals("admin")) {
                Session session = ConnectionBuilder.hibSession();

                try {

                    Role role = new RoleDAO().searchById(roleId, session);
                    SystemUser systemUser = new SystemUserDAO().searchById(userName, session);
                    if (role != null && systemUser != null) {
                        systemUser.setRole(role);
                        new SystemUserDAO().update(systemUser, session);
                        out.print("Update successful !");
                    } else {
                        out.print("Data not valid !");
                    }

                } catch (Exception e) {
                    out.print("Update failed !");
                    e.printStackTrace();
                }
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
