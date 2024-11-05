/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Role;

import connection.ConnectionBuilder;
import dao.RoleDAO;
import dto.CurrentEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FirstLevelAccess;
import model.Role;
import org.hibernate.Session;
import security.Commen.EmployeeAuthorization;
import security.Log.CreateLog;

/**
 *
 * @author Dasun
 */
@MultipartConfig
public class saveRole extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            CurrentEmployee currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
            EmployeeAuthorization eA = new EmployeeAuthorization();
            
            if (currentEmployee != null && eA.CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f1")) {

                String roleIdAddRole = request.getParameter("roleIdAddRole");
                String roleNameAddRole = request.getParameter("roleNameAddRole");
                String roleDescriptionAddRole = request.getParameter("roleDescriptionAddRole");
                String f1AddRole = request.getParameter("f1AddRole");
                String f2AddRole = request.getParameter("f2AddRole");
                String f3AddRole = request.getParameter("f3AddRole");
                String f4AddRole = request.getParameter("f4AddRole");
                String f5AddRole = request.getParameter("f5AddRole");
                String f6AddRole = request.getParameter("f6AddRole");

                try {
                    if (roleIdAddRole != null && !roleIdAddRole.equals("")
                            && roleNameAddRole != null && !roleNameAddRole.equals("")
                            && roleDescriptionAddRole != null && !roleDescriptionAddRole.equals("")
                            && !roleIdAddRole.equals("admin")) {

                        Role role = new Role();
                        role.setRoleId(roleIdAddRole);
                        role.setRoleName(roleNameAddRole);
                        role.setRoleDescription(roleDescriptionAddRole);

                        FirstLevelAccess fla = new FirstLevelAccess();
                        if (f1AddRole.equals("true")) {
                            fla.setF1(true);
                        }
                        if (f2AddRole.equals("true")) {
                            fla.setF2(true);
                        }
                        if (f3AddRole.equals("true")) {
                            fla.setF3(true);
                        }
                        if (f4AddRole.equals("true")) {
                            fla.setF4(true);
                        }
                        if (f5AddRole.equals("true")) {
                            fla.setF5(true);
                        }
                        if (f6AddRole.equals("true")) {
                            fla.setF6(true);
                        }

                        new RoleDAO().save(role, fla);

                        out.print("Role save successful !");
                        
                        // Log Employee Activity
                        new CreateLog("info", "Saving a role.", currentEmployee.getEmployeeId(), saveRole.class.getName()).employeeActivey();
                        
                    } else {
                        out.print("Invalid Data !");
                    }
                } catch (Exception e) {
                    out.print("Role save failed !");
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
