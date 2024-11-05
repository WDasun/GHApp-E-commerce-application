/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Role;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.FirstLevelAccessDAO;
import dao.RoleDAO;
import dto.CurrentEmployee;
import dto.RoleInfo;
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
import security.Commen.EmployeeAuthorization;
import security.Log.CreateLog;

/**
 *
 * @author Dasun
 */
@MultipartConfig
public class updateRole extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            CurrentEmployee currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
            EmployeeAuthorization eA = new EmployeeAuthorization();

           if (currentEmployee != null && eA.CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f1")) {

                String roleInfoJsn = request.getParameter("roleInfo");
                RoleInfo roleInfo = new RoleInfo();

                try {
                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();
                    roleInfo = gson.fromJson(roleInfoJsn, RoleInfo.class);

                    if (roleInfo.getRoleId() != null && !roleInfo.getRoleId().equals("")
                            && roleInfo.getRoleName() != null && !roleInfo.getRoleName().equals("")
                            && roleInfo.getRoleDescription() != null && !roleInfo.getRoleDescription().equals("")) {

                        Role role = new RoleDAO().searchById(roleInfo.getRoleId());
                        role.setRoleName(roleInfo.getRoleName());
                        role.setRoleDescription(roleInfo.getRoleDescription());

                        FirstLevelAccess fla = new FirstLevelAccessDAO().searchByRole(role);
                        fla.setF1(roleInfo.isF1());
                        fla.setF2(roleInfo.isF2());
                        fla.setF3(roleInfo.isF3());
                        fla.setF4(roleInfo.isF4());
                        fla.setF5(roleInfo.isF5());
                        fla.setF6(roleInfo.isF6());

                        new RoleDAO().update(role, fla);

                        out.print("Role update successful !");
                        
                        // Log Employee Activity
                        new CreateLog("info", "Updating a role.", currentEmployee.getEmployeeId(), updateRole.class.getName()).employeeActivey();
                        
                    } else {
                        out.print("Invalid Data !");
                    }

                } catch (Exception e) {
                    out.print("Role update Failed !");
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
