/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DashBoardCustomerList;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "LoadCustomerList", urlPatterns = {"/LoadCustomerList"})
public class LoadCustomerList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            List<Customer> customerList = new CustomerDAO().getCustomerList();
            String html = "";

            for (Customer c : customerList) {
                if(!c.isStatus()){
                    html += "   <tr>\n"
                        + "     <td>"+c.getCustomerId()+"</td>\n"
                        + "     <td>"+c.getFname()+" "+c.getLname()+"</td>\n"
                        + "     <td>"+c.getEmail()+"</td>\n"
                        + "     <td>Inactive</td>\n"
                        + "     <td class=\"\">\n"
                        + "         <button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"activeCustomer_customerManage("+c.getCustomerId()+")\">Active</button>\n"
                        + "         <button class=\"btn btn-danger btn-categoryList-tableTow\" onclick=\"viewCustomer_customerManage("+c.getCustomerId()+")\">View</button>\n"
                        + "     </td>\n"
                        + "</tr>";
                }else{
                    html += "   <tr>\n"
                        + "     <td>"+c.getCustomerId()+"</td>\n"
                        + "     <td>"+c.getFname()+" "+c.getLname()+"</td>\n"
                        + "     <td>"+c.getEmail()+"</td>\n"
                        + "     <td>Active</td>\n"
                        + "     <td class=\"\">\n"
                        + "         <button class=\"btn btn-warning btn-categoryList-tableTow\" onclick=\"inactiveCustomer_customerManage("+c.getCustomerId()+")\">Inactive</button>\n"
                        + "         <button class=\"btn btn-danger btn-categoryList-tableTow\" onclick=\"viewCustomer_customerManage("+c.getCustomerId()+")\">View</button>\n"
                        + "     </td>\n"
                        + "</tr>";
                }
            }
            
            out.print(html);

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
