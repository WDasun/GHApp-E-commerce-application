/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Item;

import connection.ConnectionBuilder;
import dao.ItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductItem;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
public class LoadItemList extends HttpServlet {

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
            Session session = ConnectionBuilder.hibSession();
            String html = "";
            List<ProductItem> itemList = new ItemDAO().getItemList(session);
            for (ProductItem productItem : itemList) {
                if(productItem.isStatus()){
                   html += "<tr>\n" +
"                        <td>"+productItem.getId()+"</td>\n" +
"                        <td>"+productItem.getName()+"</td>\n" +
"                        <td>"+productItem.getProduct().getId()+" : "+productItem.getProduct().getName()+
                           " / [CAT] "+productItem.getProduct().getProductCategory().getId()+" : "+productItem.getProduct().getProductCategory().getName()+"</td>\n" +
"                        <td>"+productItem.getSku()+"</td>\n" +
"                        <td>"+productItem.getQty()+"</td>\n" +
"                        <td>"+productItem.getPrice()+"</td>\n" +
"                        <td>Active</td>\n" +
"                        <td class=\"\">\n" +
"                            <button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateItem('"+productItem.getId()+"')\">Update</button>\n" +
"                            <button class=\"btn btn-danger btn-categoryList-tableTow\" onclick=\"clickOnInactivateItem('"+productItem.getId()+"')\">Inactivate</button>\n" +
"                        </td>\n" +
"                    </tr>"; 
                }else{
                    html += "<tr>\n" +
"                        <td>"+productItem.getId()+"</td>\n" +
"                        <td>"+productItem.getName()+"</td>\n" +
"                        <td>"+productItem.getProduct().getId()+" : "+productItem.getProduct().getName()+
                           " / [CAT] "+productItem.getProduct().getProductCategory().getId()+" : "+productItem.getProduct().getProductCategory().getName()+"</td>\n" +
"                        <td>"+productItem.getSku()+"</td>\n" +
"                        <td>"+productItem.getQty()+"</td>\n" +
"                        <td>"+productItem.getPrice()+"</td>\n" +
"                        <td>Inactive</td>\n" +
"                        <td class=\"\">\n" +
"                            <button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateItem('"+productItem.getId()+"')\">Update</button>\n" +
"                            <button class=\"btn btn-warning btn-categoryList-tableTow\" onclick=\"clickOnActivateItem('"+productItem.getId()+"')\">Activate</button>\n" +
"                        </td>\n" +
"                    </tr>";
                }
            }
            out.print(html);
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
