/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.systemUser;

import dao.SystemUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SystemUser;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "changeSystemUserStatus", urlPatterns = {"/changeSystemUserStatus"})
public class changeSystemUserStatus extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            try {
                if (id != null && !id.equals("")
                        && status != null && !status.equals("")
                        && !id.equals("admin")
                        && (status.equals("true") || status.equals("false"))) {

                    SystemUser su = new SystemUserDAO().searchById(id);
                    if(status.equals("true")){
                        su.setStatus(true);
                    }else{
                        su.setStatus(false);
                    }
                    
                    new SystemUserDAO().updateStatus(su);
                    out.print("Status change successful !");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.print("Invalid Data !");
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
