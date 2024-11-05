/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DashBoardCustomerOrders;

import connection.ConnectionBuilder;
import dao.CustomerOrderDAO;
import dao.OrderStatusDAO;
import dto.CurrentEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CustomerOrder;
import model.OrderStatus;
import org.hibernate.Session;
import security.Commen.EmployeeAuthorization;
import security.Log.CreateLog;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "changeCustomerOrderStatus", urlPatterns = {"/changeCustomerOrderStatus"})
public class changeCustomerOrderStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            CurrentEmployee currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
            EmployeeAuthorization eA = new EmployeeAuthorization();

            if (currentEmployee != null && eA.CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f3")) {

                String orderId = request.getParameter("orderId");
                String selectedStatusId = request.getParameter("selectedStatusId");

                Session hsession = ConnectionBuilder.hibSession();

                try {
                    CustomerOrder customerOrder = new CustomerOrderDAO().getCustomerOrderById(Integer.parseInt(orderId), hsession);
                    OrderStatus orderStatus = new OrderStatusDAO().searchById(Integer.parseInt(selectedStatusId), hsession);
                    if (customerOrder != null || orderStatus != null) {
                        new CustomerOrderDAO().updateOrderStatus(customerOrder, orderStatus, hsession);
                        out.print("Update successful !");

                        // Log Employee Activity
                        new CreateLog("info", "Customer order status changed.", currentEmployee.getEmployeeId(), changeCustomerOrderStatus.class.getName()).employeeActivey();

                    } else {
                        out.print("Update failed !");
                    }
                } catch (Exception e) {
                    out.print("Update failed !");
                    e.printStackTrace();
                }

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
