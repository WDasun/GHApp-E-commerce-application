/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Recovery;

import common.CreateRecoveryCode;
import common.SendRecoveryCodeToEmail;
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
 * @author Dasun vimukthi
 */
@WebServlet(name = "GenerateRecoveryCode", urlPatterns = {"/GenerateRecoveryCode"})
public class GenerateRecoveryCode extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String customerEmail = request.getParameter("customerEmail");
            Session session = ConnectionBuilder.hibSession();
            try {
                Customer customer = new CustomerDAO().searchByEmail(customerEmail);
                String recoveryCode = new CreateRecoveryCode().create(20);

                Recovery exsistRecovery = new RecoveryDAO().searchByCustomer(customer, session);
                if(exsistRecovery != null){
                   new RecoveryDAO().delete(exsistRecovery, session);
                }
                Recovery recovery = new Recovery();
                recovery.setCustomer(customer);
                recovery.setRecoveryCode(recoveryCode.trim());

                new RecoveryDAO().save(recovery, session);

                new SendRecoveryCodeToEmail(customer.getEmail(), recovery.getRecoveryCode(), "http://localhost:8080/GHApp/Recovery.jsp").Post();
            } catch (Exception e) {
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
