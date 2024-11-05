/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.systemUser;

import connection.ConnectionBuilder;
import dao.SystemUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SystemUser;
import org.hibernate.Session;

/**
 *
 * @author Dasun
 */
public class loadSystemUserList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Session session = ConnectionBuilder.hibSession();
            List<SystemUser> userList = new SystemUserDAO().getSystemUserList(session);
            System.out.println("System User list size : "+userList.size());
            String html = "";
            for (SystemUser systemUser : userList) {
                if (!systemUser.getRole().getRoleId().equals("admin")) {
                    if (systemUser.isStatus()) {
                        html += "<tr>\n"
                                + "<td>" + systemUser.getUserName() + "</td>\n"
                                + "<td>" + systemUser.getEmployee().getEmployeeId() + " : " + systemUser.getEmployee().getFname() + " " + systemUser.getEmployee().getLname() + "</td>\n"
                                + "<td>" + systemUser.getRole().getRoleName() + "</td>\n"
                                + "<td>" + systemUser.getLastLogin() + "</td>\n"
                                + "<td>" + systemUser.getLastUpdate() + "</td>\n"
                                + "<td>Active</td>\n"
                                + "<td class=\"\">\n"
                                + "<button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateSystemUser('" + systemUser.getUserName() + "')\">Update</button>\n"
                                + "<button class=\"btn btn-danger btn-categoryList-tableTow\" onclick=\"clickOnchangeStatus_systemUser('" + systemUser.getUserName() + "', false)\">Inactivate</button>\n"
                                + "</td>\n"
                                + "</tr>";
                    } else {
                        html += "<tr>\n"
                                + "<td>" + systemUser.getUserName() + "</td>\n"
                                + "<td>" + systemUser.getEmployee().getEmployeeId() + " : " + systemUser.getEmployee().getFname() + " " + systemUser.getEmployee().getLname() + "</td>\n"
                                + "<td>" + systemUser.getRole().getRoleName() + "</td>\n"
                                + "<td>" + systemUser.getLastLogin() + "</td>\n"
                                + "<td>" + systemUser.getLastUpdate() + "</td>\n"
                                + "<td>Inactive</td>\n"
                                + "<td class=\"\">\n"
                                + "<button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateSystemUser('" + systemUser.getUserName() + "')\">Update</button>\n"
                                + "<button class=\"btn btn-warning btn-categoryList-tableTow\" onclick=\"clickOnchangeStatus_systemUser('" + systemUser.getUserName() + "', true)\">Activate</button>\n"
                                + "</td>\n"
                                + "</tr>";
                    }
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
