/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.CardDetails;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CardDetailsDAO;
import dao.CardTypeDAO;
import dao.CustomerDAO;
import dto.CardDetailCustomerProfile;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CardDetails;
import model.CreditOrDebitCardType;
import model.Customer;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "SaveCard", urlPatterns = {"/SaveCard"})
public class SaveCard extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String details = request.getParameter("CardDetailCustomerProfile");

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            CardDetailCustomerProfile cdcp = gson.fromJson(details, CardDetailCustomerProfile.class);

            String cardTypeId = cdcp.getCardType();
            String cardNumber = cdcp.getCardNumber();
            String cardName = cdcp.getNameOnCard();
            String expYear = cdcp.getExpYear();
            String expMonth = cdcp.getExpMonth();

            HttpSession session = request.getSession();
            Customer sCustomer = (Customer) session.getAttribute("currentCustomerAccount");
            try {
                Customer currentCustomer = new CustomerDAO().searchById(sCustomer.getCustomerId());

                CardDetails cardForNumber = new CardDetailsDAO().SearchByCardNumber(cardNumber);
                if (cardForNumber == null) {
                    try {
                        CreditOrDebitCardType cardType = new CardTypeDAO().searchById(Integer.parseInt(cardTypeId));
                        Customer customer = new CustomerDAO().searchById(currentCustomer.getCustomerId());

                        CardDetails cd = new CardDetails();
                        cd.setCreditOrDebitCardType(cardType);
                        cd.setCustomer(customer);
                        cd.setCardNumber(cardNumber);
                        cd.setCardName(cardName);
                        cd.setExpYear(Integer.parseInt(expYear));
                        cd.setExpMonth(Byte.parseByte(expMonth));
                        cd.setIsDefault(false);

                        new CardDetailsDAO().save(cd);

                        out.print("Card saved !");

                    } catch (Exception e) {
                        out.print("Failed !");
                        e.printStackTrace();
                    }
                } else {
                    out.print("Card number already exist !");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.print("Failed !");
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
