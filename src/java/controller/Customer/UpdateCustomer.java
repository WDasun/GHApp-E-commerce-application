/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Customer;

import dao.CustomerDAO;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UpdateCustomer", urlPatterns = {"/UpdateCustomer"})
@MultipartConfig
public class UpdateCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");
            String dob = request.getParameter("dob");

            if (!fname.equals("") && fname != null
                    && !lname.equals("") && lname != null
                    && !email.equals("") && email != null
                    && !contact.equals("") && contact != null
                    && !dob.equals("") && dob != null) {

                HttpSession hs = request.getSession();
                Customer customer = (Customer) hs.getAttribute("currentCustomerAccount");
                if(customer != null){
                    customer.setFname(fname);
                    customer.setLname(lname);
                    customer.setEmail(email);
                    customer.setCntNumber(contact);
                    Date currentDate = new Date();
                    customer.setUpdatedAt(currentDate);
                    try {
                        customer.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dob));
                    } catch (Exception e) {
                        out.print(new HTMLContents().responseContent("Invalid Data !"));
                        e.printStackTrace();
                    }
                    try {
                        new CustomerDAO().update(customer);
                        out.print(new HTMLContents().responseContent("Profile updated !"));
                    } catch (Exception e) {
                         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred !");
                         e.printStackTrace();
                    }
                }
                
            }else{
                out.print(new HTMLContents().responseContent("Invalid Data !"));
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
