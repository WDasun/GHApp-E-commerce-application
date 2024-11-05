package controller.SessionCart;

import dao.ItemDAO;
import dao.ShoppingCartItemDAO;
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
import model.ProductItem;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ChangeItemSelectStatus", urlPatterns = {"/ChangeItemSelectStatus"})
public class ChangeItemSelectStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String productItemId = request.getParameter("itemId").trim();
            String selectStatus = request.getParameter("status").trim();
            
            System.out.println("ChangeItemSelectStatus called !");
            System.out.println("Item id : "+productItemId);
            System.out.println("Item status : "+selectStatus);

            boolean status = Boolean.parseBoolean(selectStatus);

            HttpSession session = request.getSession();
            ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute("sessionCartItemList");
            ArrayList<CartItem> newCartItemList = new ArrayList<>();
            for (CartItem cartItem : cartItemList) {
                System.out.println("List item Id : "+cartItem.getProductItem().getId());
                if (cartItem.getProductItem().getId().equals(productItemId)) {
                    CartItem ci = cartItem;
                    ci.setSelected(status);
                    newCartItemList.add(ci);
                    
                    try {     
                        System.out.println("ShoppingCartItemId : "+cartItem.getShoppingCartItemId());
                        new ShoppingCartItemDAO().changeSelectedStatus(cartItem.getShoppingCartItemId(), status);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    newCartItemList.add(cartItem);
                }
            }

            session.setAttribute("sessionCartItemList", newCartItemList);

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
