/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.SessionCart;

import connection.ConnectionBuilder;
import dao.CartDAO;
import dao.CartItemDAO;
import dao.ItemDAO;
import dto.CalculateCartItemSummery;
import dto.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.ProductItem;
import model.ShoppingCart;
import model.ShoppingCartItem;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ChangeQtyAndCalculateSummery", urlPatterns = {"/ChangeQtyAndCalculateSummery"})
public class ChangeQtyAndCalculateSummery extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String operation = request.getParameter("operation");
            int qtyChange = Integer.parseInt(request.getParameter("qtyChange"));
            String itemId = request.getParameter("itemId");
            Session hsession = ConnectionBuilder.hibSession();
            boolean loggeUser = false;
            if (operation != null) {
                if (qtyChange != 0) {
                    if (itemId != null) {
                        if ((operation.equals("subtract") || operation.equals("add")) && !itemId.equals("")) {

                            try {
                                new ItemDAO().searchById(itemId);

                                int stockQty = 0;
                                int cartQty = 0;
                                int qtyToChange = 0;

                                HttpSession session = request.getSession();
                                ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute("sessionCartItemList");
                                int readIndex = 0;
                                boolean cartItemFound = false;
                                for (CartItem cartItem : cartItemList) {
                                    ProductItem pi = new ItemDAO().searchById(cartItem.getProductItem().getId(), hsession);
                                    if (pi.getId().equals(itemId)) {
                                        cartItemFound = true;
                                        stockQty = pi.getQty();
                                        cartQty = cartItem.getQty();
                                        qtyToChange = qtyChange;

                                        if (operation.equals("add")) {
                                            if ((cartQty + qtyToChange) <= stockQty) {

                                                // Only Session cart process
                                                cartQty += qtyToChange;

                                                // DB cart process
                                                Customer currentCustomer = (Customer) session.getAttribute("currentCustomerAccount");
                                                if (currentCustomer != null) {
                                                    loggeUser = true;
                                                    ProductItem productItem = new ItemDAO().searchById(itemId);
                                                    ShoppingCart shoppingCart = new CartDAO().searchByCustomer(currentCustomer);
                                                    ShoppingCartItem sci = new CartItemDAO().searchByProductItemAndCustomer(productItem, shoppingCart);
                                                    sci.setQty(sci.getQty() + qtyToChange);
                                                    new CartItemDAO().updateQty(sci.getId(), sci.getQty());
                                                }

                                            }
                                        } else if (operation.equals("subtract")) {
                                            if ((cartQty - qtyToChange) > 0) {
                                                // Only Session cart process
                                                cartQty -= qtyToChange;

                                                // DB cart process
                                                Customer currentCustomer = (Customer) session.getAttribute("currentCustomerAccount");
                                                if (currentCustomer != null) {
                                                    loggeUser = true;
                                                    ProductItem productItem = new ItemDAO().searchById(itemId);
                                                    ShoppingCart shoppingCart = new CartDAO().searchByCustomer(currentCustomer);
                                                    ShoppingCartItem sci = new CartItemDAO().searchByProductItemAndCustomer(productItem, shoppingCart);
                                                    sci.setQty(sci.getQty() - qtyToChange);
                                                    new CartItemDAO().updateQty(sci.getId(), sci.getQty());
                                                }

                                            }
                                        }

                                        cartItemList.get(readIndex).setQty(cartQty);
//                                        cartItem.setTotalItemPrice(cartItem.getQty() * cartItem.getProductItem().getPrice());
                                        session.setAttribute("sessionCartItemList", cartItemList);

                                        break;
                                    }
                                    readIndex++;
                                }

                                if (cartItemFound) {
                                    String jsonObj = "{\"summeryHTML\" : \"" + new CalculateCartItemSummery().CalculateAndGetSummeryHTML(cartItemList, 0, loggeUser) + "\", \"newQTY\" : \"" + cartQty + "\"}";
                                    out.print(jsonObj);
                                } else {
                                    System.out.println("Item not found in the cart !");
                                }

                            } catch (NullPointerException e) {
                                System.out.println("Item not found !");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            System.out.println("Date not valid !");
                        }
                    } else {
                        System.out.println("Item Id cannot be null !");
                    }
                } else {
                    System.out.println("Qty change cannot be Zero !");
                }
            } else {
                System.out.println("Operation cannot be null !");
            }
//            hsession.close();
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
