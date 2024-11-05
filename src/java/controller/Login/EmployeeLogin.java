/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Login;

import com.google.gson.JsonObject;
import common.MD5;
import connection.ConnectionBuilder;
import dao.SystemUserDAO;
import dto.CurrentEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SystemUser;
import org.hibernate.Session;
import security.Log.CreateLog;

/**
 *
 * @author Dasun
 */
@MultipartConfig
public class EmployeeLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            Session hSession = ConnectionBuilder.hibSession();
            JsonObject jsonResponse = new JsonObject();

            if (userName != null && !userName.equals("")) {

                String encryptedUserPassword = new MD5().getMd5(password);
                SystemUserDAO suDAO = new SystemUserDAO();
                SystemUser systemUser = suDAO.searchSystemUserByUserNameAndPassword(userName, encryptedUserPassword, hSession);
                if (systemUser != null) {

                    if (systemUser.isStatus()) {

                        HttpSession session = request.getSession();
                        CurrentEmployee currentEmployee = new CurrentEmployee();
                        currentEmployee.setUserName(systemUser.getUserName());
                        currentEmployee.setEmployeeId(systemUser.getEmployee().getEmployeeId());
                        currentEmployee.setRoleId(systemUser.getRole().getRoleId());
                        session.setAttribute("CurrentEmployee", currentEmployee);

                        // Log Employee Activity
                        new CreateLog("info", "Employee system login.", currentEmployee.getEmployeeId(), EmployeeLogin.class.getName()).employeeActivey();

                        jsonResponse.addProperty("redirect", true);
                        jsonResponse.addProperty("redirectUrl", request.getContextPath() + "/dashBoard.jsp");

                    } else {
                        jsonResponse.addProperty("redirect", false);
                        jsonResponse.addProperty("message", "User is Inactivate !");
                    }

                } else {
                    jsonResponse.addProperty("redirect", false);
                    jsonResponse.addProperty("message", "Incorrect user name or password !");
                }
            } else {
                jsonResponse.addProperty("redirect", false);
                jsonResponse.addProperty("message", "Data not valid !");
            }
            out.print(jsonResponse.toString());

//            hSession.close();
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
