/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.WishList;

import dao.CustomerDAO;
import dao.ItemDAO;
import dao.WishListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.ProductItem;
import model.WishList;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "SaveWishList", urlPatterns = {"/SaveWishList"})
public class SaveWishList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String customerId = request.getParameter("customerId");
            String productId = request.getParameter("productId");

            if (customerId != null && productId != null && !customerId.equals("") && !productId.equals("")) {
                try {
                    Customer customer = new CustomerDAO().searchById(Integer.parseInt(customerId));
                    ProductItem productItem = new ItemDAO().searchById(productId);
                    
                    if(customer != null && productItem != null){
                        WishList wishList = new WishList();
                        wishList.setCustomer(customer);
                        wishList.setProductItem(productItem);
                        new WishListDAO().save(wishList);
                        
                        out.print(wishList.getId());
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("Data not valid !");
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
