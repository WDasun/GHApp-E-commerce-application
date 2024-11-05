/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Customer;

import connection.ConnectionBuilder;
import dao.AddressDAO;
import dao.CountryDAO;
import dao.CustomerDAO;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Address;
import model.Country;
import model.Customer;
import org.hibernate.Session;

/**
 *
 * @author Dasun wimukthi
 */
@WebServlet(name = "SaveCustomerAddress", urlPatterns = {"/SaveCustomerAddress"})
@MultipartConfig
public class SaveCustomerAddress extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String addressLinOne = request.getParameter("addressLineOne");
            String addressLinTwo = request.getParameter("addressLineTwo");
            String city = request.getParameter("city");
            String stateOrDistrict = request.getParameter("stateOrDistrict");
            String postalCode = request.getParameter("postalCode");
            String countryId = request.getParameter("country");
            Date createdAt = new Date();
            Date updatedAt = new Date();
            Session hSession = ConnectionBuilder.hibSession();
            Address customerAddress = null;
            if (!addressLinOne.equals("") && addressLinOne != null
                    && !city.equals("") && city != null
                    && !stateOrDistrict.equals("") && stateOrDistrict != null
                    && !postalCode.equals("") && postalCode != null
                    && !countryId.equals("") && countryId != null) {

                customerAddress = new Address();
                Customer sCustomer = (Customer) request.getSession().getAttribute("currentCustomerAccount");
                customerAddress.setCustomer(new CustomerDAO().searchById(sCustomer.getCustomerId(), hSession));
                customerAddress.setAddressLine1(addressLinOne);
                customerAddress.setAddressLine2(addressLinTwo);
                customerAddress.setCity(city);
                customerAddress.setStateOrDistrict(stateOrDistrict);
                customerAddress.setPostalCode(postalCode);
                customerAddress.setCreatedAt(createdAt);
                customerAddress.setUpdatedAt(updatedAt);

                Country country = new CountryDAO().searchById(countryId, hSession);
                if (country != null) {
                    customerAddress.setCountry(country);
                    try {
                        new AddressDAO().save(customerAddress, hSession);
                        
                        out.print(new HTMLContents().responseContent("New Address saved !"));
                    } catch (Exception e) {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred !");
                    }
                } else {
                    out.print(new HTMLContents().responseContent("Invalid Data !"));
                }
            } else {
                out.print(new HTMLContents().responseContent("Invalid Data !"));
            }

//            hSession.close();
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
