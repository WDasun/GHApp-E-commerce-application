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
import model.OrderLine;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "LoadOrderLineForOrder", urlPatterns = {"/LoadOrderLineForOrder"})
public class LoadOrderLineForOrder extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String orderId = request.getParameter("orderId");
            Session session = ConnectionBuilder.hibSession();
            try {
                CustomerOrder customerOrder = new CustomerOrderDAO().getCustomerOrderById(Integer.parseInt(orderId), session);
                if(customerOrder != null){
                    Set<OrderLine> orderLineSet = customerOrder.getOrderLines();
                    ArrayList<OrderLineInfo> oliList = new ArrayList<>();
                    for (OrderLine orderLine : orderLineSet) {
                        OrderLineInfo oli = new OrderLineInfo();
                        oli.setItemName(orderLine.getProductItem().getName());
                        oli.setProductItemId(orderLine.getProductItem().getId());
                        oli.setQty(String.valueOf(orderLine.getQty()));
                        oli.setPrice(String.valueOf(orderLine.getPrice()));
                        oliList.add(oli);
                    }
                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();
                    
                    out.print(gson.toJson(oliList));
                }
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
