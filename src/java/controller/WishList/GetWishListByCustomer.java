/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.WishList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.ConnectionBuilder;
import dao.CustomerDAO;
import dao.WishListDAO;
import dto.EncodeImageToBase64;
import dto.WishListInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.WishList;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "GetWishListByCustomer", urlPatterns = {"/GetWishListByCustomer"})
public class GetWishListByCustomer extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String customerid = request.getParameter("customerId");
            Session session = ConnectionBuilder.hibSession();
            try {
                Customer customer = new CustomerDAO().searchById(Integer.parseInt(customerid));
                List<WishList> wishList = new WishListDAO().wishListbyCustomer(customer, session);
                ArrayList<WishListInfo> wlList = new ArrayList<>();
                for (WishList w : wishList) {
                    WishListInfo wishListInfo = new WishListInfo(String.valueOf(w.getId()), String.valueOf(w.getCustomer().getCustomerId()), w.getProductItem().getId());
                    wishListInfo.setItemImage(new EncodeImageToBase64().encodeImage(w.getProductItem().getItemImgOnePath()));
                    wishListInfo.setItemName(w.getProductItem().getName());
                    wlList.add(wishListInfo);
                }
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                String gString = gson.toJson(wlList);
                System.out.println(gString);
                out.print(gString);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            session.close();
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
