/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Address;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.ConnectionBuilder;
import dao.AddressDAO;
import dao.CustomerDAO;
import dto.AddressDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Address;
import model.Customer;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddressListOfCustomer", urlPatterns = {"/AddressListOfCustomer"})
public class AddressListOfCustomer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String customerId = request.getParameter("id").trim();
            Session hSession = ConnectionBuilder.hibSession();
            if (customerId != null && !customerId.equals("")) {
                int cId = Integer.parseInt(customerId);
                try {
                    Customer customer = new CustomerDAO().searchById(cId);
                    List<Address> addressList = new AddressDAO().getSpecificCustomerAddressList(customer,hSession);
                    AddressDTO[] addressArray = new AddressDTO[addressList.size()];

                    for (int i = 0; i < addressList.size(); i++) {
                        AddressDTO addressDTO = new AddressDTO();
                        Address address = addressList.get(i);
                        addressDTO.setId(address.getId());
                        addressDTO.setCountryId(address.getCountry().getId());
                        addressDTO.setCustomerId(address.getCustomer().getCustomerId());
                        addressDTO.setAddressLine1(address.getAddressLine1());
                        addressDTO.setAddressLine2(address.getAddressLine2());
                        addressDTO.setCity(address.getCity());
                        addressDTO.setStateOrDistrict(address.getStateOrDistrict());
                        addressDTO.setPostalCode(address.getPostalCode());
                        addressDTO.setCreatedAt(address.getCreatedAt());
                        addressDTO.setUpdatedAt(address.getUpdatedAt());
                        addressArray[i] = addressDTO;
                    }

                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();

                    Gson gson = builder.create();

                    String jsonString = gson.toJson(addressArray);

                    out.print(jsonString);

                } catch (Exception e) {
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
