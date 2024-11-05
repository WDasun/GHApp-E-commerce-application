/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Address;

import connection.ConnectionBuilder;
import dao.AddressDAO;
import dao.CountryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Address;
import model.Country;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "UpdateCustomerAddress", urlPatterns = {"/UpdateCustomerAddress"})
public class UpdateCustomerAddress extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String addressId = request.getParameter("addressId");
            String countryId = request.getParameter("countryId");
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            String city = request.getParameter("city");
            String stateOrDistrict = request.getParameter("stateOrDistrict");
            String postalCode = request.getParameter("postalCode");
            
            Session session = ConnectionBuilder.hibSession();
            try {           
                Address address = new AddressDAO().searchById(Integer.parseInt(addressId));
                
                Country country = new CountryDAO().searchById(countryId);
                address.setCountry(country);
                address.setAddressLine1(addressLine1);
                address.setAddressLine2(addressLine2);
                address.setCity(city);
                address.setStateOrDistrict(stateOrDistrict);
                address.setPostalCode(postalCode);
                address.setUpdatedAt(new Date());
                
                new AddressDAO().update(address, session);
                out.print("Update Successful!");
            } catch (Exception e) {
                out.print("Update Failed!");
                e.printStackTrace();
            } finally{
                
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
