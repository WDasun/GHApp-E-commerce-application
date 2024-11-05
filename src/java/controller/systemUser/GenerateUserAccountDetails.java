/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.systemUser;

import com.google.gson.Gson;
import dao.RoleDAO;
import dao.SystemUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Role;
import model.SystemUser;

/**
 *
 * @author Dasun
 */
public class GenerateUserAccountDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String systemUserName = request.getParameter("systemUserName").trim();

            if (!systemUserName.equals("") && systemUserName != null) {

                SystemUser su = new SystemUserDAO().searchById(systemUserName);

                List<Role> roleList = new RoleDAO().getRoleList();
                String html = "";
                for (Role role : roleList) {
                    if (!role.getRoleId().equals("admin")) {
                        if (su.getRole().getRoleId().equals(role.getRoleId())) {
                            html += "<option value=\"" + role.getRoleId() + "\" selected>" + role.getRoleId() + " : " + role.getRoleName() + "</option>";
                        } else {
                            html += "<option value=\"" + role.getRoleId() + "\">" + role.getRoleId() + " : " + role.getRoleName() + "</option>";
                        }
                    }
                }

                HashMap<String, String> data = new HashMap<>();
                data.put("userName", su.getUserName());
                data.put("employeeId", su.getEmployee().getEmployeeId());
                data.put("roleList", html);

                out.print(new Gson().toJson(data));
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
