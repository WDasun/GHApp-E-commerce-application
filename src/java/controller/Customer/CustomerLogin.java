/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Customer;

import common.CreateCartItem;
import dao.CartDAO;
import dao.CartItemDAO;
import dao.CustomerDAO;
import dao.ShoppingCartItemDAO;
import dto.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ShoppingCart;
import model.ShoppingCartItem;
import security.Commen.CustomerAuthentication;

/**
 *
 * @author Dasun wimukthi
 */
@WebServlet(name = "CustomerLogin", urlPatterns = {"/CustomerLogin"})
@MultipartConfig
public class CustomerLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            try {
                if (!email.equals("") && email != null
                        && !password.equals("") && password != null) {
                    CustomerAuthentication cA = new CustomerAuthentication(email, password);
                    if (cA.check()) {
                        HttpSession hs = request.getSession();
                        // "employee" is a Employee object
                        if (hs.getAttribute("CurrentEmployee") == null) {

                            if (cA.getCustomer().isStatus()) {
                                hs.setAttribute("currentCustomerAccount", cA.getCustomer());

                                ShoppingCart customerShoppingCart = new CartDAO().searchByCustomer(cA.getCustomer());

                                ArrayList<CartItem> cartItemList = new ArrayList<>();
                                List<ShoppingCartItem> cartItemSet = new ShoppingCartItemDAO().searchByCart(customerShoppingCart);

                                for (ShoppingCartItem shoppingCartItem : cartItemSet) {
                                    cartItemList.add(new CreateCartItem(shoppingCartItem).createCartItem());
                                }

                                hs.setAttribute("sessionCartItemList", cartItemList);
                                out.print("{\"loginStatus\": true, \"message\": \"Login succesful !\", \"redirectUrl\": \"index.jsp\"}");
                            }else{
                                out.print("{\"loginStatus\": false, \"message\": \"Customer account not enable !\"}");
                            }

                        } else {
                            out.print("{\"loginStatus\": false, \"message\": \"You must logout from Employee acount !\"}");
                        }
                    } else {
                        out.print("{\"loginStatus\": false, \"message\": \"Email or Password incorrect !\"}");
                    }
                } else {
                    out.print("{\"loginStatus\": false, \"message\": \"Data not valid !\"}");
                }
            } catch (Exception e) {
                e.printStackTrace();
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
