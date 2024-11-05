/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.CustomerProfile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.ConnectionBuilder;
import dao.CustomerDAO;
import dao.InvoiceDAO;
import dto.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.CustomerOrder;
import model.Invoice;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "GetOrderList", urlPatterns = {"/GetOrderList"})
public class GetOrderList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            
            Customer currentCustomer = (Customer) session.getAttribute("currentCustomerAccount");
            Session hSession = ConnectionBuilder.hibSession();
            try {
                Customer customer  = new CustomerDAO().searchById(currentCustomer.getCustomerId(), hSession);
                Set<CustomerOrder> orderSet = customer.getCustomerOrders();
                ArrayList<OrderDetail> orderList = new ArrayList<>();
                for (CustomerOrder co : orderSet) {
                    Invoice invoice = new InvoiceDAO().getInvoiceByCustomerOrder(co);
                    String orderedDate = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").format(co.getOrderDate());
                    String address = co.getAddress().getAddressLine1()+" "+co.getAddress().getAddressLine2();
                    OrderDetail od = new OrderDetail(
                            invoice.getId(),
                            String.valueOf(co.getId()),
                            String.valueOf(co.getOrderTotal()),
                            orderedDate,
                            co.getOrderStatus().getValue(),
                            co.getShippingType().getName(),
                            address);
                    orderList.add(od);
                }
                
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                
                out.print(gson.toJson(orderList));
                
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
