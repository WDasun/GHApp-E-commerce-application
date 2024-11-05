/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Review;

import connection.ConnectionBuilder;
import dao.CustomerDAO;
import dao.CustomerOrderDAO;
import dao.ItemDAO;
import dao.ReviewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.CustomerOrder;
import model.OrderLine;
import model.ProductItem;
import model.Review;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SaveReview", urlPatterns = {"/SaveReview"})
public class SaveReview extends HttpServlet {

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
            String productId = request.getParameter("productId");
            String customerId = request.getParameter("customerId");
            String title = request.getParameter("title");
            String comment = request.getParameter("comment");
            String rateValue = request.getParameter("rateValue");
            
            Session session = ConnectionBuilder.hibSession();
            try {
                ProductItem productItem = new ItemDAO().searchById(productId, session);
                Customer customer = new CustomerDAO().searchById(Integer.parseInt(customerId));
                CustomerOrder customerOrder = null;
                Set<OrderLine> orderLineSet = productItem.getOrderLines();
                for (OrderLine orderLine : orderLineSet) {
                    if (customer.getCustomerId() == orderLine.getCustomerOrder().getCustomer().getCustomerId()) {
                        customerOrder = orderLine.getCustomerOrder();
                        break;
                    }
                }
                Review customerReviewForTheProductItem = new ReviewDAO().getReviewByCustomerAndProductItem(customer, productItem, session);
                if (customerOrder != null && customerReviewForTheProductItem == null) {

                    Review rv = new Review();
                    rv.setCustomer(customer);
                    rv.setCustomerOrder(customerOrder);
                    rv.setProductItem(productItem);
                    rv.setCommentTitle(title);
                    rv.setComment(comment);
                    rv.setRate(Integer.parseInt(rateValue));
                    rv.setDate(new Date());

                    new ReviewDAO().save(rv);

                } else if (customerOrder == null) {
                    System.out.println("You must buy the product first !");
                } else if (customerReviewForTheProductItem != null) {
                    System.out.println("You already commented for this product !");
                } else {
                    System.out.println("Somthing else is wrong !");
                }
                new SimpleDateFormat();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                session.close();
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
