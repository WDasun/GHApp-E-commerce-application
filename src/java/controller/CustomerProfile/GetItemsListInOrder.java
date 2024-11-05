/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.CustomerProfile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.ConnectionBuilder;
import dao.CustomerOrderDAO;
import dao.InvoiceDAO;
import dto.OrderLineInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerOrder;
import model.Invoice;
import model.OrderLine;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "GetItemsListInOrder", urlPatterns = {"/GetItemsListInOrder"})
public class GetItemsListInOrder extends HttpServlet {

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
            
            String orderId = request.getParameter("orderId");

            Session hSession = ConnectionBuilder.hibSession();
            try {
                CustomerOrder order = new CustomerOrderDAO().getCustomerOrderById(Integer.parseInt(orderId), hSession);
                Set<OrderLine> orderLineSet = order.getOrderLines();
                ArrayList<OrderLineInfo> orderLineInfoList = new ArrayList<>();
                Invoice invoice = new InvoiceDAO().getInvoiceByCustomerOrder(order);
                for (OrderLine orderLine : orderLineSet) {         
                    OrderLineInfo oi = new OrderLineInfo(
                            invoice.getId(),
                            orderLine.getProductItem().getName(),
                            String.valueOf(orderLine.getQty()),
                            String.valueOf(orderLine.getPrice()));
                    orderLineInfoList.add(oi);
                }
                
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                
                out.print(gson.toJson(orderLineInfoList));

            } catch (Exception e) {
                e.printStackTrace();
            }finally{
//                hSession.close();
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
