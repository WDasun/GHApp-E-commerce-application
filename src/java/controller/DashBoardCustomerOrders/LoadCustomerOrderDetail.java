/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DashBoardCustomerOrders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.ConnectionBuilder;
import dao.CustomerOrderDAO;
import dto.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerOrder;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoadCustomerOrderDetail", urlPatterns = {"/LoadCustomerOrderDetail"})
public class LoadCustomerOrderDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String customerOrderId = request.getParameter("orderId");
            Session session = ConnectionBuilder.hibSession();
            try {
                CustomerOrder customerOrder = new CustomerOrderDAO().getCustomerOrderById(Integer.parseInt(customerOrderId), session);
                OrderDetail od = new OrderDetail();
                od.setOrderId(String.valueOf(customerOrder.getId()));
                od.setCustomerId(String.valueOf(customerOrder.getCustomer().getCustomerId()));
                od.setFname(customerOrder.getCustomer().getFname());
                od.setLname(customerOrder.getCustomer().getLname());
                od.setOrderedDate(new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").format(customerOrder.getOrderDate()));
                od.setOrderTotal(String.valueOf(customerOrder.getOrderTotal()));
                od.setShippingType(customerOrder.getShippingType().getName());
                od.setShippingAddress(customerOrder.getAddress().getAddressLine1()+" "+customerOrder.getAddress().getAddressLine2());
                
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                
                out.print(gson.toJson(od));
                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
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
