/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Customer;

import connection.ConnectionBuilder;
import dao.AddressDAO;
import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Address;
import model.Customer;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "LoadCustomerAddressPool", urlPatterns = {"/LoadCustomerAddressPool"})
public class LoadCustomerAddressPool extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            System.out.println("Called 1");

            String content = "";
            String rowStart = "<div class=\"row m-0\">";
            String rowEnd = "</div>";
            System.out.println("Called 1.1");
            Session hSession = ConnectionBuilder.hibSession();
            System.out.println("Called 1.2");
            HttpSession session = request.getSession();
            System.out.println("Called 1.3");
            Customer sCustomer = (Customer) session.getAttribute("currentCustomerAccount");
            System.out.println("Called 1.4");
            Customer customer = new CustomerDAO().searchById(sCustomer.getCustomerId(), hSession);

            System.out.println("Called 2");

            if (customer != null) {
                List<Address> addressList = new AddressDAO().getSpecificCustomerAddressList(customer, hSession);
                System.out.println("Size of c pool : " + addressList.size());
                System.out.println("Called 3");
                int listSize = addressList.size();
                int loopCountforRow = 0;
                int addressReadCount = 1;
                for (Address address : addressList) {
                    if (loopCountforRow == 0) {
                        content += rowStart;
                    }

                    content += "        <div class=\"col p-0 px-2 py-2\">\n"
                            + "            <div class=\" py-2 px-3 border\">\n"
                            + "                <div class=\"d-flex align-items-center justify-content-between\">\n"
                            + "                    <h5 class=\"fw-bold m-0 me-5\">Address " + addressReadCount + "</h5>\n"
                            + "                    <button class=\"btn btn-light border\" onclick=\"editAddress_customerProfile('" + address.getId() + "')\">Edit.</button>\n"
                            + "                    <button class=\"btn btn-light border\" onclick=\"deleteAddress_customerProfile('" + address.getId() + "')\">Delete.</button>\n"
                            + "                </div>\n"
                            + "                <p class=\"m-0\"><span class=\"fw-bold\">Adl 1 : </span>" + address.getAddressLine1() + "</p>\n"
                            + "                <p class=\"m-0\"><span class=\"fw-bold\">Adl 2 : </span>" + address.getAddressLine2() + "</p>\n"
                            + "                <p class=\"m-0\"><span class=\"fw-bold\">City : </span>" + address.getCity() + "</p>\n"
                            + "                <p class=\"m-0\"><span class=\"fw-bold\">State/District : </span>" + address.getStateOrDistrict() + "</p>\n"
                            + "                <p class=\"m-0\"><span class=\"fw-bold\">Country : </span>" + address.getCountry().getCountryName() + "</p>\n"
                            + "                <p class=\"m-0\"><span class=\"fw-bold\">Last update : </span>" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(address.getUpdatedAt()) + "</p>\n"
                            + "            </div>\n"
                            + "        </div>";
                    addressReadCount++;

                    loopCountforRow++;
                    if (loopCountforRow == 3) {
                        content += rowEnd;
                        loopCountforRow = 0;
                    }
                }
                if (listSize % 3 > 0) {
                    for (int i = 0; i < 3 - (listSize % 3); i++) {
                        content += "<div class=\"col p-0 px-2 py-2\">\n"
                                + "            <div class=\" py-2 px-3\">\n"
                                + "                \n"
                                + "            </div>\n"
                                + "        </div>";
                    }
                    content += rowEnd;
                }
            }

            String addressPoolHtml = "<div class=\"container-fluid div-addressPool p-0\">\n"
                    + "    <div class=\"row d-flex align-items-center justify-content-end m-0 pe-2\">\n"
                    + "        <button onclick=\"newAddress__customerProfile()\" class=\"btn btn-secondary w-25\" id=\"addNewAddress_customerProfile\">New address</button>\n"
                    + "    </div>\n"
                    + content
                    + "</div>";
            out.print(addressPoolHtml);
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
