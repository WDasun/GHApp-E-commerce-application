/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Checkout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.CreateCartItem;
import dao.CartDAO;
import dao.CustomerDAO;
import dao.CustomerOrderDAO;
import dao.ShoppingCartItemDAO;
import dto.CartItem;
import dto.CheckoutInfo;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.ShoppingCart;
import model.ShoppingCartItem;
import security.Log.CreateLog;
import security.Validation.CreditCardNumberValidation;
import security.Validation.CurrentDatecompare;
import security.Validation.CvvValidation;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "PayNow", urlPatterns = {"/PayNow"})
public class PayNow extends HttpServlet {

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
            String jsonCheckoutInfo = request.getParameter("jsonCheckoutInfo");

            String filePath = "";

            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("currentCustomerAccount");

            try {
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();

                CheckoutInfo ci = gson.fromJson(jsonCheckoutInfo, CheckoutInfo.class);

                // Validation start
                boolean validCardType = false;
                if (ci.getCardTypeId() == 1 || ci.getCardTypeId() == 2) {
                    validCardType = true;
                } else {
                    validCardType = false;
                }

                boolean validCardNumber = new CreditCardNumberValidation().isValidCreditCardCheckout(ci.getCardNumber());

                boolean validExpDate = false;
                try {
                    int numYear = Integer.parseInt(ci.getExpYear());
                    int numMonth = Integer.parseInt(ci.getExpMonth());
                    if (numYear <= 9999 && numYear > 999 && numMonth > 0 && numMonth < 13) {
                        validExpDate = new CurrentDatecompare().afterCurrentDate(numYear, numMonth, 0);
                    } else {
                        validExpDate = false;
                    }
                } catch (NumberFormatException e) {
                    validExpDate = false;
                }

                boolean validCvv = new CvvValidation().patternMatch(String.valueOf(ci.getCvv()));

                boolean validCardName = false;
                if (ci.getNameOnCard() != null && !ci.getNameOnCard().equals("")) {
                    validCardName = true;
                } else {
                    validCardName = false;
                }
                // Validation end
                if (validCardType && validCardNumber && validExpDate && validCvv && validCardName) {

                    List<CartItem> itemList = (List<CartItem>) session.getAttribute("sessionCartItemList");

                    filePath = new CustomerOrderDAO().SaveCustomerOrder(ci, itemList, customer);

                    // Update CartItem List
                    ShoppingCart customerShoppingCart = new CartDAO().searchByCustomer(customer);

                    ArrayList<CartItem> cartItemList = new ArrayList<>();
                    List<ShoppingCartItem> cartItemSet = new ShoppingCartItemDAO().searchByCart(customerShoppingCart);

                    for (ShoppingCartItem shoppingCartItem : cartItemSet) {
                        cartItemList.add(new CreateCartItem(shoppingCartItem).createCartItem());
                    }

                    session.setAttribute("sessionCartItemList", cartItemList);
                    String encodedFilePath = URLEncoder.encode(filePath, "UTF-8");
                    out.print(encodedFilePath);
                    
                     new CreateLog("info", "Payment successful.", String.valueOf(customer.getCustomerId()), PayNow.class.getName()).customerActivity();
                    
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request data");
                    new CreateLog("warning", "Payment failed due to invalid data.", String.valueOf(customer.getCustomerId()), PayNow.class.getName()).customerActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request data");
                new CreateLog("warning", "Payment failed due to invalid data.", String.valueOf(customer.getCustomerId()), PayNow.class.getName()).customerActivity();
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
