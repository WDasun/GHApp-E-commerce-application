/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Recovery;

import connection.ConnectionBuilder;
import dao.CustomerDAO;
import dao.RecoveryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Recovery;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/ResetPassword"})
public class ResetPassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String customerEmail = request.getParameter("customerEmail").trim();
            String recoveryCode = request.getParameter("recoveryCode").trim();
            String newPassword = request.getParameter("newPassword").trim();

            Session session = ConnectionBuilder.hibSession();
            try {
                Customer customer = new CustomerDAO().searchByEmail(customerEmail, session);
                Recovery recover = new RecoveryDAO().searchByRecoveryCode(recoveryCode, session);
                System.out.println("Is recover Customer : "+ recover == null);
                System.out.println("recover Id : "+ recover.getId());
                if (recover.getCustomer().getCustomerId() == customer.getCustomerId()) {
                    new CustomerDAO().updatePassword(newPassword, customer.getCustomerId());
                    new RecoveryDAO().delete(recover, session);
                    out.print("Password reset successful !");
                } else {
                    out.print("Password reset failed !");
                }
            } catch (Exception e) {
                out.print("Password reset failed !");
                e.printStackTrace();
            } finally {
//                session.close();
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
