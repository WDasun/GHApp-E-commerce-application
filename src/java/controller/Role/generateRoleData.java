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
import dto.RoleInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FirstLevelAccess;
import model.Role;


/**
 *
 * @author Dasun
 */
public class generateRoleData extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String id = request.getParameter("id").trim();
            
            Role role = new RoleDAO().searchById(id);
            
            RoleInfo ri = new RoleInfo();
            ri.setRoleId(role.getRoleId());
            ri.setRoleName(role.getRoleName());
            ri.setRoleDescription(role.getRoleDescription());
            
            FirstLevelAccess fla = new FirstLevelAccessDAO().searchByRole(role);
            ri.setF1(fla.isF1());
            ri.setF2(fla.isF2());
            ri.setF3(fla.isF3());
            ri.setF4(fla.isF4());
            ri.setF5(fla.isF5());
            ri.setF6(fla.isF6());
            
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            
            out.print(gson.toJson(ri));
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
