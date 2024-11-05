/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Variation;

import connection.ConnectionBuilder;
import dao.VariationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Variation;
import org.hibernate.Session;

public class GenerateVariationList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Session session = ConnectionBuilder.hibSession();
            List<Variation> variationList = new VariationDAO().getVariationList(session);
            String html = "";
            for (Variation variation : variationList) {
                html += " <tr>\n"
                        + "<td>" + variation.getId() + "</td>\n"
                        + "<td>" + variation.getName() + "</td>\n"
                        + "<td>" + variation.getProductCategory().getId() + " : " + variation.getProductCategory().getName() + "</td>\n"
                        + "<td class=\"\">\n"
                        + "<button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateVariation('" + variation.getId() + "')\">Update</button>\n"
                        + "</td>\n"
                        + "</tr>";
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
