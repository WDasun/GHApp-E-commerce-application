/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.SessionCart;

import common.CreateCartItem;
import dao.CartDAO;
import dao.CartItemDAO;
import dao.ItemDAO;
import dto.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.ProductItem;
import model.Promotion;
import model.ShoppingCartItem;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "AddToSessionCart", urlPatterns = {"/AddToSessionCart"})
public class AddToSessionCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String itemId = request.getParameter("itemId");

            if (!itemId.equals("") && itemId != null) {
                ProductItem newItem = null;
                try {
                    newItem = new ItemDAO().searchById(itemId);

                    HttpSession session = request.getSession();
                    //If user logged or not
                    if (session.getAttribute("currentCustomerAccount") == null && session.getAttribute("user") == null) {
                        try {       
                            //If there is a SessionCartItemList already or not 
                            if (session.getAttribute("sessionCartItemList") == null) {  
                                //Creating a Cart item and add it to the list
                                ArrayList<CartItem> cartItemList = new ArrayList<>();
                                cartItemList.add(new CreateCartItem(newItem, 0).createCartItem());
                                //Add list to the session Obj
                                session.setAttribute("sessionCartItemList", cartItemList);
                            } else {
                                ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute("sessionCartItemList");
                                String newItemId = newItem.getId();
                                //Finding is item exist in the list or not
                                boolean itemExist = false;
                                for (CartItem cartItem : cartItemList) {
                                    if (cartItem.getProductItem().getId().equals(newItemId)) {
                                        itemExist = true;
                                        break;
                                    } else {
                                        itemExist = false;
                                    }
                                }
                                if (!itemExist) {  
                                    //Creating a Cart item and add it to the list
                                    cartItemList.add(new CreateCartItem(newItem, 0).createCartItem());
                                    //Add list to the session Obj
                                    session.setAttribute("sessionCartItemList", cartItemList);
                                    out.print("Item added to the session Cart !");
                                } else {
                                    out.print("Item allready exist in the session Cart !");
                                }
                            }
                        } catch (NullPointerException e) {
                            out.println(e.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (session.getAttribute("currentCustomerAccount") != null && session.getAttribute("user") == null) {
                        // DB cart process, When there is a Customer account in session
                        try {
                            ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute("sessionCartItemList");
                            String newItemId = newItem.getId();
                            boolean itemExist = false;
                            for (CartItem cartItem : cartItemList) {
                                if (cartItem.getProductItem().getId().equals(newItemId)) {
                                    itemExist = true;
                                    break;
                                } else {
                                    itemExist = false;
                                }
                            }
                            if (!itemExist) {

                                ShoppingCartItem sci = new ShoppingCartItem();
                                sci.setShoppingCart(new CartDAO().searchByCustomer((Customer) session.getAttribute("currentCustomerAccount")));
                                sci.setProductItem(newItem);
                                sci.setQty(0);

                                new CartItemDAO().save(sci);

                                //Creating a Cart item and add it to the list
                                cartItemList.add(new CreateCartItem(sci).createCartItem());
                                //Add list to the session Obj
                                session.setAttribute("sessionCartItemList", cartItemList);
                                out.print("Item added to the Your Cart !");
                            } else {
                                out.print("Item allready exist in the Your Cart !");
                            }
                        } catch (NullPointerException e) {
                            out.println(e.getMessage());
                        } catch (Exception e) {
                            out.print("Error accrued !. Please contact customer support.");
                            e.printStackTrace();
                        }
                    }
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
