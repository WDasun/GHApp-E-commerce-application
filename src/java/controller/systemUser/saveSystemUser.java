/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.systemUser;

import common.MD5;
import dao.EmployeeDAO;
import dao.RoleDAO;
import dao.SystemUserDAO;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SystemUser;

/**
 *
 * @author Dasun
 */
@MultipartConfig
public class saveSystemUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String employeeId = request.getParameter("employeeId");
            String roleId = request.getParameter("roleId");

            if (!roleId.equals("admin")) {
                if (!userName.equals("") && userName != null
                        && !password.equals("") && password != null
                        && !employeeId.equals("") && employeeId != null
                        && !roleId.equals("") && roleId != null) {

                    if (new SystemUserDAO().checkAvailabilityEmplyeeHasUserAccount(new EmployeeDAO().searchById(employeeId))) {

                        if (new SystemUserDAO().checkAvailability(userName)) {

                            SystemUser su = new SystemUser();
                            su.setUserName(userName);
                            su.setUserPassword(new MD5().getMd5(password));
                            su.setEmployee(new EmployeeDAO().searchById(employeeId));
                            su.setRole(new RoleDAO().searchById(roleId));
                            su.setLastLogin(new Date());
                            su.setCreatedDate(new Date());
                            su.setLastUpdate(new Date());
                            su.setStatus(true);

                            new SystemUserDAO().save(su);

                            out.print(new HTMLContents().responseContent("User account saved !"));
                        } else {
                            out.print(new HTMLContents().responseContent("User name not valid !"));
                        }

                    }

                } else {
                    out.print(new HTMLContents().responseContent("Data not valid !"));
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
