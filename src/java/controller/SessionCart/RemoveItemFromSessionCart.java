/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.SessionCart;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.ItemDAO;
import dto.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.ProductItem;
import model.ShoppingCart;
import model.ShoppingCartItem;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RemoveItemFromSessionCart", urlPatterns = {"/RemoveItemFromSessionCart"})
public class RemoveItemFromSessionCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            System.out.println("RemoveItemFromSessionCart Called !");
            String itemId = request.getParameter("itemId");
            System.out.println("RemoveItemFromSessionCart recived item id : "+itemId);
            try {
                if (!itemId.equals("")) {
                    try {
                        
                        ProductItem productItem = new ItemDAO().searchById(itemId);
                        System.out.println("RemoveItemFromSessionCart product ID : "+productItem.getId());
                        Customer currentCustomer = (Customer) request.getSession().getAttribute("currentCustomerAccount");
                        if(currentCustomer != null){
                            ShoppingCart shoppingCart = new CartDAO().searchByCustomer(currentCustomer);
                            //Test
                            if(shoppingCart != null){
                                System.out.println("Shopping cart foud id : "+shoppingCart.getId());
                            }else{
                                System.out.println("Shopping cart not found !");
                            }
                            //Test
                            ShoppingCartItem sci = new CartItemDAO().searchByProductItemAndCustomer(productItem,shoppingCart);
                            //Test
                            if(sci != null){
                                System.out.println("Shopping cart item found id : "+sci.getId());
                            }else{
                                System.out.println("Shopping cart item not found !");
                            }
                            //Test
                            new CartItemDAO().delete(sci);
                        }
                        
                        ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) request.getSession().getAttribute("sessionCartItemList");
                        //Test
                        System.out.println("Product Item List in cart item list");
                        for (CartItem cartItem : cartItemList) {
                            System.out.println("Product Item id : "+cartItem.getProductItem().getId());
                        }
                        //Test
                        if (cartItemList != null) {
                            boolean itemFound = false;
                            String removedItemId = "";
                            for (int i = 0; i < cartItemList.size(); i++) {
                                if (cartItemList.get(i).getProductItem().getId().equals(productItem.getId())) {
                                    removedItemId = cartItemList.get(i).getProductItem().getId();
                                    itemFound = true;
                                    cartItemList.remove(i);
                                    break;
                                }
                            }
                            
                            System.out.println("Item found in Session cart : "+itemFound);
                            System.out.println("removed Item id : "+removedItemId);

                            if (itemFound) {
                                out.print("Item " + removedItemId + " is removed from the session cart !");
                            } else {
                                out.print("Item " + itemId + " not exist in the session cart !");
                            }
                        } else {
                            out.print("A session cart not exist !");
                        }

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        out.println("Item not found !");
                    } catch (Exception e) {
                        e.printStackTrace();
                        out.println("Somthing wrong !");
                    }
                } else {
                    out.println("Data not valid !");
                }
            } catch (NullPointerException e) {
                out.println("Data not valid (NULL) !");
            } catch (Exception e){
                out.print(e);
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
