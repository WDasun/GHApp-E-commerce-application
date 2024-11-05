/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DashBoardCustomerOrders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.ConnectionBuilder;
import dao.CustomerDAO;
import dao.CustomerOrderDAO;
import dao.InvoiceDAO;
import dao.OrderStatusDAO;
import dto.OrderDetail;
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
import model.CustomerOrder;
import model.Invoice;
import model.OrderStatus;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoadCustomerOrders", urlPatterns = {"/LoadCustomerOrders"})
public class LoadCustomerOrders extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Session session = ConnectionBuilder.hibSession();
            String customerId = request.getParameter("customerId");
            String orderId = request.getParameter("orderId");
            String invoiceId = request.getParameter("invoice");
            String orderStatus = request.getParameter("orderStatus");

            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            List<CustomerOrder> orderList = null;
            ArrayList<OrderDetail> odList = new ArrayList<>();
            try {
                // Search only with Order ID
                if (orderId != null && customerId == null && invoiceId == null) {
                    orderList = new CustomerOrderDAO().searchCustomerOrder(null, Integer.parseInt(orderId), null, session);
                }

                //Search with customer
                if (orderId == null && customerId != null && invoiceId == null) {
                    Customer customer = new CustomerDAO().searchById(Integer.parseInt(customerId), session);
                    if (customer != null) {
                        OrderStatus status = new OrderStatusDAO().searchById(Integer.parseInt(orderStatus),session);
                        orderList = new CustomerOrderDAO().searchCustomerOrder(customer, 0, status, session);
                    }
                }

                //Search with Invoice
                if (orderId == null && customerId == null && invoiceId != null) {
                    Invoice invoice = new InvoiceDAO().searchByInvoiceId(invoiceId, session);
                    if (invoice != null) {
                        orderList = new CustomerOrderDAO().searchCustomerOrder(null, invoice.getCustomerOrder().getId(), null, session);
                    }
                }

                //All search
                if (orderId == null && customerId == null && invoiceId == null) {
                    OrderStatus status = new OrderStatusDAO().searchById(Integer.parseInt(orderStatus),session);
                    orderList = new CustomerOrderDAO().searchCustomerOrder(null, 0, status, session);
                }

                // Test S
                if (orderList != null) {
                    System.out.println("Order List Size : " + orderList.size());
                    for (CustomerOrder co : orderList) {
                        System.out.println("Order Id : " + co.getId() + ", Customer : " + co.getCustomer().getCustomerId() + ", Status : " + co.getOrderStatus().getId());
                    }
                } else {
                    System.out.println("Order List Size : empty");
                }
                // Test E   
                if (orderList != null) {
                    for (CustomerOrder co : orderList) {
                        //Test s
                        if(co.getId() == 57){
                            System.out.println("Customer 57 Order Status : "+co.getOrderStatus().getValue());
                        }
                        //Test e
                        OrderDetail od = new OrderDetail();
                        od.setInvoiceId(new InvoiceDAO().getInvoiceByCustomerOrder(co).getId());
                        od.setOrderId(String.valueOf(co.getId()));
                        od.setFname(co.getCustomer().getFname());
                        od.setLname(co.getCustomer().getLname());
                        od.setStatus(co.getOrderStatus().getValue());
                        odList.add(od);
                    }
                }

                out.print(gson.toJson(odList));

            } catch (Exception e) {
                e.printStackTrace();
                out.print(gson.toJson(odList));
            } finally {
//                System.out.println("Finnaly Called !");
//                session.evict(CustomerOrder.class);
//                session.evict(Invoice.class);
//                session.evict(OrderStatus.class);
//                session.clear();
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
